package com.flash.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.flash.base.web.dto.GuessShop;
import com.flash.commons.earth.EarthUtils;
import com.flash.dao.ShopDao;
import com.flash.domain.Shop;
import com.flash.service.ShopService;

@Service("shopService")
public class ShopServiceImpl implements ShopService{

	@Resource(name = "shopDao")
	private ShopDao shopDao;
	
	@Override
	public List<GuessShop> guessShopsNearby(Double lat, Double lng, int cityId) {
		List<Shop> allShop = this.shopDao.findEntitiesByString("cityId", cityId);
		List<GuessShop> result = new ArrayList<GuessShop>();
		for (Shop shop : allShop) {
			double distance = EarthUtils.getDistance(shop.getLng(),
					shop.getLat(), lng, lat);
			if(distance <= 1000){
				GuessShop guessShop = new GuessShop();
				BeanUtils.copyProperties(shop, guessShop);
				result.add(guessShop);
			}
		}
		
		return result;
	}

}
