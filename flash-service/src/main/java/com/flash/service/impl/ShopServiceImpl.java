package com.flash.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.flash.base.web.dto.GuessShop;
import com.flash.base.web.tool.comparator.GuessShopDistanceComparator;
import com.flash.commons.earth.EarthUtils;
import com.flash.dao.ShopDao;
import com.flash.domain.Shop;
import com.flash.service.ShopService;

@Service("shopService")
public class ShopServiceImpl implements ShopService{

	@Resource(name = "shopDao")
	private ShopDao shopDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);
	
	@Override
	public List<GuessShop> guessShopsNearby(Double lat, Double lng, int cityId) {
		List<Shop> allShop = this.shopDao.findEntitiesByString("cityId", cityId);
		if(null == allShop || allShop.size() == 0)
			return null;
		List<GuessShop> result = new ArrayList<GuessShop>();
		for (Shop shop : allShop) {
			double distance = EarthUtils.getDistance(shop.getLng(),
					shop.getLat(), lng, lat);
			if(distance <= 2000){
				GuessShop guessShop = new GuessShop();
				BeanUtils.copyProperties(shop, guessShop);
				guessShop.setDistance((int) distance);
				result.add(guessShop);
			}
		}
		Collections.sort(result, new GuessShopDistanceComparator());
		return result;
	}

	@Override
	public List<GuessShop> getShopListByCityId(int cityId ,double lng, double lat) {
		List<Shop> allShop = this.shopDao.findEntitiesByString("cityId", cityId);
		if(null == allShop || allShop.size() == 0)
			return null;
		List<GuessShop> result = new ArrayList<GuessShop>();
		for (Shop shop : allShop) {
			GuessShop guessShop = new GuessShop();
			BeanUtils.copyProperties(shop, guessShop);
			if((lng != -1 && lat != -1) && ( lng != 0 && lat != 0) ){
				logger.info("lng = {} , lat = {} , cityId = {}",lng, lat , cityId);
				double distance = EarthUtils.getDistance(shop.getLng(),
						shop.getLat(), lng, lat);
				guessShop.setDistance((int) distance);
			}
			result.add(guessShop);
		}
		Collections.sort(result, new GuessShopDistanceComparator());
		logger.debug("当前线程名称{}",Thread.currentThread().getName());
		return result;
	}

}
