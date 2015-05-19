package com.flash.dao.impl;

import org.springframework.stereotype.Repository;

import com.flash.dao.AuthDao;
import com.flash.domain.User;
@Repository("authDao")
public class AuthDaoImpl extends CommonDaoImpl<User> implements AuthDao{

	@Override
	public User findUserByLoginName(String username) {
		return this.findEntryByString("loginName", username);
	}
	
	
}
