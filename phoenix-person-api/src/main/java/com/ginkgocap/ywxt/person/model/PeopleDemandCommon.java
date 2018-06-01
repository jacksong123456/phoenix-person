package com.ginkgocap.ywxt.person.model;

import java.util.List;

/**
 * 人脉：投资、融资、专家需求、专家身份
 * @author wenbin
 *
 */
public class PeopleDemandCommon extends BaseObject {

	private static final long serialVersionUID = 3691972358064307751L;
	
	/**主键*/
	private Long id;
	/**所属模块： 1-投资，2-融资，3-专家需求，4-专家身份*/
	private Integer parentType;
	/**地址*/
	private PeopleAddress address;
	/**行业*/
	private String industryIds;
	/**行业*/
	private String industryNames;
	/**类型*/
	private String typeIds;
	/**类型*/
	private String typeNames;
	/**附加信息*/
	private String otherInformation;
	/**自定义字段*/
	private List<PeoplePersonalLine> personalLineList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getParentType() {
		return parentType;
	}
	public void setParentType(Integer parentType) {
		this.parentType = parentType;
	}
	public PeopleAddress getAddress() {
		return address;
	}
	public void setAddress(PeopleAddress address) {
		this.address = address;
	}
	public String getIndustryIds() {
		return industryIds;
	}
	public void setIndustryIds(String industryIds) {
		this.industryIds = industryIds;
	}
	public String getIndustryNames() {
		return industryNames;
	}
	public void setIndustryNames(String industryNames) {
		this.industryNames = industryNames;
	}
	public String getTypeIds() {
		return typeIds;
	}
	public void setTypeIds(String typeIds) {
		this.typeIds = typeIds;
	}
	public String getTypeNames() {
		return typeNames;
	}
	public void setTypeNames(String typeNames) {
		this.typeNames = typeNames;
	}
	public String getOtherInformation() {
		return otherInformation;
	}
	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}
	public List<PeoplePersonalLine> getPersonalLineList() {
		return personalLineList;
	}
	public void setPersonalLineList(List<PeoplePersonalLine> personalLineList) {
		this.personalLineList = personalLineList;
	}
	
}
