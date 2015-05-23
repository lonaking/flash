package com.flash.base.web.dto;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 猜测用户所在超市
 * 
 * @author leon
 *
 */
public class GuessShop {
	private int id;
	private String name;
	private String address;
	@JsonProperty("open_time")
	private String openTime;
	@JsonProperty("close_time")
	private String closeTime;
	private boolean allday = false;
	private int status = 0;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public boolean isAllday() {
		return allday;
	}

	public void setAllday(boolean allday) {
		this.allday = allday;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

}