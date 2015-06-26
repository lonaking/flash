package com.flash.sso.token;

import com.flash.service.redis.RedisService;

public class TokenHelper {
	
	protected static RedisService redisService;
	
	public static Token getCurrentToken(){
		String key = "SESSION_"+Thread.currentThread().getName();
		Token token = redisService.getObj(key, Token.class);
		return token;
	}
}
