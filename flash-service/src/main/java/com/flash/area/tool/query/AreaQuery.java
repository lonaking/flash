package com.flash.area.tool.query;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.flash.base.tool.query.BaseQuery;

public class AreaQuery extends BaseQuery {
	
	public static final String QUERY_TYPE_ALL = "all";
	
	public static final String QUERY_TYPE_CHILD = "child";
	
	public static final String QUERY_TYPE_COUNTRY = "country";
	
	public static final String QUERY_TYPE_PROVINCE = "province";
	
	public static final String QUERY_TYPE_CITY = "city";
	
	public static final String QUERY_TYPE_COUNTY = "county";//县城
	
	public static final String QUERY_TYPE_MCITY = "mcity";//直辖市
	/**
	 * 构造where语句
	 * @Override
	 */
	public Map<String, Object> buildWhere() {
		if (this.id != null) {
			super.keyValues.put("id", this.id);
		}
		if(this.pid != null){
			super.keyValues.put("pid", this.pid);
		}
		if (StringUtils.isNotBlank(this.name)) {
			super.keyValues.put("name", this.name);
		}
		if(this.scope != null){
			super.keyValues.put("scope", this.scope);
		}
		if(this.isRoot != null){
			super.keyValues.put("isRoot", this.isRoot);
		}
		return super.keyValues;
	}
	
	/**
	 * 构造hql语句
	 * @return
	 */
	@Override
	public Map<String, Map<String, Object> > buildWhereHql() {
		
		return super.hqlValues;
	}

	@Override
	public Map<String, Integer> buildQueryBetweenDate() {
		
		return null;
	}
	
	private Integer id;
	private Integer pid;
	private String name;
	private Integer scope;//类别
	private Boolean isRoot;
	private Integer sort;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
