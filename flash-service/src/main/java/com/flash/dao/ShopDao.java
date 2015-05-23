package com.flash.dao;

import java.util.List;

import com.flash.base.dao.CommonDao;
import com.flash.domain.Shop;

public interface ShopDao extends CommonDao<Shop>{

	/**
	 * 寻找指定范围内的所有超市
	 * @param lat
	 * @param lng
	 * @param distance
	 * @return
	 */
	public List<Shop> findShopsNearBy(Double lat, Double lng, int distance);
	
}

