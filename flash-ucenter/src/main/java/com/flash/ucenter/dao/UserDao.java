package com.flash.ucenter.dao;

import com.flash.base.dao.CommonDao;
import com.flash.ucenter.domain.User;

public interface UserDao extends CommonDao<User> {

	/**
	 * 根据用户名和密码查找用户
	 * @param user 包含用户名密码的用户对象
	 * @return
	 */
	public User findUser(User user);

	/**
	 * 根据用户登录名查找用户
	 * @param name 登录名
	 * @return 用户
	 */
	public User findUserByLoginName(String name);
}
