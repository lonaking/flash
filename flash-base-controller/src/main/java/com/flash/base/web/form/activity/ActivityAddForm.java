package com.flash.base.web.form.activity;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

public class ActivityAddForm {
	@JsonProperty("activity_number")
	private String activityNumber;// 活动编号
	private String title;// 标题
	private String intro;// 简介
	private String description;// 描述
	@JsonProperty("start_time")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startTime;
	@JsonProperty("end_time")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endTime;
	private Integer type;// 类型 0促销？ 1买赠？需要定规范
	private Integer num = 0;
	private Integer status;// 状态 需要定规范
	private String pic;
	private String section;//
	
	public String getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(String activityNumber) {
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

}
