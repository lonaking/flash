package com.flash.web.controller.activity;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.activity.service.ActivityService;
import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.activity.ActivityDto;
import com.flash.base.web.form.activity.ActivityAddForm;
import com.flash.base.web.form.activity.ActivityUpdateForm;
import com.flash.base.web.response.BaseResponse;
import com.flash.base.web.tool.query.ActivityQuery;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@Resource(name = "activityService")
	private ActivityService activityService;
	
	/**
	 * 条件查询促销活动信息
	 * @param shopId
	 * @return
	 * @throws BaseException 
	 */
	@RequestMapping(value = "/activity_list_query")
	public @ResponseBody BaseResponse<Page<ActivityDto>> activityListQuery(ActivityQuery query){
		Page<ActivityDto> findPage = this.activityService.queryActivitiesByQuery(query);
		return new BaseResponse<Page<ActivityDto>>(findPage);
	}
	
	/**
	 * 根据id获取促销活动详情	ok
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value = "/activity_info/{activityId}")
	public @ResponseBody BaseResponse<ActivityDto> activityDetail(@PathVariable Long activityId ){
		ActivityDto activityDto = this.activityService.queryActivityDetailById(activityId);
		return new BaseResponse<ActivityDto>(activityDto);
	}
	
	/**
	 * 添加一个促销活动信息
	 * @param activity
	 * @return
	 * TODO　需要添加权限校验
	 */
	@RequestMapping(value = "/activity_add",method = RequestMethod.POST)
	public @ResponseBody BaseResponse<ActivityDto> addActivity(@ModelAttribute ActivityAddForm activity){
		ActivityDto activityDto = this.activityService.addActivity(activity);
		return new BaseResponse<ActivityDto>(activityDto);
	}
	
	/**
	 * 更新一条促销活动信息
	 * @param activityUpdateForm
	 * @return
	 * TODO　需要添加权限校验
	 */
	@RequestMapping(value = "/activity_update" ,method = RequestMethod.POST)
	public @ResponseBody BaseResponse<ActivityDto> updateActivity(ActivityUpdateForm activityUpdateForm){
		ActivityDto activityDto = this.activityService.updateActivity(activityUpdateForm);
		return new BaseResponse<ActivityDto>(activityDto);
	}
}
