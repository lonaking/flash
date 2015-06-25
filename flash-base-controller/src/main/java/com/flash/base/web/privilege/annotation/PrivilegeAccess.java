package com.flash.base.web.privilege.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 权限注解 加此注解
 * @author leon
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivilegeAccess {
	
	public String sign() default "";//权限名的唯一标识

	public String name() default "";//权限名
	
	public Class opera() default Class.class;//操作的对象的类型
	
	public String operaId() default "id";//操作对象的id
}
