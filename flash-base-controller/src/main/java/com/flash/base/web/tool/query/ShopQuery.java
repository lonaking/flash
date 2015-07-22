package com.flash.base.web.tool.query;

import java.util.Map;

import com.flash.base.tool.query.BaseQuery;

public class ShopQuery extends BaseQuery {

	@Override
	public Map<String, Object> buildWhere() {
		if(null != this.name && !"".equals(this.name)){
			super.keyValues.put("name", this.name);
		}
		if(null != cityId && cityId > 0){
			super.keyValues.put("cityId", this.cityId);
		}
		if(null != type){
			super.keyValues.put("type", this.type);
		}
		if(null != this.pin && !("".equals(this.pin))){
			
		}
		if(null != lng && null != lat){
			super.addOne("lng", this.lng);
			super.addOne("lat", this.lat);
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
		// TODO Auto-generated method stub
		return null;
	}

	private String name;
	private Integer cityId;
	private Double lng;
	private Double lat;
	private Integer type;
	private String pin;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
}
