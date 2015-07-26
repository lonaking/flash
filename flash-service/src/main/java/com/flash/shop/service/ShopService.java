package com.flash.shop.service;

import java.util.List;

import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.GuessShop;
import com.flash.base.web.dto.shop.ShopDto;
import com.flash.base.web.form.shop.ShopAddForm;
import com.flash.base.web.form.shop.ShopUpdateForm;
import com.flash.base.web.tool.query.ShopQuery;
import com.flash.shop.exception.ShopServiceException;

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

	/**
	 * 添加商铺
	 * @author lonaking
	 * @param shopForm
	 * @return
	 */
	public ShopDto addShop(ShopAddForm shopForm);

	/**
	 * 更新超市信息
	 * @author lonaking
	 * @param shopForm
	 * @return
	 */
	public ShopDto updateShop(ShopUpdateForm shopForm);

	/**
	 * 删除一个超市，软删除
	 * @author lonaking
	 * @param shopId
	 * @throws BaseException 
	 */
	public void softDeleteShopById(Integer shopId) throws ShopServiceException;

	/**
	 * 获取超市详细信息
	 * @param shopId
	 * @return
	 */
	public ShopDto getShopInfo(Integer shopId);
}
