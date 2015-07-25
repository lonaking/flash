package com.flash.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.flash.base.dao.impl.RedisAndDbDaoImpl;
import com.flash.product.dao.ShopProductDao;
import com.flash.product.domain.ShopProduct;

@Repository("shopProductDao")
public class ShopProductDaoImpl extends RedisAndDbDaoImpl<ShopProduct> implements ShopProductDao{

}
