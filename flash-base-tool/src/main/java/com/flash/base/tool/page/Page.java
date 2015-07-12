package com.flash.base.tool.page;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.flash.base.tool.page.utils.PageUtils;
/**
 * 分页信息
 * @author leon
 */
public class Page<T> {
	private int currentPage;//当前页
	private int beforePage;//上一页
	private int nextPage;//下一页
	private int pageSize;//页码
	private int pageStart;//当前页码开始标号
	private int pageEnd;//当前页页码结束标号
	@JsonIgnore
	private int[] pagesIndex;//页码数组
	private String pagesIndexStr;//页码数组按照字符串排列1,2,3,4,5
	private int totalPages;//总页码
	private int totalCount;//总记录数
	private List<T> pageData = new ArrayList<T>();//当前页的数据

	
	
	/**
	 * 通过当前页面和页面尺寸即可构造出一个Page对象
	 * @param currentPage
	 * @param pageSize
	 * @param totalCount
	 */
	public Page(int currentPage, int pageSize, int totalCount) {
		this.totalCount = totalCount;
		// 当前页最小值为1
		this.currentPage = Math.max(currentPage,1);
		// 每页显示最少为10
		this.pageSize = Math.max(this.pageSize,10);
		// 上一页
		this.beforePage = this.beforePage<=0 ? 1 :this.currentPage-1;
		//总页数 工具类计算出来
		this.totalPages = PageUtils.getTotalPages(totalCount, pageSize);
		//处理当前页面越界的错误 
		this.currentPage = Math.min(this.currentPage, this.totalPages);
		//当前页面开始数据从第几条开始
		this.pageStart = (this.currentPage - 1) * this.pageSize;
		//当前页面最后一条数据是第几条
		this.pageEnd = this.pageStart + this.pageSize;
		//下一页
		this.nextPage = Math.min(this.currentPage + 1, this.totalPages);
		//设置页码分组
		this.pagesIndex = PageUtils.getPagesIndexs(this.currentPage, this.totalPages);
		//设置页码分组的字符串形式
		this.pagesIndexStr = Arrays.toString(this.pagesIndex).substring(1,Arrays.toString(this.pagesIndex).length()-1).replaceAll(" ", "");
	}
	/**
	 * 若采用本构造方法，在查询结果务必使用setPageData()方法注入数据。
	 * @param currentPage
	 * @param pageSize
	 */
	public Page(int currentPage, int pageSize) {
		// 当前页最小值为1
		this.currentPage = Math.max(currentPage,1);
		// 每页显示最少为10
		this.pageSize = Math.max(this.pageSize,10);
	}
	
	/**
	 * 一般不使用此构造方法 只在copyProperties的时候使用
	 */
	@Deprecated
	public Page() {
		
	}
	public final List<T> getPageData() {
		return pageData;
	}

	/**
	 * 这个方法重写了 
	 * @param pageData
	 */
	public final void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBeforePage() {
		return beforePage;
	}

	public void setBeforePage(int beforePage) {
		this.beforePage = beforePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int[] getPagesIndex() {
		return pagesIndex;
	}

	public void setPagesIndex(int[] pagesIndex) {
		this.pagesIndex = pagesIndex;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(Integer pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public final String getPagesIndexStr() {
		return pagesIndexStr;
	}

	public final void setPagesIndexStr(String pagesIndexStr) {
		this.pagesIndexStr = pagesIndexStr;
	}
	
}