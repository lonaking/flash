package com.flash.base.web.dto;


/**
 * 此dto用于商品信息传输
 * 
 * @author leon
 *
 */
public class ShopProductInfo {
	private int id;
	private String name;
	private String barcode;// 条码
	private String title;
	private double price;
	private String intro;
	private String description;
	private String pic;
	private int weight;// 重量
	private String weightUnit;
	private int capacity;
	private String capacityUnit;
	private String unit;
	private int status;// 0下架 1 上架
	private double shopPrice;// 本商场的价格
	private boolean onsale;
	private int floor;// 楼层
	private String storage;// 货架
	private int storageX;// 货架横排
	private char storageY;// 货架竖排

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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
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

	public double getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(double shopPrice) {
		this.shopPrice = shopPrice;
	}

	public boolean isOnsale() {
		return onsale;
	}

	public void setOnsale(boolean onsale) {
		this.onsale = onsale;
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

	public char getStorageY() {
		return storageY;
	}

	public void setStorageY(char storageY) {
		this.storageY = storageY;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getCapacityUnit() {
		return capacityUnit;
	}

	public void setCapacityUnit(String capacityUnit) {
		this.capacityUnit = capacityUnit;
	}

}
