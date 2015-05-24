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
import com.flash.base.web.response.BaseResponse;
import com.flash.domain.Shop;
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
	public @ResponseBody BaseResponse<List<GuessShop>> guessShop(@PathVariable Double lat, @PathVariable Double lng, @PathVariable int city){
		List<GuessShop> result = this.shopService.guessShopsNearby(lat, lng, city);
		return new BaseResponse<List<GuessShop>>(result);
	}
	
	public BaseResponse<List<Shop>> shopsNearby(@PathVariable int city){
		return null;
	}
	
}
