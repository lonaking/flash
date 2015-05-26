package com.flash.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.ShopProductInfo;
import com.flash.base.web.response.BaseResponse;
import com.flash.base.web.tool.query.ShopProductQuery;
import com.flash.service.ShopProductService;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	private static final Logger logger = LoggerFactory
			.getLogger("ProductController");
	
	@Resource(name = "shopProductService")
	private ShopProductService shopProductService;
	
	
	/**
	 * 查看某个超市内的促销商品
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/onsale/{shopId}")
	public @ResponseBody BaseResponse<Page<ShopProductInfo>> onsaleProducts(@PathVariable int shopId, ShopProductQuery query){
		Page<ShopProductInfo> result = this.shopProductService.findOnsaleShopProducts(query);
		return new BaseResponse<Page<ShopProductInfo>>(result);
	}
}
