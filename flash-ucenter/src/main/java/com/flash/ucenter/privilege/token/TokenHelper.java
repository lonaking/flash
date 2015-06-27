package com.flash.ucenter.privilege.token;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flash.service.redis.RedisService;

public class TokenHelper {

	private static Logger logger = LoggerFactory.getLogger(TokenHelper.class);
	protected static RedisService redisService;

	public static Token getCurrentToken() {
		String key = "SESSION_" + Thread.currentThread().getName();
		Token token = redisService.getObj(key, Token.class);
		logger.info("获取到当前线程的access_token为{},获取到当前登录用户的token为{}", key, token);
		return token;
	}

	public RedisService getRedisService() {
		return redisService;
	}
	@Resource(name = "redisService")
	public void setRedisService(RedisService redisService) {
		TokenHelper.redisService = redisService;
	}

}
