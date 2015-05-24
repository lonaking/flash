package com.flash.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		Collections.sort(result, new Comparator<GuessShop>() {

			@Override
			public int compare(GuessShop gs1, GuessShop gs2) {
				if(null == gs1 && null == gs2)
					return 0;
				if(null == gs1 && null != gs2 && gs2.getDistance() != 0)
					return -1;
				if(null != gs1 && null == gs2 && gs1.getDistance() != 0)
					return 1;
				int c = gs1.getDistance() - gs2.getDistance();
				return c;
			}
		});
		return result;
	}

}
