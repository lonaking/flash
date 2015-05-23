package com.flash.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flash.dao.AuthDao;
import com.flash.service.AuthService;
import com.flash.service.redis.RedisService;
import com.flash.ucenter.domain.User;
@Service("authService")
public class AuthServiceImpl implements AuthService{
	
	@Resource(name = "authDao")
	private AuthDao authDao;
	
	@Resource(name = "redisService")
	private RedisService redisService;
	
	@Override
	public User login(User user){
		User userExists = null;
		try{
			userExists = this.redisService.hgetObj("users", user.getLoginName(), User.class);
		}catch(Exception e){
			userExists = this.authDao.findUserByLoginName(user.getLoginName());
		}
		if(null == userExists){
			userExists = this.authDao.findUserByLoginName(user.getLoginName());
		}
		if(null != userExists){
			this.redisService.setObj("SESSION_"+userExists.getLoginName(), userExists);
			this.redisService.expire("SESSION_"+userExists.getLoginName(), 30*60*1000);
		}
		return userExists;
	}


	public User findUser(String username) {
		//return this.authDao.findUserByLoginName(username);
		User user = this.authDao.findEntityById(12);
		return user;
	}

	@Override
	public boolean checkLogin(User user) {
		
		
		return false;
	}
	
}
