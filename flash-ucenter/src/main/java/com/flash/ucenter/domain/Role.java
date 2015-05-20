package com.flash.ucenter.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 * 
 * @author Lee
 */
public class Role implements Serializable {

	private static final long serialVersionUID = -588783803042556935L;
	private Integer id;
	private Integer pid;
	private boolean isParent;
	private String name;
	private Set<Privilege> privileges = new HashSet<Privilege>();
	private Set<User> users = new HashSet<User>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
