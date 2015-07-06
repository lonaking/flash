package com.flash.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.ShopProductInfo;
import com.flash.base.web.response.BaseResponse;

@Controller
@RequestMapping(value="/product_search")
public class ProductSearchController {

	/**
	 * 根据商品名搜索商品
	 * @return
	 */
	public @ResponseBody BaseResponse<Page<ShopProductInfo>> searchShopProduct(){
		return null;
	}
}
