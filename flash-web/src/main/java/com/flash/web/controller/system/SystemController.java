package com.flash.web.controller.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.web.dto.system.VersionUpdateDto;
import com.flash.base.web.form.system.CheckVersionForm;
import com.flash.base.web.form.system.FeedBackForm;
import com.flash.base.web.response.BaseResponse;
import com.flash.system.service.SystemService;
import com.flash.web.framework.WebApiBaseController;

@Controller
@RequestMapping(value="/sys")
public class SystemController extends WebApiBaseController{
	@Resource(name="systemService")
	private SystemService systemService;
	
	/**
	 * 版本检测
	 * @author lonaking
	 * @param versionForm 用户的客户端版本
	 * @return
	 */
	@RequestMapping(value="/check_version",method=RequestMethod.POST)
	public @ResponseBody BaseResponse<VersionUpdateDto> checkVersion(CheckVersionForm versionForm){
		VersionUpdateDto result = this.systemService.checkVersion(versionForm);
		return BaseResponse.success(result);
	}
	/**
	 * 意见反馈
	 * @author lonaking
	 * @param feedBackForm
	 * @return
	 */
	@RequestMapping(value= "/feedback", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<String> feedBack(FeedBackForm feedBackForm) {
		this.systemService.saveFeedBackInfo(feedBackForm);
		return null;
	}
}
