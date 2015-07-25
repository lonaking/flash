package com.flash.web.controller.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.web.dto.system.VersionUpdateDto;
import com.flash.base.web.form.system.CheckVersionForm;
import com.flash.base.web.response.BaseResponse;
import com.flash.system.service.SystemService;

@Controller
@RequestMapping(value="/sys")
public class SystemController {
	@Resource(name="systemService")
	private SystemService systemService;
	@RequestMapping(value="/check_version",method=RequestMethod.POST)
	public @ResponseBody BaseResponse<VersionUpdateDto> checkVersion(CheckVersionForm versionForm){
		VersionUpdateDto result = this.systemService.checkVersion(versionForm);
		return BaseResponse.success(result);
	}
}
