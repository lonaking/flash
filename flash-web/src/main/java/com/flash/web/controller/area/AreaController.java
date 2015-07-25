package com.flash.web.controller.area;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flash.area.service.AreaService;
import com.flash.base.web.response.BaseResponse;

@Controller
@RequestMapping(value = "/area")
public class AreaController {

	@Resource(name = "areaService")
	private AreaService areaService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public BaseResponse<?> add() {

		return BaseResponse.success(null);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public BaseResponse<?> update() {

		return BaseResponse.success(null);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public BaseResponse<?> areas() {
		return BaseResponse.success(null);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public BaseResponse<?> search() {
		return BaseResponse.success(null);
	}
}
