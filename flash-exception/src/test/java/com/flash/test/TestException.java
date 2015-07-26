package com.flash.test;

import com.flash.exception.base.BaseExceptions;
import com.flash.exception.utils.ExceptionHelper;

public class TestException {
	public static void main(String[] args) {
		int code = ExceptionHelper.getCode(BaseExceptions.SESSION_VALIDATE_EXCEPTION);
		String msg = ExceptionHelper.getMessage(BaseExceptions.SESSION_VALIDATE_EXCEPTION);
		System.out.println(code);
		System.out.println(msg);
		System.out.println(BaseExceptions.SESSION_VALIDATE_EXCEPTION.name());
	}
}
