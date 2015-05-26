package com.flash.service;

import com.flash.base.tool.page.Page;
import com.flash.base.tool.query.BaseQuery;
import com.flash.base.web.dto.ShopProductInfo;

public interface ShopProductService {

	public Page<ShopProductInfo> findOnsaleShopProducts(BaseQuery query);

}
