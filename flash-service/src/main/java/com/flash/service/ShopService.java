package com.flash.service;

import java.util.List;

import com.flash.base.web.dto.GuessShop;

public interface ShopService {

	/**
	 * 根据地理位置猜测当前位置附近的店铺
	 * @param lat
	 * @param lng
	 * @return
	 */
	public List<GuessShop> guessShopsNearby(Double lat, Double lng, int cityId);
	
	/**
	 * 根据城市id获取该城市下的所有店铺
	 * @param cityId
	 * @return
	 */
	public List<GuessShop> getShopListByCityId(int cityId, double lng ,double lat);
}
