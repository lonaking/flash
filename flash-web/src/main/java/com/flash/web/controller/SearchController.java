package com.flash.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.tool.page.Page;
import com.flash.base.web.response.BaseResponse;
import com.flash.base.web.tool.query.ShopProductQuery;
import com.flash.domain.ShopProduct;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

	
	@RequestMapping(value = "/search/{shopId}/{keywords}")
	public @ResponseBody BaseResponse<Page<List<ShopProduct>>> search(@PathVariable int shopId, @PathVariable String keywords, ShopProductQuery query){
		query.setShopId(shopId);
		Page<List<ShopProduct>> result = this.searchService.searchByKeywords(query, keywords);
		return result;
	}
}
