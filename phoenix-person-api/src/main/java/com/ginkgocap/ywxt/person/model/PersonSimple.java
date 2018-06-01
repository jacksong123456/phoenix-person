package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PersonSimple implements Serializable {
	
	private static final long serialVersionUID = 8450933692823537008L;

	public static enum OpType {
		del, upsert
	}

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 人的ID
	 */
	private Long personid;

	/**
	 * 人的类型(1-用户;2-人脉)
	 */
	private Short persontype;
	public static final Short PERSON_TYPE_USER = 1;
	public static final Short PERSON_TYPE_PEOPLE = 2;

	/**
	 * 姓
	 */
	private String name1;

	/**
	 * 名
	 */
	private String name2;

	/**
	 * 名称全拼拼音
	 */
	private String pinyin;

	/**
	 * 创建时间
	 */
	private Date createtime;

	/**
	 * 公司
	 */
	private String company;

	/**
	 * 职位
	 */
	private String position;

	/**
	 * 头像路径
	 */
	private String picpath;

	/**
	 * 区域代码
	 */
	private Long regionId;

	/**
	 * 分类代码ID
	 */
	private Long typeId;

	/**
	 * 职业代码ID
	 */
	private Long careerId;

	/**
	 * 电话
	 */
	private String phone;

	private Date ctime;

	// 国外的洲或国内的省
	private String country;
	// 市
	private String city;
	// 县
	private String county;
	// 分类 名称
	private String typeName;
	
	//性别
	private int gender;
	
	/**
	 * 一级行业方向id
	 * */
	private Long firstIndustryDirectionId;
	
	/**
	 * 行业方向id
	 * */
	private Long secondIndustryDirectionId;
	
	public String getCountry() {
		if (country == null) {
			country = "";
		}
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		if (city == null) {
			city = "";
		}
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		if (county == null) {
			county = "";
		}
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTypeName() {
		if (typeName == null) {
			typeName = "";
		}
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 创建人ID
	 */
	private Long creator;

	private List<Map<String, String>> listMobilePhone;

	private List<Map<String, String>> listFixedPhone;

	private String opType;

	public String getOpType() {
		if (opType == null)
			return "";
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public List<Map<String, String>> getListMobilePhone() {
		if (listMobilePhone == null)
			listMobilePhone = new ArrayList<Map<String, String>>();
		return listMobilePhone;
	}

	public void setListMobilePhone(List<Map<String, String>> listMobilePhone) {
		this.listMobilePhone = listMobilePhone;
	}

	public List<Map<String, String>> getListFixedPhone() {
		if (listFixedPhone == null)
			listFixedPhone = new ArrayList<Map<String, String>>();
		return listFixedPhone;
	}

	public void setListFixedPhone(List<Map<String, String>> listFixedPhone) {
		this.listFixedPhone = listFixedPhone;
	}

	public Long getId() {
		if (id == null)
			return 0L;
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonid() {
		if (personid == null)
			return 0L;
		return personid;
	}

	public void setPersonid(Long personid) {
		this.personid = personid;
	}

	public Short getPersontype() {
		if (persontype == null)
			return 0;
		return persontype;
	}

	public void setPersontype(Short persontype) {
		this.persontype = persontype;
	}

	public String getName1() {
		if (name1 == null)
			return "";
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		if (name2 == null)
			return "";
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getPinyin() {
		if (pinyin == null)
			return "";
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getCompany() {
		if (company == null)
			return "";
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		if (position == null)
			return "";
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPicpath() {
		if (picpath == null)
			return "";
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public Long getRegionId() {
		if (regionId == null)
			return 0L;
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getTypeId() {
		if (typeId == null)
			return 0L;
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getCareerId() {
		if (careerId == null)
			return 0L;
		return careerId;
	}

	public void setCareerId(Long careerId) {
		this.careerId = careerId;
	}

	public String getPhone() {
		if (phone == null)
			return "";
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Long getFirstIndustryDirectionId() {
		return firstIndustryDirectionId;
	}

	public void setFirstIndustryDirectionId(Long firstIndustryDirectionId) {
		this.firstIndustryDirectionId = firstIndustryDirectionId;
	}

	public Long getSecondIndustryDirectionId() {
		return secondIndustryDirectionId;
	}

	public void setSecondIndustryDirectionId(Long secondIndustryDirectionId) {
		this.secondIndustryDirectionId = secondIndustryDirectionId;
	}

}