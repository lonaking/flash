package com.flash.ucenter.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限
 * 
 * @author Lee
 */
public class Privilege implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;// parent id 父节点id
	private String name;// 权限名
	private String sign;// 代号，权限用这个
	private String icon;// 图标
	private String url;// 指向的url
	private String target;// url对应的目标
	private boolean isParent;// 是不是父节点
	private Integer type;// 1表示菜单 2表示操作 的元素
	private boolean checked = false;// 是否选中，不放数据库
	/* 权限：角色 一对多 一个权限对应多个角色 */
	private Set<Role> roles = new HashSet<Role>();

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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public boolean getIsChecked() {
		return checked;
	}

	public void setIsChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Privilege other = (Privilege) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
