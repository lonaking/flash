package com.flash.commons.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class BeanAndDtoTransfer {
	/**
	 * 将dto转换为Bean
	 * @author lonaking
	 * @param dto
	 * @param cla
	 * @deprecated 请使用新的方法@see transOneToAnoter
	 * @return
	 */
	public static <D, B> B PutDtoIntoBean(D dto , Class<B> cla){
			B bean;
			try {
				bean = cla.newInstance();
				BeanUtils.copyProperties(dto, bean);
				return bean;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * 将Bean转换为Dto
	 * @author lonaking
	 * @param bean
	 * @param cla
	 * @deprecated 请使用新的方法@see transOneToAnoter
	 * @return
	 */
	public static <B, D> D putBeanIntoDto(B bean, Class<D> cla){
		try {
			D dto = cla.newInstance();
			BeanUtils.copyProperties(bean, dto);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将一个Bean(或者dto)的列表中的所有元素复制到另一哥Dto(或者bean)的列表中
	 * @author lonaking
	 * @param one 一个已知列表
	 * @param cla 要复制到的列表的类型
	 * @return
	 */
	public static <B, D> List<D> transOneListToAnoterList(List<B> one, Class<D> cla){
		List<D> listD = new ArrayList<D>();
		for (B b : one) {
			D d = BeanAndDtoTransfer.transOneToAnoter(b, cla);
			listD.add(d);
		}
		return listD;
	}
	/**
	 * 快捷复制对象到领一个对象 只能复制字段名相同的字段 底层使用copyProperties()
	 * @author lonaking
	 * @param bean
	 * @param cla
	 * @return
	 */
	public static <B ,D> D transOneToAnoter(B bean, Class<D> cla){
		try {
			D dto = cla.newInstance();
			BeanUtils.copyProperties(bean, dto);
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
