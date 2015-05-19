package com.flash.tool.query;

import java.util.HashMap;
import java.util.Map;

/**
 * 基本查询,此类封装了进行查询的时候，页面传入的当前页码和每页显示数量
 * @author Administrator
 */
public abstract class BaseQuery {
	public static final int DEFAULT_PAGESIZE = 10;
	public static final int DEFAULT_CURRENTPAGE = 1;
	public static final int SELECT_TODAY = 1;
	public static final int SELECT_WEEK = 2;
	public static final int SELECT_MONTH = 3;
	public static final int SELECT_BEFORE_MONTH = 4;
	public static final String DESC = "desc";//降序
	public static final String ASC = "asc";//升序
	
	//当前页
	private int currentPage = DEFAULT_CURRENTPAGE;
	//每页显示多少数据
	private int pageSize = DEFAULT_PAGESIZE;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = Math.max(currentPage, DEFAULT_CURRENTPAGE);
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = Math.max(pageSize, DEFAULT_PAGESIZE);
	}
	
	
	public Map<String, Object> keyValues = new HashMap<String, Object>();
	public abstract Map<String, Object> buildWhere();
	
	
	/*string:hql语句 []:  内部Map[String]名字  [Object]值*/
	public Map<String, Map<String,Object>> hqlValues = new HashMap<String, Map<String,Object>>();
	public abstract Map<String, Map<String, Object>> buildWhereHql();
	
	/*构造orderBy语句*/
	public Map<String, String> orderByValues = new HashMap<String, String>();
	public void buildOrderBy(String str, String sort){
		orderByValues.put(str, sort);
	};
	
	/*根据日期进行查询的时候*/
	public Map<String, Integer> queryBetweenDate = new HashMap<String, Integer>();
	public abstract Map<String, Integer> buildQueryBetweenDate();
	
}
