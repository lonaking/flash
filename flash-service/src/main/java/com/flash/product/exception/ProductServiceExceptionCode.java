package com.flash.product.exception;

import com.flash.exception.annotation.Desc;
import com.flash.exception.resource.ExceptionCode;

/**
 * 所有Product模块的业务异常码
 * Product 模块的异常信息为5开头 如6401 代表 商品不存在
 * @author lonaking
 */
public enum ProductServiceExceptionCode implements ExceptionCode {
	@Desc(code= 6401 ,msg="商品不存在")
	PRODUCT_IS_NOT_FOUND
}
