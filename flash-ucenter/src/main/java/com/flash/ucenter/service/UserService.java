package com.flash.ucenter.service;


import org.apache.shiro.subject.Subject;

import com.flash.ucenter.domain.User;
import com.flash.ucenter.exception.LoginException;
import com.flash.ucenter.exception.NullInputException;

public interface UserService{
	
	/**
	 * 用户登录
	 * @param user 包含loginName 和 password的用户对象
	 * @return 一个用户对象
	 * @throws NullInputException 
	 */
	public User login(User user) throws LoginException;
	
	public User findUser(String userName);

	public void login(User user, Subject currentUser);
	
}
