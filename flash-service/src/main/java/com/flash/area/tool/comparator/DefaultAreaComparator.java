package com.flash.area.tool.comparator;

import java.util.Comparator;

import com.flash.area.domain.Area;
/**
 * 默认的排序规则：先根据sort属性排序，1-100,后根据拼音首字母排序
 * @author Leon
 * @date 2015年2月4日
 * @since v 1.0
 */
public class DefaultAreaComparator implements Comparator<Area>{

	/**
     * 如果a1小于a2,返回一个负数;如果a1大于a2，返回一个正数;如果他们相等，则返回0;
     */
	@Override
	public int compare(Area a1, Area a2) {
		if(a1.getSort() > a2.getSort()){
			//说明a1小
			return 1;
		}else if (a1.getSort() == a2.getSort()) {
			//说明相等，按照拼音首字母排序
			if(a1.getPin().charAt(0) > a2.getPin().charAt(0)){
				//按照字母排序，
				return 1;
			}else if (a1.getPin().charAt(0) == a2.getPin().charAt(0)) {
				return 0;
			}else {
				return -1;
			}
		}else{
			//说明a2在前
			return -1;
		}
	}
}
