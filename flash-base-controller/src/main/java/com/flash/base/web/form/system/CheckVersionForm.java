package com.flash.base.web.form.system;

public class CheckVersionForm {
	private String version;// 版本号
	private Integer deviceType;// 类型 1:android 2 ios
	private String deviceId;//产品序列号

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

}
