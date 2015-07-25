package com.flash.product.domain;

import java.math.BigDecimal;

/**
 * 商场的商品对象 包含一个商品／商品分组／
 * 
 * @author lonaking
 */
public class ShopProduct {
	private Integer id;
	private BigDecimal shopPrice;// 本商场的价格
	private Boolean onsale;
	private String section; // 区域 如食品区／菜品区
	private Integer floor;// 楼层
	private String storage;// 货架
	private Integer storageX;// 货架横排
	private String storageY;// 货架竖排
	private Integer baseProductId;
	private Integer shopId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}

	public Boolean getOnsale() {
		return onsale;
	}

	public void setOnsale(Boolean onsale) {
		this.onsale = onsale;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public Integer getStorageX() {
		return storageX;
	}

	public void setStorageX(Integer storageX) {
		this.storageX = storageX;
	}

	public String getStorageY() {
		return storageY;
	}

	public void setStorageY(String storageY) {
		this.storageY = storageY;
	}

	public Integer getBaseProductId() {
		return baseProductId;
	}

	public void setBaseProductId(Integer baseProductId) {
		this.baseProductId = baseProductId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

}
