package com.flash.base.web.tool.query;

import java.util.Map;

import com.flash.base.tool.query.BaseQuery;

public class ShopProductQuery extends BaseQuery{

	@Override
	public Map<String, Object> buildWhere() {
		if(shopId > 0)
			super.keyValues.put("id", this.shopId);
		if(null != onsale)
			super.keyValues.put("onsale", this.onsale);
		return super.keyValues;
	}

	@Override
	public Map<String, Map<String, Object>> buildWhereHql() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> buildQueryBetweenDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int shopId;
	private Boolean onsale;
	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public Boolean getOnsale() {
		return onsale;
	}

	public void setOnsale(Boolean onsale) {
		this.onsale = onsale;
	}
}
