package com.flash.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.GuessShop;
import com.flash.base.web.dto.shop.ShopDto;
import com.flash.base.web.response.BaseResponse;
import com.flash.base.web.tool.query.ShopQuery;
import com.flash.service.ShopService;
import com.flash.ucenter.privilege.annotation.PrivilegeAccess;

@Controller
@RequestMapping("/shop")
public class ShopController {
	private static final Logger logger = LoggerFactory
			.getLogger(ShopController.class);
	
	@Resource(name = "shopService")
	private ShopService shopService;
	
	/**
	 * 猜测用户所在超市,并且可以获取附近的超市列表
	 * @param lat
	 * @param lng
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/shop_guess/{lat}/{lng}/{city}", method = RequestMethod.GET)
	public @ResponseBody BaseResponse<List<GuessShop>> guessShop(@PathVariable double lat, @PathVariable double lng, @PathVariable int city){
		List<GuessShop> result = this.shopService.guessShopsNearby(lat, lng, city);
		return new BaseResponse<List<GuessShop>>(result);
	}
	
	/**
	 * 查看某个地区的所有超市，知道地区id，并且知道坐标
	 * @param city 地区id
	 * @param lng 纬度
	 * @param lat 经度
	 * @return
	 */
	@PrivilegeAccess(sign = "CKDPLB")
	@RequestMapping(value = "/shop_list/{city}/{lng}/{lat}")
	public @ResponseBody BaseResponse<List<GuessShop>> shopListByCity(@PathVariable int city ,@PathVariable double lng,@PathVariable double lat){
		logger.debug("当前线程名称{}" , Thread.currentThread().getName());
		List<GuessShop> result = this.shopService.getShopListByCityId(city, lng, lat);
		return new BaseResponse<List<GuessShop>>(result);
	}
	
	/**
	 * 查看某个地区的所有超市 只知道地区id，不知道坐标
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/shop_list/{city}")
	public @ResponseBody BaseResponse<List<GuessShop>> shopListNearby(@PathVariable int city){
		List<GuessShop> result = this.shopService.getShopListByCityId(city, 0, 0);
		return new BaseResponse<List<GuessShop>>(result);
	}
	
	/**
	 * 条件查询超市列表
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/shop_list_query",method = RequestMethod.GET)
	public @ResponseBody BaseResponse<Page<ShopDto>> listShop(ShopQuery query){
		if(null == query){
			return new BaseResponse<Page<ShopDto>>(201, "非法的查询", null);
		}
		Page<ShopDto> page = this.shopService.listByShopQuery(query);
		return new BaseResponse<Page<ShopDto>>(page);
	}
	
}
