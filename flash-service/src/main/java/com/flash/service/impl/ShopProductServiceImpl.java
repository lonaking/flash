package com.flash.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;

import com.flash.base.tool.page.Page;
import com.flash.base.tool.query.BaseQuery;
import com.flash.base.web.dto.ShopProductInfo;
import com.flash.dao.ShopProductDao;
import com.flash.domain.ShopProduct;
import com.flash.service.ShopProductService;

public class ShopProductServiceImpl implements ShopProductService{

	@Resource(name = "shopProductDao")
	private ShopProductDao shopProductDao;
	
	@Override
	public Page<ShopProductInfo> findOnsaleShopProducts(BaseQuery query) {
		Page<ShopProduct> page = this.shopProductDao.findPage(query);
		if( null == page.getPageData() || page.getPageData().size() == 0)
			return null;
		
		List<ShopProductInfo> resultData = new ArrayList<ShopProductInfo>();
		
		for (ShopProduct shopProduct : page.getPageData()) {
			ShopProductInfo shopProductInfo = new ShopProductInfo();
			BeanUtils.copyProperties(shopProduct, shopProductInfo);
			BeanUtils.copyProperties(shopProduct.getProduct(), shopProductInfo);
			resultData.add(shopProductInfo);
		}
		Page<ShopProductInfo> result = new Page<ShopProductInfo>(page.getCurrentPage(), page.getPageSize());
		BeanUtils.copyProperties(page, result, new String[]{"pageData"});
		result.setPageData(resultData);
		return result;
	}

}
