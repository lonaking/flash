package com.flash.dao.impl;

import org.springframework.stereotype.Repository;

import com.flash.base.dao.impl.CommonDaoImpl;
import com.flash.base.dao.impl.RedisAndDbDaoImpl;
import com.flash.dao.ShopProductDao;
import com.flash.domain.ShopProduct;

@Repository("shopProductDao")
public class ShopProductDaoImpl extends RedisAndDbDaoImpl<ShopProduct> implements ShopProductDao{

}
