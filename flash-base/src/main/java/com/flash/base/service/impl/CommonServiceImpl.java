package com.flash.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.flash.base.dao.CommonDao;
import com.flash.base.service.CommonService;
import com.flash.base.tool.page.Page;
import com.flash.base.tool.query.BaseQuery;

public abstract class CommonServiceImpl<T> implements CommonService<T> {
	
	public abstract CommonDao<T> getCommonDao();

	@Override
	public Page<T> findPage(BaseQuery baseQuery) {
		return this.getCommonDao().findPage(baseQuery);
	}

	@Override
	public Page<T> findPageByHqlMap(BaseQuery baseQuery) {
		return this.getCommonDao().findPageByHqlMap(baseQuery);
	}

	@Override
	public List<T> findAll() {
		List<T> list = this.getCommonDao().findAll();
		return list;
	}

	@Override
	@Transactional
	public void saveEntity(T t) {
		this.getCommonDao().saveEntity(t);
	}

	@Override
	@Transactional
	public void updateEntity(T t) {
		this.getCommonDao().updateEntity(t);
	}

	@Override
	@Transactional
	public void saveOrUpdateEntity(T t) {
		this.getCommonDao().saveOrUpdateEntity(t);

	}

	@Override
	public T getEntryById(Serializable id) {
		return (T) this.getCommonDao().findEntityById(id);
	}

	@Override
	@Transactional
	public void deleteEntryById(Serializable id) {
		this.getCommonDao().deleteEntityById(id);
	}

	@Override
	@Transactional
	public void deleteEntriesByIds(Serializable[] ids) {
		this.getCommonDao().deleteEntityByIds(ids);
	}
}
