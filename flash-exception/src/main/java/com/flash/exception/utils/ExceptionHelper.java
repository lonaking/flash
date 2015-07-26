package com.flash.exception.utils;

import java.lang.reflect.Field;

import com.flash.exception.annotation.Desc;
import com.flash.exception.base.BaseExceptions;
import com.flash.exception.resource.ExceptionCode;

public class ExceptionHelper {
	
	public static final int DEFAULT_EXCEPTION_CODE = 400;
	/**
	 * 获取异常描述信息
	 * @author lonaking
	 * @param code
	 * @return
	 */
	public static String getMessage(ExceptionCode code) {
		if (code == null)
			code = BaseExceptions.UNKNOWN_EXCEPTION;
		try {
			Field field = code.getClass().getField(code.name());
			Desc descAnnotation = field.getAnnotation(Desc.class);
			if (descAnnotation == null)
				return code.name();
			else if (descAnnotation.msg() == null || "".equals(descAnnotation.msg().trim()))
				return code.name();
			else
				return descAnnotation.msg();
		} catch (Throwable e) {
			return code.name();
		}
	}

	/**
	 * 获取异常状态码
	 * @author lonaking
	 * @param code
	 * @return
	 */
	public static int getCode(ExceptionCode code) {
		if (code == null)
			code = BaseExceptions.UNKNOWN_EXCEPTION;
		
		try {
			Field field = code.getClass().getField(code.name());
			Desc descAnnotation = field.getAnnotation(Desc.class);
			if (descAnnotation == null)
				return DEFAULT_EXCEPTION_CODE;
			else if (descAnnotation.code() == 0 )
				return DEFAULT_EXCEPTION_CODE;
			else
				return descAnnotation.code();
		} catch (Throwable e) {
			return DEFAULT_EXCEPTION_CODE;
		}
	}
}
