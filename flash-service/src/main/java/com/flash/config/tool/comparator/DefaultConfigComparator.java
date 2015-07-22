package com.flash.config.tool.comparator;

import java.util.Comparator;

import com.flash.config.domain.Config;

public class DefaultConfigComparator implements Comparator<Config>{

	/**
     * 如果a1小于a2,返回一个负数;如果a1大于a2，返回一个正数;如果他们相等，则返回0;
     */
	@Override
	public int compare(Config o1, Config o2) {
		if(o1.getSort() == null) o1.setSort(0);
		if(o2.getSort() == null) o2.setSort(0);
		if(o1.getSort() > o2.getSort()){
			//说明a1小
			return 1;
		}else if (o1.getSort() == o2.getSort()) {
			//说明相等，按照ID排序
			if(o1.getId() > o2.getId()){
				//按照字母排序，
				return 1;
			}else {
				return -1;
			}
		}else{
			//说明a2在前
			return -1;
		}
	}

}
