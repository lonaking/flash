package com.flash.service;

import java.util.List;

import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.GuessShop;
import com.flash.base.web.dto.shop.ShopDto;
import com.flash.base.web.tool.query.ShopQuery;

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

	/**
	 * 根据条件查询超市，支持条件：name　cityId　type　pin　lat lng
	 * @param query
	 * @return
	 */
	public Page<ShopDto> listByShopQuery(ShopQuery query);
}
