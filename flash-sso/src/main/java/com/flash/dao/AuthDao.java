package com.flash.dao;

import com.flash.ucenter.domain.User;

public interface AuthDao extends CommonDao<User>{

	public User findUserByLoginName(String username);

}
