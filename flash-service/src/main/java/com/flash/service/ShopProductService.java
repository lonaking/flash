package com.flash.service;

import com.flash.base.service.CommonService;
import com.flash.base.tool.page.Page;
import com.flash.base.tool.query.BaseQuery;
import com.flash.base.web.dto.ShopProductInfo;
import com.flash.domain.ShopProduct;

public interface ShopProductService extends CommonService<ShopProduct>{

	public Page<ShopProductInfo> findOnsaleShopProducts(BaseQuery query);

}
