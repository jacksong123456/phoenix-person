package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 投资意向 、融资意向等
 */
public class Intention implements Serializable {
	/**
	 * 父类型
	 */
	private Byte parentType;
	/**
	 * 类型id集合 用 , 分割
	 */
	private String typeIds;
	/**
	 * 类型名称集合 用 , 分割
	 */
	private String typeNames;
	/**
	 * 地址
	 */
	private Address address;
	/**
	 * 行业id集合 用 , 分割
	 */
	private String industryIds;
	/**
	 * 行业名称 用 , 分割
	 */
	private String industryNames;
	/**
	 * 投资意向自定义项
	 */
	private List<Basic> custom;

	public Byte getParentType() {
		if (parentType == null)
			return 0;
		return parentType;
	}

	public void setParentType(Byte parentType) {
		this.parentType = parentType;
	}

	public String getTypeIds() {
		if (typeIds == null)
			return "";
		return typeIds;
	}

	public void setTypeIds(String typeIds) {
		this.typeIds = typeIds;
	}

	public String getTypeNames() {
		if (typeNames == null)
			return "";
		return typeNames;
	}

	public void setTypeNames(String typeNames) {
		this.typeNames = typeNames;
	}

	public Address getAddress() {
		if (address == null)
			address = new Address();
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getIndustryIds() {
		if (industryIds == null)
			return "";
		return industryIds;
	}

	public void setIndustryIds(String industryIds) {
		this.industryIds = industryIds;
	}

	public String getIndustryNames() {
		if (industryNames == null)
			return "";
		return industryNames;
	}

	public void setIndustryNames(String industryNames) {
		this.industryNames = industryNames;
	}

	public List<Basic> getCustom() {
		if (custom == null)
			custom = new ArrayList<Basic>();
		return custom;
	}

	public void setCustom(List<Basic> custom) {
		this.custom = custom;
	}
}
