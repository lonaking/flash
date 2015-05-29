package com.flash.service.impl;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.usertype.LoggableUserType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.flash.dao.AuthDao;
import com.flash.service.AuthService;
import com.flash.service.redis.RedisService;
import com.flash.sso.token.Token;
import com.flash.ucenter.domain.Privilege;
import com.flash.ucenter.domain.User;
import com.flash.ucenter.exception.LoginException;
import com.flash.ucenter.exception.NullInputException;
import com.flash.ucenter.exception.PasswordErrorException;
import com.flash.ucenter.exception.UcenterException;
import com.flash.ucenter.exception.UserNotFoundException;
import com.flash.ucenter.exception.login.NotLoginException;
import com.flash.ucenter.exception.login.NullInputOptions;
import com.flash.web.controller.AuthCommond;
@Service("authService")
public class AuthServiceImpl implements AuthService{
	
	private static final String SESSION = "SESSION_";
	
	@Resource(name = "authDao")
	private AuthDao authDao;
	
	@Resource(name = "redisService")
	private RedisService redisService;
	
	@Override
	public Token login(AuthCommond user) throws LoginException{
		if(StringUtils.isBlank(user.getLoginName()))
			throw new NullInputException(NullInputOptions.LOGINNAME, "用户名不能为空");
		if(StringUtils.isBlank(user.getPassword()))
			throw new NullInputException(NullInputOptions.PASSWORD, "密码不能为空");
		User userExists = null;
		try{
			userExists = this.redisService.hgetObj("users", user.getLoginName(), User.class);
		}catch(Exception e){
			userExists = this.authDao.findUserByLoginName(user.getLoginName());
			if(null == userExists)
				throw new UserNotFoundException(user.getLoginName());
			if(user.getPassword().equals(userExists.getPassword()))
				throw new PasswordErrorException("密码错误");
		}
		Set<Privilege> privileges = userExists.getRole().getPrivileges();
		String tokenId = UUID.randomUUID().toString().replace("-", "");
		Token token = new Token(tokenId, userExists, privileges);
		this.redisService.setObj("SESSION_"+tokenId, token, 30 * 60);//存入redis
		//TODO 存入数据库
		return token;
	}


	public User findUser(String username) {
		//return this.authDao.findUserByLoginName(username);
		User user = this.authDao.findEntityById(12);
		return user;
	}


	@Override
	public boolean checkLogin(User user) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Token checkLogin(String tokenId) throws UcenterException {
		Token token = this.redisService.getObj(SESSION + tokenId, Token.class);
		if(null == token) 
			throw new NotLoginException();
		Token currentToken = new Token();
		BeanUtils.copyProperties(token, currentToken);
		if(token.getExpireTime() < System.currentTimeMillis())
			currentToken.setExpireTime(System.currentTimeMillis() + 30 * 6000L);
		currentToken.setUpdateTime(System.currentTimeMillis());
		this.redisService.setObj(tokenId, currentToken);
		this.redisService.setObj(tokenId, currentToken, 60 * 30);
		return token;
	}


	@Override
	public void logout(String tokenId) throws NotLoginException {
		long del = this.redisService.del(SESSION + tokenId);
		if(del == 0){
			throw new NotLoginException("未登录");
		}
		
	}
	
}
