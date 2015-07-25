package com.flash.base.web.tool.query;

import java.util.Map;

import com.flash.base.tool.query.BaseQuery;

/**
 * 商品查询
 * 
 * @author Leon
 * @date 2014年12月23日
 * @since v 1.0
 */
public class ProductQuery extends BaseQuery {

	private String name;
	private String title;
	private int soldCount;
	private int soldToday;
	private int soldWeek;
	private int status;
	private boolean isDel = false;
	private int allowBook;
	private int isRecommend;
	private double comment;
	private int commentTimes;
	private Integer groupId;
	private Integer shopId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSoldCount() {
		return soldCount;
	}

	public void setSoldCount(int soldCount) {
		this.soldCount = soldCount;
	}

	public int getSoldToday() {
		return soldToday;
	}

	public void setSoldToday(int soldToday) {
		this.soldToday = soldToday;
	}

	public int getSoldWeek() {
		return soldWeek;
	}

	public void setSoldWeek(int soldWeek) {
		this.soldWeek = soldWeek;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isDel() {
		return isDel;
	}

	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}

	public int getAllowBook() {
		return allowBook;
	}

	public void setAllowBook(int allowBook) {
		this.allowBook = allowBook;
	}

	public int getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}

	public double getComment() {
		return comment;
	}

	public void setComment(double comment) {
		this.comment = comment;
	}

	public int getCommentTimes() {
		return commentTimes;
	}

	public void setCommentTimes(int commentTimes) {
		this.commentTimes = commentTimes;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	@Override
	public Map<String, Object> buildWhere() {
		if (this.groupId != null) {
			super.keyValues.put("group", this.groupId);
		}
		if (this.shopId != null) {
			super.keyValues.put("shop", this.shopId);
		}
		super.keyValues.put("isDel", this.isDel);
		return super.keyValues;
	}

	@Override
	public Map<String, Map<String, Object>> buildWhereHql() {

		return null;
	}

	@Override
	public Map<String, Integer> buildQueryBetweenDate() {
		// TODO Auto-generated method stub
		return null;
	}

}
