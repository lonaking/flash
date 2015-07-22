package com.flash.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.flash.base.web.dto.system.VersionUpdateDto;
import com.flash.base.web.form.system.CheckVersionForm;
import com.flash.config.constant.SystemConstant;
import com.flash.config.domain.Config;
import com.flash.config.service.ConfigService;
import com.flash.service.SystemService;

@Service(value="systemService")
public class SystemServiceImpl implements SystemService{

	@Resource(name = "configService")
	private ConfigService configService;
	@Override
	public VersionUpdateDto checkVersion(CheckVersionForm versionForm) {
		VersionUpdateDto result = new VersionUpdateDto();
		result.setDeviceType(versionForm.getDeviceType());
		result.setForceUpdate(SystemConstant.VERISON_FORCE_UPDATE_FALSE);
		String versionSign = versionForm.getDeviceType() == SystemConstant.DEVICE_TYPE_ANDROID ? SystemConstant.ANDROID_VERSION_SIGN : SystemConstant.IOS_VERSION_SIGN;
		Config versionConfig = this.configService.selectOneConfigBySign(versionSign);
		result.setCurrentVersion(versionConfig.getValue());
		return result;
	}

}
