package com.flash.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flash.base.web.dto.GuessShop;
import com.flash.base.web.response.BaseResponse;
import com.flash.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	private static final Logger logger = LoggerFactory
			.getLogger(ShopController.class);
	
	@Resource(name = "shopService")
	private ShopService shopService;
	
	@RequestMapping(value = "/shop_guess/{lat}/{lng}/{city}", method = RequestMethod.GET)
	public BaseResponse<List<GuessShop>> guessShop(@PathVariable Double lat, @PathVariable Double lng, @PathVariable int city){
		List<GuessShop> result = this.shopService.guessShopsNearby(lat, lng, city);
		return new BaseResponse<List<GuessShop>>(result);
	}
	
}
