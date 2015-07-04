package com.flash.service;

import java.io.Serializable;

import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.activity.ActivityDto;
import com.flash.base.web.form.activity.ActivityAddForm;
import com.flash.base.web.form.activity.ActivityUpdateForm;
import com.flash.base.web.tool.query.ActivityQuery;


public interface ActivityService{

	public Page<ActivityDto> queryActivitiesByQuery(ActivityQuery query);

	public ActivityDto queryActivityDetailById(Serializable activityId);

	public ActivityDto addActivity(ActivityAddForm activityForm);

	public ActivityDto updateActivity(ActivityUpdateForm activityUpdateForm);
	
}
