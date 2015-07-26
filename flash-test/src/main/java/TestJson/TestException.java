package TestJson;

import com.flash.exception.utils.ExceptionHelper;
import com.flash.shop.exception.ShopServiceExceptionCode;

public class TestException {
	public static void main(String[] args) {
		ExceptionHelper.getCode(ShopServiceExceptionCode.SHOP_IS_NOT_FOUND);
	}
}
