package com.flash.sso.domain;

import com.flash.sso.token.Token;
import com.flash.ucenter.domain.User;

public class SsoSession {
	private int id;
	private String tokenId;
	private User user;
	private long expireTime;
	private long createTime;
	private long updateTime;
	private String loginIp;

	public SsoSession() {
	}

	public SsoSession(Token token) {
		super();
		this.tokenId = token.getTokenId();
		this.user = token.getUser();
		this.expireTime = token.getExpireTime();
		this.createTime = token.getCreateTime();
		this.updateTime = System.currentTimeMillis();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

}
