package com.flash.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.flash.base.tool.page.Page;
import com.flash.base.web.dto.GuessShop;
import com.flash.base.web.dto.shop.ShopDto;
import com.flash.base.web.form.shop.ShopAddForm;
import com.flash.base.web.form.shop.ShopUpdateForm;
import com.flash.base.web.tool.comparator.shop.GuessShopDistanceComparator;
import com.flash.base.web.tool.comparator.shop.ShopDtoDistanceComparator;
import com.flash.base.web.tool.query.ShopQuery;
import com.flash.commons.bean.BeanAndDtoTransfer;
import com.flash.commons.earth.EarthUtils;
import com.flash.dao.ShopDao;
import com.flash.domain.Shop;
import com.flash.exception.base.BaseException;
import com.flash.service.ShopService;

@Service("shopService")
public class ShopServiceImpl implements ShopService{

	@Resource(name = "shopDao")
	private ShopDao shopDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);
	
	@Override
	public List<GuessShop> guessShopsNearby(Double lat, Double lng, int cityId) {
		List<Shop> allShop = this.shopDao.findEntitiesByString("cityId", cityId);
		if(null == allShop || allShop.size() == 0)
			return null;
		List<GuessShop> result = new ArrayList<GuessShop>();
		for (Shop shop : allShop) {
			double distance = EarthUtils.getDistance(shop.getLng(),
					shop.getLat(), lng, lat);
			if(distance <= 2000){
				GuessShop guessShop = new GuessShop();
				BeanUtils.copyProperties(shop, guessShop);
				guessShop.setDistance((int) distance);
				result.add(guessShop);
			}
		}
		Collections.sort(result, new GuessShopDistanceComparator());
		return result;
	}

	@Override
	public List<GuessShop> getShopListByCityId(int cityId ,double lng, double lat) {
		List<Shop> allShop = this.shopDao.findEntitiesByString("cityId", cityId);
		if(null == allShop || allShop.size() == 0)
			return null;
		List<GuessShop> result = new ArrayList<GuessShop>();
		for (Shop shop : allShop) {
			GuessShop guessShop = new GuessShop();
			BeanUtils.copyProperties(shop, guessShop);
			if((lng != -1 && lat != -1) && ( lng != 0 && lat != 0) ){
				logger.info("lng = {} , lat = {} , cityId = {}",lng, lat , cityId);
				double distance = EarthUtils.getDistance(shop.getLng(),
						shop.getLat(), lng, lat);
				guessShop.setDistance((int) distance);
			}
			result.add(guessShop);
		}
		Collections.sort(result, new GuessShopDistanceComparator());
		logger.debug("当前线程名称{}",Thread.currentThread().getName());
		return result;
	}

	/**
	 * 条件查询附近超市，地理位置可以为空
	 */
	@Override
	public Page<ShopDto> listByShopQuery(ShopQuery query) {
		Page<Shop> shops = this.shopDao.findPage(query);
		if(null == shops || shops.getTotalCount() == 0) return null;
		logger.info("根据条件查询出超市成功，共有{}条记录符合条件",shops.getTotalCount());
		@SuppressWarnings("deprecation")
		Page<ShopDto> pageResult = new Page<ShopDto>();
		BeanUtils.copyProperties(shops, pageResult, new String[]{"pageData"});
		List<ShopDto> shopDtoList = new ArrayList<ShopDto>();
		for (Shop shop : shops.getPageData()) {
			ShopDto shopDto = new ShopDto();
			BeanUtils.copyProperties(shop, shopDto);
			if(null != query.getOne("lng") && null != query.getOne("lat") && null != shop.getLng() && null != shop.getLat()){
				if(shop.getLng() > 0 && shop.getLat() > 0 ){
					double distance = EarthUtils.getDistance(shop.getLng(),
							shop.getLat(), query.getOne("lng",Double.class), query.getOne("lat",Double.class));
					shopDto.setDistance((int) distance);
				}
			}
			shopDtoList.add(shopDto);
		}
		Collections.sort(shopDtoList, new ShopDtoDistanceComparator());
		pageResult.setPageData(shopDtoList);
		logger.debug("当前线程名称{}",Thread.currentThread().getName());
		return pageResult;
	}

	/**
	 * 添加超市
	 */
	@Override
	public ShopDto addShop(ShopAddForm shopForm) {
		Shop shop = BeanAndDtoTransfer.PutDtoIntoBean(shopForm, Shop.class);
		this.shopDao.saveEntity(shop);
		ShopDto shopDto = BeanAndDtoTransfer.putBeanIntoDto(shop, ShopDto.class);
		return shopDto;
	}

	/**
	 * 更新超市
	 */
	@Override
	public ShopDto updateShop(ShopUpdateForm shopForm) {
		//TODO 判断是否有权限更新此超市信息
		Shop shop = BeanAndDtoTransfer.PutDtoIntoBean(shopForm, Shop.class);
		this.shopDao.updateEntity(shop);
		ShopDto shopDto = BeanAndDtoTransfer.putBeanIntoDto(shop, ShopDto.class);
		return shopDto;
	}

	/**
	 * 删除超市
	 */
	@Override
	public void softDeleteShopById(Integer shopId) throws BaseException {
		//TODO 判断是否有权限删除此超市信息
		Shop shop = this.shopDao.findEntityById(shopId);
		if(null == shop){
			throw new BaseException(300, "超市不存在");
		}else{
			shop.setIsDel(true);
			this.shopDao.updateEntity(shop);
		}
	}
	
}
