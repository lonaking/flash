package com.flash.base.service;

import java.io.Serializable;
import java.util.List;

import com.flash.base.tool.page.Page;
import com.flash.base.tool.query.BaseQuery;

public interface CommonService<T> {
	
	public Page<T> findPage(final BaseQuery baseQuery);

	public Page<T> findPageByHqlMap(final BaseQuery baseQuery);

	public List<T> findAll();

	public void saveEntity(T t);

	public void updateEntity(T t);

	public void saveOrUpdateEntity(T t);

	public T getEntityById(Serializable id);

	public void deleteEntryById(Serializable id);

	public void deleteEntriesByIds(Serializable[] ids);

}
