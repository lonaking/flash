package com.flash.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.flash.base.dao.CommonDao;
import com.flash.base.tool.page.Page;
import com.flash.base.tool.query.BaseQuery;
import com.flash.base.utils.DateUtils;

public class CommonDaoImpl<T> implements CommonDao<T> {
	private Class cla;
	private ClassMetadata classMetadata;
	public Class getCla() {
		return cla;
	}
	public ClassMetadata getClassMetadata() {
		return classMetadata;
	}
	
	public CommonDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.cla = (Class) type.getActualTypeArguments()[0];
	}

	@Resource(name = "hibernateTemplate")
	public HibernateTemplate hibernateTemplate;

	@PostConstruct
	public void init() {
		this.classMetadata = this.hibernateTemplate.getSessionFactory()
				.getClassMetadata(this.cla);
	}

	@Override
	@Transactional
	public void saveEntity(T t) {
		this.hibernateTemplate.save(t);

	}

	@Override
	@Transactional
	public void updateEntity(T t) {
		this.hibernateTemplate.update(t);

	}

	@Override
	@Transactional
	public void saveOrUpdateEntity(T t) {
		this.hibernateTemplate.saveOrUpdate(t);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void deleteEntityById(Serializable id) {
		T t = (T) this.hibernateTemplate.get(this.cla, id);
		this.hibernateTemplate.delete(t);
	}

	@Override
	@Transactional
	public void deleteEntityByIds(Serializable[] ids) {
		StringBuffer hql = new StringBuffer("from " + this.cla.getName());
		String tempIds = Arrays.toString(ids).substring(1,
				Arrays.toString(ids).length() - 1);
		hql.append(" where " + this.classMetadata.getIdentifierPropertyName()
				+ " in (" + tempIds + ")");
		List<T> list = this.hibernateTemplate.find(hql.toString());
		if (list.size() >= 1) {
			this.hibernateTemplate.deleteAll(list);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findEntityById(Serializable id) {
		return (T) this.hibernateTemplate.get(this.cla, id);
	}

	@Override
	public List<T> findEntitiesByString(String string, Object value) {
		List<T> list = this.hibernateTemplate.find("from " + this.cla.getName()
				+ " where " + string + "=?", value);
		if (null == list || list.size() == 0) {
			return null;
		}
		return list;
	}
	
	@Override
	public T findEntityByString(final String string, final Object value) {
		// List<T> list =
		// this.hibernateTemplate.find("from "+this.cla.getName()+" where ?=?",string,value);
		List<T> list = findEntitiesByString(string, value);
		return list.get(0);
	}

	@Override
	public List<T> findAll() {
		List<T> list = this.hibernateTemplate
				.find("from " + this.cla.getName());
		return list;
	}

	@Override
	public Page<T> findPage(final BaseQuery baseQuery) {
		// 设置分页的信息
		Page<T> page = new Page<T>(baseQuery.getCurrentPage(),
				baseQuery.getPageSize(), getTotalCount(baseQuery));
		// 拼接hql语句
		final StringBuffer hql = new StringBuffer("from " + this.cla.getName()
				+ " where 1=1");
		// 1->.构造最普通的where语句
		final Map<String, Object> keyValues = baseQuery.buildWhere();
		for (Entry<String, Object> entry : keyValues.entrySet()) {
			if (entry.getKey().contains(".")) {
				hql.append(" and " + entry.getKey() + "=:"
						+ entry.getKey().split("\\.")[1]);
			} else {
				hql.append(" and " + entry.getKey() + "=:" + entry.getKey());
			}
		}
		// 2->. 构造有时间查询的hql语句
		final Map<String, Integer> queryBetweenDate = baseQuery
				.buildQueryBetweenDate();
		if (queryBetweenDate != null && queryBetweenDate.size() >= 1) {
			// TODO 这里应用for循环遍历，start+index 这样以便于区分多个时间区间查询
			for (Entry<String, Integer> entry : queryBetweenDate.entrySet()) {
				hql.append(" and (" + entry.getKey() + " >= :start and "
						+ entry.getKey() + " <= :end)");
			}
		}

		// 3->.构造orderby排序语句 无需给query赋值
		Map<String, String> orderByValues = baseQuery.orderByValues;
		if (baseQuery.orderByValues.size() > 0) {
			hql.append(" order by ");
			boolean isFirst = true;
			for (Entry<String, String> entry : orderByValues.entrySet()) {
				if (isFirst) {
					hql.append(entry.getKey() + " " + entry.getValue());
					isFirst = false;
				} else {
					hql.append(" and " + entry.getKey() + " "
							+ entry.getValue());
				}
			}
		}

		List<T> pageData = this.hibernateTemplate
				.execute(new HibernateCallback<List<T>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql.toString());
						// 循环给query条件赋值
						for (Entry<String, Object> entry : keyValues.entrySet()) {
							if (entry.getKey().contains(".")) {
								query.setParameter(
										entry.getKey().split("\\.")[1],
										entry.getValue());
							} else {
								query.setParameter(entry.getKey(),
										entry.getValue());
							}
						}

						if (queryBetweenDate != null
								&& queryBetweenDate.size() > 0) {
							for (Entry<String, Integer> entry : queryBetweenDate
									.entrySet()) {
								if (entry.getValue() == BaseQuery.SELECT_TODAY) {
									query.setTimestamp("start",
											DateUtils.getTodayStartTimestamp());
									query.setTimestamp("end",
											DateUtils.getTodayEndTimestamp());
								} else if (entry.getValue() == BaseQuery.SELECT_WEEK) {
									// 本周
								} else if (entry.getValue() == BaseQuery.SELECT_MONTH) {
									// 本月
									query.setTimestamp("start",
											DateUtils.getOneMonthAgo());
									query.setTimestamp("end",
											DateUtils.getTodayEndTimestamp());
								} else if (entry.getValue() == BaseQuery.SELECT_BEFORE_MONTH) {
									// 一个月以前
									query.setTimestamp("start",
											DateUtils.getGreenwichDay());
									query.setTimestamp("end",
											DateUtils.getOneMonthAgo());
								}
							}
						}

						query.setFirstResult((baseQuery.getCurrentPage() - 1)
								* baseQuery.getPageSize());
						query.setMaxResults(baseQuery.getPageSize());

						return query.list();
					}
				});
		page.setPageData(pageData);
		return page;
	}

	/**
	 * 获取总记录数
	 * 
	 * @param baseQuery
	 * @return
	 */
	private int getTotalCount(final BaseQuery baseQuery) {
		final StringBuffer hql = new StringBuffer();
		hql.append("select count("
				+ this.classMetadata.getIdentifierPropertyName() + ") from "
				+ this.cla.getName());
		hql.append(" where 1=1");
		// 拼接where语句
		final Map<String, Object> keyValues = baseQuery.buildWhere();
		for (Entry<String, Object> entry : keyValues.entrySet()) {
			if (entry.getKey().contains(".")) {
				hql.append(" and " + entry.getKey() + "=:"
						+ entry.getKey().split("\\.")[1]);
			} else {
				hql.append(" and " + entry.getKey() + "=:" + entry.getKey());
			}
		}
		return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql.toString());
				// 循环给query对象赋值
				for (Entry<String, Object> entry : keyValues.entrySet()) {
					if (entry.getKey().contains(".")) {
						query.setParameter(entry.getKey().split("\\.")[1],
								entry.getValue());
					} else {
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				Long count = (Long) query.uniqueResult();
				return count.intValue();
			}
		});
	}

	@Override
	public List<T> findEntityByIds(Serializable[] ids) {
		if (ids.length > 0) {
			StringBuffer hql = new StringBuffer();
			hql.append("from " + this.cla.getName());
			String temp = Arrays.toString(ids).substring(1,
					Arrays.toString(ids).length() - 1);
			hql.append(" where "
					+ this.classMetadata.getIdentifierPropertyName() + " in("
					+ temp + ")");
			List<T> list = this.hibernateTemplate.find(hql.toString());
			return list;
		}
		return null;
	}

	@Override
	public Page<T> findPageByHqlMap(final BaseQuery baseQuery) {
		// 设置分页的信息
		Page<T> page = new Page<T>(baseQuery.getCurrentPage(),
				baseQuery.getPageSize(), getTotalCountByHqlMap(baseQuery));
		// 拼接hql语句
		final StringBuffer hql = new StringBuffer("from " + this.cla.getName()
				+ " where 1=1");
		// 1->.构造最普通的where语句
		final Map<String, Map<String, Object>> hqlValues = baseQuery
				.buildWhereHql();
		for (Entry<String, Map<String, Object>> entry : hqlValues.entrySet()) {
			hql.append(entry.getKey());
		}

		// 3->.构造orderby排序语句 无需给query赋值
		Map<String, String> orderByValues = baseQuery.orderByValues;
		if (baseQuery.orderByValues.size() > 0) {
			hql.append(" order by ");
			boolean isFirst = true;
			for (Entry<String, String> entry : orderByValues.entrySet()) {
				if (isFirst) {
					hql.append(entry.getKey() + " " + entry.getValue());
					isFirst = false;
				} else {
					hql.append(" and " + entry.getKey() + " "
							+ entry.getValue());
				}
			}
		}

		List<T> pageData = this.hibernateTemplate
				.execute(new HibernateCallback<List<T>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql.toString());
						// 循环给query条件赋值
						for (Entry<String, Map<String, Object>> entry : hqlValues
								.entrySet()) {
							for (Entry<String, Object> e : entry.getValue()
									.entrySet()) {
								query.setParameter(e.getKey(), e.getValue());
							}
						}
						query.setFirstResult((baseQuery.getCurrentPage() - 1)
								* baseQuery.getPageSize());
						query.setMaxResults(baseQuery.getPageSize());
						return query.list();
					}
				});
		page.setPageData(pageData);
		return page;
	}

	/**
	 * 获取总记录数
	 * 
	 * @param baseQuery
	 * @return
	 */
	private int getTotalCountByHqlMap(final BaseQuery baseQuery) {
		final StringBuffer hql = new StringBuffer();
		hql.append("select count("
				+ this.classMetadata.getIdentifierPropertyName() + ") from "
				+ this.cla.getName() + " where 1=1");
		// 拼接where语句
		final Map<String, Map<String, Object>> hqlValues = baseQuery
				.buildWhereHql();
		// 看看where从哪个map过来
		for (Entry<String, Map<String, Object>> entry : hqlValues.entrySet()) {
			hql.append(entry.getKey());
		}
		return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql.toString());
				// 循环给query对象赋值
				for (Entry<String, Map<String, Object>> entry : hqlValues
						.entrySet()) {
					for (Entry<String, Object> e : entry.getValue().entrySet()) {
						query.setParameter(e.getKey(), e.getValue());
					}
				}
				Long count = (Long) query.uniqueResult();
				return count.intValue();
			}
		});
	}
}
