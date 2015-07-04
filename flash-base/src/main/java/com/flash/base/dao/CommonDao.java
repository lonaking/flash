package com.flash.base.dao;
import java.io.Serializable;
import java.util.List;

import com.flash.base.tool.page.Page;
import com.flash.base.tool.query.BaseQuery;
/**
 * 公共Dao
 * @author Administrator
 */
public interface CommonDao<T> {
	public void saveEntity(T t);
	
	public void updateEntity(T t);
	
	public void saveOrUpdateEntity(T t);
	
	public void deleteEntityById(Serializable id);
	
	public List<T> findEntitiesByString(final String string, final Object value);
	
/*	public List<T> findEntitiesByStrings(final Map<String, Object> stringMap);
*/	
	public T findEntityByString(final String string, final Object value);
	
	public void deleteEntityByIds(Serializable[] ids);
	
	public T findEntityById(Serializable id);
	
	public Page<T> findPage(final BaseQuery baseQuery);
	
	public Page<T> findPageByHqlMap(final BaseQuery baseQuery);

	public List<T> findAll();
	
	public List<T> findEntityByIds(Serializable[] ids);
	
}
