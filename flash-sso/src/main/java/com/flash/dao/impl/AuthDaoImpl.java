package com.flash.dao.impl;

import org.springframework.stereotype.Repository;

import com.flash.dao.AuthDao;
import com.flash.ucenter.domain.User;
@Repository("authDao")
public class AuthDaoImpl extends CommonDaoImpl<User> implements AuthDao{

	@Override
	public User findUserByLoginName(String username) {
		User exampleEntity = new User();
		exampleEntity.setLoginName(username);
		User user = this.findEntryByString("loginName", username);
		return user;
	}
	
	
}
