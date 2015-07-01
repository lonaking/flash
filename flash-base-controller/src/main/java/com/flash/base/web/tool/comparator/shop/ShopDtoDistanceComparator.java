package com.flash.base.web.tool.comparator.shop;

import java.util.Comparator;

import com.flash.base.web.dto.shop.ShopDto;

public class ShopDtoDistanceComparator implements Comparator<ShopDto> {

	@Override
	public int compare(ShopDto o1, ShopDto o2) {
		if(null == o1 && null == o2)
			return 0;
		if(null == o1 && null != o2 && o2.getDistance() != 0)
			return -1;
		if(null != o1 && null == o2 && o1.getDistance() != 0)
			return 1;
		int c = o1.getDistance() - o2.getDistance();
		return c;
	}

}
