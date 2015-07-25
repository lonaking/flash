package com.flash.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.flash.base.dao.CommonDao;
import com.flash.base.service.impl.CommonServiceImpl;
import com.flash.base.tool.page.Page;
import com.flash.base.tool.query.BaseQuery;
import com.flash.base.web.dto.ShopProductInfo;
import com.flash.exception.NullEntityException;
import com.flash.exception.base.BaseException;
import com.flash.product.dao.ShopProductDao;
import com.flash.product.domain.ShopProduct;
import com.flash.product.service.ShopProductService;
@Service("shopProductService")
public class ShopProductServiceImpl extends CommonServiceImpl<ShopProduct> implements ShopProductService{

	@Resource(name = "shopProductDao")
	private ShopProductDao shopProductDao;
	
	@Override
	public CommonDao<ShopProduct> getCommonDao() {
		return this.shopProductDao;
	}
	
	@Override
	public Page<ShopProductInfo> findOnsaleShopProducts(BaseQuery query) {
		Page<ShopProduct> page = this.shopProductDao.findPage(query);
		if( null == page.getPageData() || page.getPageData().size() == 0)
			return null;
		
		List<ShopProductInfo> resultData = new ArrayList<ShopProductInfo>();
		
		for (ShopProduct shopProduct : page.getPageData()) {
			ShopProductInfo shopProductInfo = new ShopProductInfo();
			BeanUtils.copyProperties(shopProduct, shopProductInfo);
			//BeanUtils.copyProperties(shopProduct.getProduct(), shopProductInfo,new String[]{"id"});
			resultData.add(shopProductInfo);
		}
		Page<ShopProductInfo> result = new Page<ShopProductInfo>(page.getCurrentPage(), page.getPageSize());
		BeanUtils.copyProperties(page, result, new String[]{"pageData"});
		result.setPageData(resultData);
		return result;
	}

	/**
	 * 根据id查询商品详细信息
	 * @throws NullEntityException 
	 */
	@Override
	public ShopProductInfo getProductDetail(int shopProductId) throws BaseException {
		ShopProduct shopProduct = this.shopProductDao.findEntityById(shopProductId);
		if(null == shopProduct) 
			throw new NullEntityException("商品不存在");
		ShopProductInfo shopProductInfo = new ShopProductInfo();
		BeanUtils.copyProperties(shopProduct, shopProductInfo);
		//BeanUtils.copyProperties(shopProduct.getProduct(), shopProductInfo,new String[]{"id"});
		return shopProductInfo;
	}

}
