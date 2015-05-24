package com.flash.domain;

/**
 * 商场的商品对象 包含一个商品／商品分组／
 * 
 * @author lonaking
 */
public class ShopProduct {
	private int id;
	private double shopPrice;// 本商场的价格
	private boolean onsale;
	private int floor;// 楼层
	private String storage;// 货架
	private int storageX;// 货架横排
	private char storageY;// 货架竖排
	
	private Product product;
	private Shop shop;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
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

	public boolean getOnsale() {
		return onsale;
	}

	public void setOnsale(boolean onsale) {
		this.onsale = onsale;
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

	public char getStorageY() {
		return storageY;
	}

	public void setStorageY(char storageY) {
		this.storageY = storageY;
	}

}
