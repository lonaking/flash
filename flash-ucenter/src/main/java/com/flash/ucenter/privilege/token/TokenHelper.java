package com.flash.ucenter.privilege.token;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flash.exception.ServiceException;
import com.flash.service.redis.RedisService;
import com.flash.ucenter.exception.UcenterServiceException;
import com.flash.ucenter.exception.UcenterServiceExceptionCode;

public class TokenHelper {

	private static Logger logger = LoggerFactory.getLogger(TokenHelper.class);
	protected static RedisService redisService;

	/**
	 * 获取当前登陆用户的Token对象
	 * @author lonaking
	 * @return
	 */
	public static Token getCurrentToken() {
		String key = "SESSION_" + Thread.currentThread().getName();
		Token token = redisService.getObj(key, Token.class);
		logger.info("获取到当前线程的access_token为{},获取到当前登录用户的token为{}", key, token);
		return token;
	}

	public static Boolean checkLogin() throws ServiceException{
		Token currentToken = getCurrentToken();
		if( null == currentToken){
			throw new UcenterServiceException(UcenterServiceExceptionCode.NOT_LOGIN_EXCEPTION,"当前用户没有登陆");
		}
		return true;
	}
	public RedisService getRedisService() {
		return redisService;
	}
	@Resource(name = "redisService")
	public void setRedisService(RedisService redisService) {
		TokenHelper.redisService = redisService;
	}

}
