package com.flash.ucenter.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flash.commons.json.JsonHelper;
import com.flash.service.redis.RedisService;
import com.flash.ucenter.dao.UserDao;
import com.flash.ucenter.domain.User;
import com.flash.ucenter.exception.LoginException;
import com.flash.ucenter.exception.NullInputException;
import com.flash.ucenter.exception.PasswordErrorException;
import com.flash.ucenter.exception.UserNotFoundException;
import com.flash.ucenter.exception.login.NullInputOptions;
import com.flash.ucenter.service.UserService;
import com.flash.ucenter.utils.MD5Utils;

@Service("userService")
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Resource(name = "userDao")
	private UserDao userDao;
	@Resource(name="redisService")
	private RedisService redisService;
	
	private final static String LOGIN = "LOGIN_";
	
	private final static Integer LOGIN_EXPIRE_TIME = 60 * 60 * 2;
	
	@Override
	@Transactional
	public User login(User user) throws LoginException {
		if(StringUtils.isEmpty(user.getLoginName()) || StringUtils.isEmpty(user.getPassword())){
			throw new NullInputException(NullInputOptions.COMMON,"用户名密码不能为空");
		}
		// md5加密
		String oldPassword = user.getPassword();
		User loginUser = this.userDao.findUserByLoginName(user.getLoginName());
		if(loginUser == null){
			throw new UserNotFoundException(user.getLoginName());
		}
		if(!loginUser.getPassword().equals(MD5Utils.md5(oldPassword))){
			throw new PasswordErrorException("密码错误");
		}
//		Set<Shop> shops = loginUser.getShops();
//		if(!shops.isEmpty()){
//			Token token = new Token();
//			token.addPermission(shops.iterator().next(), null);
//			loginUser.setToken(token);
//		}
		
		String json = JsonHelper.transObjToJsonString(loginUser);
		//key 的格式：LOGIN_5_zhangsan_c3284d0f94606de1fd2af172aba15bf3_1531232141
		String key = LOGIN+"_"+"_"+loginUser.getId()+"_"+loginUser.getLoginName()+"_"+MD5Utils.md5(oldPassword)+"_"+System.currentTimeMillis();
		this.redisService.set(key, json, LOGIN_EXPIRE_TIME);
		return loginUser;
	}

	@Override
	public User findUser(String userName) {
		return this.userDao.findUserByLoginName(userName);
	}

	/*
	public User login(LoginUser loginUser) {
		Subject currentUser = SecurityUtils.getSubject();
		boolean isAuth = currentUser.isAuthenticated();
		if(isAuth){
			logger.error("user {} has already signed in", loginUser.getLoginName());
			throw new AuthenticationException("user "+loginUser.getLoginName() + "has already signed in");
		}
		UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getLoginName(), MD5Utils.md5(loginUser.getPassword()));
		token.setRememberMe(loginUser.isRememberMe());
		currentUser.login(token);
		User user = this.findUser(loginUser.getLoginName());
		return user;
	}
	*/
}
