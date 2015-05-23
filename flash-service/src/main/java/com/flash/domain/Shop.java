package com.flash.domain;

import java.io.Serializable;

/**
 * 店铺
 * 
 * @author Leon
 * @since V1.0 Oct 21, 2014 7:53:11 PM
 */
public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String intro;
	private String linkManName;
	private String linkMobile;
	private String tel;
	private String email;
	private String address;
	private int cityId;
	private String openTime;
	private String closeTime;
	private int status;
	private String headPic;
	private int pattern;
	private boolean isDel;
	private int recommend;
	private String pin;
	private String description;
	private String notice;
	private int type;
	private double lng;
	private double lat;
	private long createTime;
	private long updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getLinkManName() {
		return linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	public String getLinkMobile() {
		return linkMobile;
	}

	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public int getPattern() {
		return pattern;
	}

	public void setPattern(int pattern) {
		this.pattern = pattern;
	}

	public boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(boolean isDel) {
		this.isDel = isDel;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
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

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
}
