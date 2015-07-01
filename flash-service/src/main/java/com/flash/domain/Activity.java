package com.flash.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 活动
 * 
 * @author lonaking
 */
public class Activity {
	private Long id;
	private Long activityNumber;//活动编号
	private String title;//标题
	private String intro;// 简介
	private String description;// 描述
	private Date startTime;
	private Date endTime;
	private Integer type;// 类型 促销？ 买赠？需要定规范
	private Integer num = 0;
	private Integer status;// 状态 需要定规范
	private String pic;
	private String section;//
	//TODO 可能要添加位置 x y
	private Set<ShopProduct> products = new HashSet<ShopProduct>();
	private Shop shop;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(Long activityNumber) {
		this.activityNumber = activityNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Set<ShopProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<ShopProduct> products) {
		this.products = products;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
