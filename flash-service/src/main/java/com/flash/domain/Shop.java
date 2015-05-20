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
	/**
	 * 店铺id
	 */
	private Integer id;
	/**
	 * 店铺名
	 */
	private String name;
	/**
	 * 店铺简介
	 */
	private String intro;
	/**
	 * 联系人姓名，并非用户姓名
	 */
	private String linkManName;
	/**
	 * 联系人手机
	 */
	private String linkMobile;
	/**
	 * 店铺电话
	 */
	private String tel;
	/**
	 * 店铺邮件
	 */
	private String email;
	/**
	 * 店铺地址
	 */
	private String addr;
	/**
	 * 店铺状态
	 */
	private int status;
	/**
	 * 店铺图标
	 */
	private String headPic;
	/**
	 * 店铺模式
	 */
	private int pattern;
	/**
	 * 店铺点击量
	 */
	private int visitCounts;
	/**
	 * 是否删除，是否在回收站
	 */
	private boolean isDel;
	/**
	 * 是否推荐 1推荐 0默认
	 */
	private int recommend;
	/**
	 * 店铺拼音首字母
	 */
	private String pin;
	/**
	 * 店铺描述
	 */
	private String description;
	/**
	 * 店铺公告
	 */
	private String notice;
	/**
	 * 店铺类别
	 */
	private int type;
	/**
	 * 店铺所在地经纬度 经度longitude
	 */
	private double lng;
	/**
	 * 店铺所在地经纬度 纬度latitude
	 */
	private double lat;
	/**
	 * 店铺添加时间
	 */
	private long createTime;
	/**
	 * 更新时间
	 */
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public int getVisitCounts() {
		return visitCounts;
	}

	public void setVisitCounts(int visitCounts) {
		this.visitCounts = visitCounts;
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
}
