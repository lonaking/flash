package com.flash.service;

import com.flash.base.web.dto.system.VersionUpdateDto;
import com.flash.base.web.form.system.CheckVersionForm;

/**
 * 系统级别的service
 * @author lonaking
 */
public interface SystemService {

	/**
	 * 查询当前手机app端版本是否更新
	 * @author lonaking
	 * @param versionForm 提交的一个版本参数表
	 * @return
	 */
	public VersionUpdateDto checkVersion(CheckVersionForm versionForm);
}
