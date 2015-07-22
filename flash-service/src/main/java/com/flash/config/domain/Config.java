package com.flash.config.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 网站配置信息
 * 
 * @author Leon
 * @date 2014年12月28日
 * @since v 1.0
 */
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 英文名
	 */
	private String sign;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 类型 '1':
	 * 'boolean','2':'array','3':'list','4':'string','5':'int','6':'double','7':'checkbox','8':'textarea','9':'password,10:radi
	 * o '
	 */
	private Integer type;
	/**
	 * 如果是checkBox或者list array 使用json存放
	 */
	private String listJson;

	private Map<String, String> mapData;

	/**
	 * list或者map数据
	 */
	private List listData;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 模式
	 */
	private String module;
	/**
	 * 是否只读
	 */
	private boolean readOnly;
	/**
	 * 帮助指向链接
	 */
	private String helpUrl;
	/**
	 * 是否快捷打开模式 否为0
	 */
	private int fastOpen;

	private boolean allowEmpty;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getHelpUrl() {
		return helpUrl;
	}

	public void setHelpUrl(String helpUrl) {
		this.helpUrl = helpUrl;
	}

	public int getFastOpen() {
		return fastOpen;
	}

	public void setFastOpen(int fastOpen) {
		this.fastOpen = fastOpen;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getListJson() {
		return listJson;
	}

	public void setListJson(String listJson) {
		this.listJson = listJson;
	}

	public boolean getAllowEmpty() {
		return allowEmpty;
	}

	public void setAllowEmpty(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}

	public Map<String, String> getMapData() {
		return mapData;
	}

	public void setMapData(Map<String, String> mapData) {
		this.mapData = mapData;
	}

	public List getListData() {
		return listData;
	}

	public void setListData(List listData) {
		this.listData = listData;
	}

	@Override
	public String toString() {
		return "Config [id=" + id + ", name=" + name + ", sign=" + sign
				+ ", value=" + value + ", description=" + description
				+ ", type=" + type + ", listJson=" + listJson + ", mapData="
				+ mapData + ", listData=" + listData + ", sort=" + sort
				+ ", module=" + module + ", readOnly=" + readOnly
				+ ", helpUrl=" + helpUrl + ", fastOpen=" + fastOpen
				+ ", allowEmpty=" + allowEmpty + "]";
	}

	
}
