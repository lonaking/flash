package com.flash.base.web.tool.comparator.shop;

import java.util.Comparator;

import com.flash.base.web.dto.GuessShop;
/**
 * GuessShop 根据distance排序
 * @author leon
 *
 */
public class GuessShopDistanceComparator implements Comparator<GuessShop>{

	/**
     * 如果a1小于a2,返回一个负数;如果a1大于a2，返回一个正数;如果他们相等，则返回0;
     */
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

}
