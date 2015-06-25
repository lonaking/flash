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

import com.flash.base.web.dto.GuessShop;
import com.flash.base.web.privilege.annotation.PrivilegeAccess;
import com.flash.base.web.response.BaseResponse;
import com.flash.service.ShopService;

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
	@PrivilegeAccess(sign = "shop_list:2")
	@RequestMapping(value = "/shop_list/{city}/{lng}/{lat}")
	public @ResponseBody BaseResponse<List<GuessShop>> shopListByCity(@PathVariable int city ,@PathVariable double lng,@PathVariable double lat){
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
	
}
