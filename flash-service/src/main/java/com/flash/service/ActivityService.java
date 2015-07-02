package com.flash.service;

import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.activity.ActivityDto;
import com.flash.base.web.tool.query.ActivityQuery;


public interface ActivityService{

	public Page<ActivityDto> queryActivitiesByQuery(ActivityQuery query);
	
}
