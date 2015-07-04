package com.flash.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.activity.ActivityDto;
import com.flash.base.web.response.BaseResponse;
import com.flash.base.web.tool.query.ActivityQuery;
import com.flash.service.ActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@Resource(name = "activityService")
	private ActivityService activityService;
	
	/**
	 * 获取某个超市的促销信息
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/activity_list_query")
	public @ResponseBody BaseResponse<Page<ActivityDto>> activityListQuery(ActivityQuery query){
		Page<ActivityDto> findPage = this.activityService.queryActivitiesByQuery(query);
		return new BaseResponse<Page<ActivityDto>>(findPage);
	}
	
	@RequestMapping(value = "/activity_info/{activity_id}")
	public @ResponseBody BaseResponse<ActivityDto> activityDetail(@PathVariable(value = "activity_id") Integer activityId ){
		ActivityDto activityDto = this.activityService.queryActivityDetailById(activityId);
		return new BaseResponse<ActivityDto>(activityDto);
	}
}
