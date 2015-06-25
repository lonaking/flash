package com.flash.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.flash.base.dao.impl.RedisAndDbDaoImpl;
import com.flash.dao.AuthDao;
import com.flash.service.redis.RedisService;
import com.flash.ucenter.domain.User;
@Repository("authDao")
public class AuthDaoImpl extends RedisAndDbDaoImpl<User> implements AuthDao{
	private static Logger logger = LoggerFactory.getLogger(AuthDaoImpl.class);

	@Resource(name = "redisService")
	private RedisService redisService;
	@Override
	public User findUserByLoginName(String loginName) {
		User user = this.redisService.hgetObj("users", loginName, User.class);
		if(null != user){
			logger.info("用户登录：从Redis中查询出来名成为{}的用户共{}名",loginName, 1);
			return user;
		}
		List<User> list = findEntitiesByString("loginName", loginName);
		if(null != list && list.size()!=0){
			user = list.get(0);
			logger.info("用户登录：从数据库中查询出来名成为{}的用户共{}名",loginName,list.size());
		}
		return user;
	}
	
	
}
