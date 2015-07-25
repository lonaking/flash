package com.flash.baseproduct.domain;

import java.io.Serializable;

/**
 * 商品
 * 
 * @author Leon
 * @date 2014年12月24日
 * @since v 1.0
 */
public class ProductGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int parentId;

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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
