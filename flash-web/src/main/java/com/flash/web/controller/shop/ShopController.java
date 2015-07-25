package com.flash.web.controller.shop;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.GuessShop;
import com.flash.base.web.dto.shop.ShopDto;
import com.flash.base.web.form.shop.ShopAddForm;
import com.flash.base.web.form.shop.ShopUpdateForm;
import com.flash.base.web.response.BaseResponse;
import com.flash.base.web.tool.query.ShopQuery;
import com.flash.commons.earth.EarthUtils;
import com.flash.exception.base.BaseException;
import com.flash.shop.service.ShopService;
import com.flash.ucenter.privilege.annotation.PrivilegeAccess;

@Controller
@RequestMapping("/shop")
public class ShopController {
	private static final Logger logger = LoggerFactory
			.getLogger(ShopController.class);
	
	@Resource(name = "shopService")
	private ShopService shopService;
	
	/**
	 * 猜测用户所在超市,并且可以获取附近的超市列表
	 * @param lat
	 * @param lng
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/shop_guess/{lat}/{lng}/{city}", method = RequestMethod.GET)
	public @ResponseBody BaseResponse<List<GuessShop>> guessShop(@PathVariable double lat, @PathVariable double lng, @PathVariable int city){
		List<GuessShop> result = this.shopService.guessShopsNearby(lat, lng, city);
		return new BaseResponse<List<GuessShop>>(result);
	}
	
	/**
	 * 查看某个地区的所有超市，知道地区id，并且知道坐标
	 * @param city 地区id
	 * @param lng 纬度
	 * @param lat 经度
	 * @return
	 */
	@PrivilegeAccess(sign = "CKDPLB")
	@RequestMapping(value = "/shop_list/{city}/{lng}/{lat}")
	public @ResponseBody BaseResponse<List<GuessShop>> shopListByCity(@PathVariable int city ,@PathVariable double lng,@PathVariable double lat){
		logger.debug("当前线程名称{}" , Thread.currentThread().getName());
		List<GuessShop> result = this.shopService.getShopListByCityId(city, lng, lat);
		return new BaseResponse<List<GuessShop>>(result);
	}
	
	/**
	 * 查看某个地区的所有超市 只知道地区id，不知道坐标
	 * @param city
	 * @return
	 */
	@RequestMapping(value = "/shop_list")
	public @ResponseBody BaseResponse<List<GuessShop>> shopListNearby(@PathVariable(value="cityId") int cityId,ShopQuery query){
		List<GuessShop> result = this.shopService.getShopListByCityId(cityId, 0, 0);
		return new BaseResponse<List<GuessShop>>(result);
	}
	
	/**
	 * 条件查询超市列表
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/shop_list_query",method = RequestMethod.GET)
	public @ResponseBody BaseResponse<Page<ShopDto>> listShop(ShopQuery query){
		if(null == query){
			return new BaseResponse<Page<ShopDto>>(201, "非法的查询", null);
		}
		Page<ShopDto> page = this.shopService.listByShopQuery(query);
		return new BaseResponse<Page<ShopDto>>(page);
	}
	
	/**
	 * 获取超市详细信息
	 */
	@RequestMapping(value="/shop_detail/{shop_id}")
	public @ResponseBody BaseResponse<ShopDto> shopDetail(@PathVariable("shop_id") Integer shopId,@RequestParam(required=false) Double lng,@RequestParam(required=false) Double lat){
		ShopDto shop = this.shopService.getShopInfo(shopId);
		if(null != lng && null != lat){
			double distance = EarthUtils.getDistance(shop.getLng(), shop.getLat(), lng, lat);
			shop.setDistance((int) distance);
		}
		return new BaseResponse<ShopDto>(shop);
	}
	
	/**
	 * 后台添加商铺api
	 * @author lonaking
	 * @param shopForm
	 * @return
	 * TODO 添加的权限
	 */
	@RequestMapping(value = "/shop_add",method = RequestMethod.POST)
	public @ResponseBody BaseResponse<ShopDto> addShop(ShopAddForm shopForm){
		ShopDto shop = this.shopService.addShop(shopForm);
		return new BaseResponse<ShopDto>(shop);
	}
	
	/**
	 * 后台更新超市api
	 * @param shopForm
	 * @return
	 * TODO 添加的权限
	 */
	@RequestMapping(value = "/shop_update",method = RequestMethod.POST)
	public @ResponseBody BaseResponse<ShopDto> updateShop(ShopUpdateForm shopForm){
		ShopDto shop = this.shopService.updateShop(shopForm);
		return new BaseResponse<ShopDto>(shop);
	}
	/**
	 * 删除一个超市api(软删除)
	 * @param shopForm
	 * @return
	 * TODO 删除的权限
	 * @throws BaseException 
	 */
	@RequestMapping(value = "/shop_del",method = RequestMethod.POST)
	public @ResponseBody BaseResponse<?> deleteShopSoft(@RequestParam(value="shop_id") Integer shopId) throws BaseException{
		this.shopService.softDeleteShopById(shopId);
		return new BaseResponse();
	}
	
}
