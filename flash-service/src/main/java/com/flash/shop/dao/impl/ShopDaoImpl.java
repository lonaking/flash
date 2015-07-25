package com.flash.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flash.base.dao.impl.RedisAndDbDaoImpl;
import com.flash.shop.dao.ShopDao;
import com.flash.shop.domain.Shop;

@Repository("shopDao")
public class ShopDaoImpl extends RedisAndDbDaoImpl<Shop> implements ShopDao{

	@Override
	public List<Shop> findShopsNearBy(Double lat, Double lng, int distance) {
		if(distance == 0){
			distance = 1000;
		}
		StringBuffer hql = new StringBuffer("from "+ this.getCla().getName() +" where cityId");
		List<Shop> list = this.hibernateTemplate.find("", lat, lng);
		if(null == list || list.size() == 0){
			return null;
		}
		return list;
	}

}
