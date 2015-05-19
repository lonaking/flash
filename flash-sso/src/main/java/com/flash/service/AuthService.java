package com.flash.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flash.dao.AuthDao;
import com.flash.domain.User;
import com.flash.service.redis.RedisService;
@Service("authService")
public class AuthService {
	
	@Resource(name = "authDao")
	private AuthDao authDao;
	
	@Resource(name = "redisService")
	private RedisService redisService;
	
	public User login(User user){
		User userExists = null;
		try{
			userExists = this.redisService.hget("users", user.getLoginName(), User.class);
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
		return this.authDao.findUserByLoginName(username);
	}
	
}
