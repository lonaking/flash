/**
 * 
 */
package com.flash.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商品信息
 * 
 * @author Leon
 * @since V1.0 Oct 22, 2014 1:17:16 AM
 */
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 商品id
	 */
	private int id;
	/**
	 * 商品名
	 */
	private String name;
	private String barCode;//条形码
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 定价
	 */
	private double price;
	/**
	 * 简介
	 */
	private String intro;
	/**
	 * 详情
	 */
	private String description;
	/**
	 * 图片
	 */
	private String pic;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 0下架 1 上架
	 */
	private int status;
	/**
	 * 是否删除到回收站
	 */
	private boolean isDel;
	/* 所属分组， 商品：分组 多对一 */
	private ProductGroup group;

	/**
	 * 添加时间
	 */
	private Timestamp addTime;
	/**
	 * 更新时间
	 */
	private Timestamp updateTime;

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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isDel() {
		return isDel;
	}

	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}

	public ProductGroup getGroup() {
		return group;
	}

	public void setGroup(ProductGroup group) {
		this.group = group;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
}
