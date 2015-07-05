package com.flash.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.tool.page.Page;
import com.flash.base.web.response.BaseResponse;
import com.flash.base.web.tool.query.ShopProductQuery;
import com.flash.domain.ShopProduct;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

	/**
	 * 搜索商品的api 若用户在超市，则搜索本超市，若用户不在超市，则搜索所有超市
	 * @param shopId
	 * @param keywords
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/search/{shopId}/{keywords}", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<Page<List<ShopProduct>>> search(ShopProductQuery query){
		
		//Page<List<ShopProduct>> result = this.searchService.searchByKeywords(query, keywords);
		return new BaseResponse<Page<List<ShopProduct>>>();
	}
}
