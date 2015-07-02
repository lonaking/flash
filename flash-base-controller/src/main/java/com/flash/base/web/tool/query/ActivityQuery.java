package com.flash.base.web.tool.query;

import java.sql.Date;
import java.util.Map;

import com.flash.base.tool.query.BaseQuery;

public class ActivityQuery extends BaseQuery {

	@Override
	public Map<String, Object> buildWhere() {
		if (null != type) {
			super.keyValues.put("type", this.type);
		}
		if (null != status) {
			super.keyValues.put("status", this.status);
		}
		if (null != shopId) {
			super.keyValues.put("shop.id", this.shopId);
		}
		return super.keyValues;
	}

	@Override
	public Map<String, Map<String, Object>> buildWhereHql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> buildQueryBetweenDate() {
		
		return null;
	}

	private Integer type;
	private Integer status;
	private Integer shopId;
	private Date startTime;//待定
	private Date endTime;//待定

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
