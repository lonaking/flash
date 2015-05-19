package com.flash.domain;
/**
 * 商场的商品对象 包含一个商品／商品分组／
 * @author lonaking
 */
public class ShopProduct {
	private int productId;
	private int shopId;
	private int GroupId;
	private double shopPrice;//本商场的价格
	private int floor;// 楼层
	private String storage;// 货架
	private int storageX;// 货架横排
	private String storageY;// 货架竖排
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public int getGroupId() {
		return GroupId;
	}
	public void setGroupId(int groupId) {
		GroupId = groupId;
	}
	public double getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(double shopPrice) {
		this.shopPrice = shopPrice;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public int getStorageX() {
		return storageX;
	}
	public void setStorageX(int storageX) {
		this.storageX = storageX;
	}
	public String getStorageY() {
		return storageY;
	}
	public void setStorageY(String storageY) {
		this.storageY = storageY;
	}
}
