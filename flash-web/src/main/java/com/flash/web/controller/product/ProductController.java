package com.flash.web.controller.product;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.ShopProductInfo;
import com.flash.base.web.response.BaseResponse;
import com.flash.base.web.tool.query.ShopProductQuery;
import com.flash.domain.ShopProduct;
import com.flash.exception.base.BaseException;
import com.flash.service.ShopProductService;

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
	@RequestMapping(value = "/products_onsale/{shop_id}")
	public @ResponseBody BaseResponse<Page<ShopProductInfo>> onsaleProducts(@PathVariable(value="shop_id") int shopId, ShopProductQuery query){
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
	@RequestMapping(value = "/product_detail/{shop_product_id}")
	public @ResponseBody BaseResponse<ShopProductInfo> productDetail(@PathVariable(value="shop_product_id") int shopProductId) throws BaseException{
		ShopProductInfo productInfo = this.shopProductService.getProductDetail(shopProductId);
		return new BaseResponse<ShopProductInfo>(productInfo);
	}
}
