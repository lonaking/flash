package com.flash.web.controller.product;

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
import com.flash.product.exception.ProductServiceException;
import com.flash.product.service.ShopProductService;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(ProductController.class);
	
	@Resource(name = "shopProductService")
	private ShopProductService shopProductService;
	
	
	/**
	 * 查看某个超市内的促销商品
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/products_onsale/{shopId}")
	public @ResponseBody BaseResponse<Page<ShopProductInfo>> onsaleProducts(@PathVariable int shopId, ShopProductQuery query){
		query.setOnsale(true);
		query.setShopId(shopId);
		Page<ShopProductInfo> result = this.shopProductService.findOnsaleShopProducts(query);
		return new BaseResponse<Page<ShopProductInfo>>(result);
	}
	
	/**
	 * 商品详情
	 * @param shopProductId
	 * @return
	 * @throws BaseException 
	 */
	@RequestMapping(value = "/product_detail/{shopProductId}")
	public @ResponseBody BaseResponse<ShopProductInfo> productDetail(@PathVariable int shopProductId) throws ProductServiceException{
		ShopProductInfo productInfo = this.shopProductService.getProductDetail(shopProductId);
		return new BaseResponse<ShopProductInfo>(productInfo);
	}
}
