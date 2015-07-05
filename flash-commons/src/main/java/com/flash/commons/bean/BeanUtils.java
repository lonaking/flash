package com.flash.commons.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils extends org.springframework.beans.BeanUtils{
	/**
	 * 只是将org.springframework.beans.BeanUtils中的copyProperties方法无意义的包装了一下
	 * @param source
	 * @param target
	 * @param ignoreProperties
	 */
	public static void getFat(Object source, Object target, String[] properties, boolean ignore){
		if(ignore == false){
			//就是不包含properties中的属性，则遍历所有的属性，取出properties中的
			Field[] fields = target.getClass().getFields();
			List<String> list = new ArrayList<String>();
			for (Field field : fields) {
				String name = field.getName();
				list.add(name);
			}
			for (String pro : properties) {
				list.remove(pro);
			}
			properties = (String[]) list.toArray();
		}
		org.springframework.beans.BeanUtils.copyProperties(source, target, properties);
	}
	
}
