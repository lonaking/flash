package com.flash.domain;

import java.io.Serializable;

/**
 * 地区
 * 
 * @author Leon
 * @date 2014年12月27日
 * @since v 1.0
 */
public class Area implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;
	private String name;
	/**
	 * 行政范围 0 国家 1 省份 2城市 3县区 4直辖市
	 */
	private Integer scope;
	private String description;
	/**
	 * 地区排序，从1-9这样排序，数字越大，越靠后
	 */
	private Integer sort;
	/**
	 * 地区拼音首字母
	 */
	private String pin;
	/**
	 * 地区拼音
	 */
	private String pinyin;
	private Integer depth;
	private Boolean isRoot;
	private String pic;
	private Double lng;
	private Double lat;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

}
