package com.flash.exception.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Desc {
	public static final int DEFAULT_CODE = 400;
	public String value() default "";
	/**
	 * 异常码
	 * @author lonaking
	 * @return
	 */
	public int code() default DEFAULT_CODE;
	/**
	 * 异常描述
	 * @author lonaking
	 * @return
	 */
	public String msg() default "未知异常";
	
}
