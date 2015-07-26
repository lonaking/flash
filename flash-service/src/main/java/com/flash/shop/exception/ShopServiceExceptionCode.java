package com.flash.shop.exception;

import com.flash.exception.annotation.Desc;
import com.flash.exception.resource.ExceptionCode;

/**
 * 所有Shop模块的业务异常码
 * Shop 模块的异常信息为5开头 如5401 代表 店铺不存在
 * @author lonaking
 */
public enum ShopServiceExceptionCode implements ExceptionCode {
	@Desc(code = 5401, msg = "超市不存在")
	SHOP_IS_NOT_FOUND
}
