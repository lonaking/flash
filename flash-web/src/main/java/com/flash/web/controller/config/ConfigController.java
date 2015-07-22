package com.flash.web.controller.config;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flash.base.web.form.config.ConfigAddForm;
import com.flash.base.web.response.BaseResponse;
import com.flash.commons.bean.BeanAndDtoTransfer;
import com.flash.config.domain.Config;
import com.flash.config.service.ConfigService;

@Controller
@RequestMapping("/config")
public class ConfigController {
	private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);
	
	@Resource(name="configService")
	private ConfigService configService;
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	public BaseResponse<Config> insert(ConfigAddForm configForm){
		Config config = BeanAndDtoTransfer.transOneToAnoter(configForm, Config.class);
		this.configService.saveEntity(config);
		return new BaseResponse<Config>(config);
	}
	
}
