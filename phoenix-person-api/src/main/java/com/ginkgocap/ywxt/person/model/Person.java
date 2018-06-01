package com.ginkgocap.ywxt.person.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Person implements Serializable {
	private static final long serialVersionUID = 6123642579309829629L;
	public static final int PARENTTYPE_CONTACT = 1;
	public static final String TYPE_VIRTUAL_USER = "0";//个人用户
	public static final String TYPE_VIRTUAL_PERSON = "1";//人脉

	/**
	 * 大数据记录id：大数据从外网抓取的人脉数据在保存时，此字段有值，且createUserId为0
	 */
	private String bigId;

	/**
	 * 荣誉
	 */
	private List<Honouexp> honouexps;
	/**
	 * 作品
	 */
	private List<ProductExp> productexps;

	public String getCountry() {
		if (country == null) {
			country = "0";
		}
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getVirtual() {
		if (StringUtils.isBlank(this.virtual)) {
			if (null != this.getFromUserId() && null != this.getCreateUserId()
					&& this.getCreateUserId().equals(this.getFromUserId())
					&& this.getCreateUserId().longValue() > 0) {
				virtual = "0";//0-用户；1-人脉
			} else {
				virtual = "1";
			}
		}
		return virtual;
	}

	public void setVirtual(String virtual) {
		this.virtual = virtual;
	}

	public Long getFromPersonId() {
		return fromPersonId;
	}

	public void setFromPersonId(Long fromPersonId) {
		this.fromPersonId = fromPersonId;
	}

	public static enum ModelType {
		BASIC_INFO(10), CONTACT(11), SITUATION(12), INVESTMENT_INTENTION(13), FINANCING_INTENTION(
				14), EXPERTS_DEMAND(15), EXPERTS_IDENTITY(16), EDUCAT_EXPERIEN(
				17), WORK_EXPERIEN(18), SOCIAL_ACTIVITIES(19), CUSTOM(99);
		public Integer code;

		ModelType(Integer code) {
			this.code = code;
		}
	}

	/**
	 * 人脉对象ID
	 */
	private Long id;

	public Long getId() {
		if (id == null)
			return 0L;
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人ID
	 */
	private Long createUserId = 0L;
	/**
	 * 转换用户ID：从用户转为人脉时，此字段存原来的用户的id；此人脉对象保存的是用户的个人资料时，此字段和创建人ID相同
	 */
	private Long fromUserId = 0L;
	/**
	 * 将其他用户通过大乐或转发等操作分享的人脉保存为自己的人脉时，此字段保存原来的人脉id
	 */
	private Long fromPersonId = 0L;
	/**
	 * 原始用户ID
	 */
//	private Long oldPeopleId;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 职业代码ID
	 */
	private Long careerId = 0L;
	/**
	 * 汉语拼音
	 */
	private String pinyin;
	/**
	 * 人脉附件ID
	 */
	private String taskId;
	/**
	 * 拼音缩写
	 */
	private String nameIndex;
	/**
	 * 拼音首字母
	 */
	private String nameFirst;
	/**
	 * 性别 1-男，2-女，3-未知
	 */
	private Byte gender = 3;
	/**
	 * 分类
	 */
	private String peopleType;
	/**
	 * 分类代码ID
	 */
	private Integer typeId = 0;
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 公司
	 */
	private String company;
	/**
	 * 现住地址
	 */
	private String address;
	/**
	 * 人脉头像地址
	 */
	private String portrait;

	/**
	 * 0-个人用户；1-人脉
	 */
	private String virtual;

	private String country = "0"; // 0-国内；1-国外
	/**
	 * 所在国家：国外时表示洲，国内时表示省
	 */
	private String locationCountry;
	/**
	 * 所在城市：国外：国家，国内：市
	 */
	private String locationCity;
	/**
	 * 国内：县
	 */
	private String locationCounty;
	/**
	 * 区域代码
	 */
	private Long regionId = 0L;
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 一级行业方向
	 * */
	private String firstIndustryDirection;
	
	/**
	 * 一级行业方向id
	 * */
	private long firstIndustryDirectionId;
	
	/**
	 * 行业方向
	 * */
	private String secondIndustryDirection;
	
	/**
	 * 行业方向id
	 * */
	private long secondIndustryDirectionId;
	
	/**
	 * 自定义项
	 */
	private List<Basic> customTagList;
	/**
	 * 个人情况
	 */
	private List<PersonalInformation> personalInformationList;
	/**
	 * 投资意向
	 */
	private List<Intention> investmentdemandList;
	/**
	 * 融资意向
	 */
	private List<Intention> financingdemandList;
	/**
	 * 专家需求
	 */
	private List<Intention> expertdemandList;
	/**
	 * 专家身份
	 */
	private List<Intention> expertIdentityList;
	/**
	 * 教育
	 */
	private List<Education> educationList;
	/**
	 * 工作经历
	 */
	private List<WorkExperience> workExperienceList;
	/**
	 * 社会活动
	 */
	private List<SocialActivity> socialActivityList;
	/**
	 * 联系方式
	 */
	private List<Basic> contactInformationList;
	/**
	 * 人脉的名称
	 */
	private List<PersonName> peopleNameList;
	
	/**
	 * 人脉的名称
	 */
	private List<CustomTemp> customTemp;
	
	/**
	 * 人脉权限
	 */
	private PermIds permIds;
	
	
	/**
	 * 人脉权限
	 */
	private Map permissions;
	
	/**
	 * 通讯录人脉区分
	 */
	private int mailType=0; 
	
	// 关联的对象
	private ASSORPOK asso;

	public ASSORPOK getAsso() {
		if (asso == null) {
			asso = new ASSORPOK();
		}
		return asso;
	}

	public void setAsso(ASSORPOK asso) {
		this.asso = asso;
	}

	public PermIds getPermIds() {
		
		return permIds;
	}

	public void setPermIds(PermIds permIds) {
		this.permIds = permIds;
	}

	public String getModelType() {
		if (modelType == null)
			return "";
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	/**
	 * @return 是否是系统用户对应的用于资料保存的人脉对象
	 */
	public boolean isUser() {
		if ("0".equals(this.getVirtual()))
			return true;//0表示用户；1表示人脉
		return false;
	}

	/**
	 * 权限模块
	 */
	private String modelType;

	public Long getCreateUserId() {
//		if (createUserId == null)
//			return 0L;
		return createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getFromUserId() {
		if (fromUserId == null)
			return 0L;
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}
/*
	public Long getOldPeopleId() {
		if (oldPeopleId == null)
			return 0L;
		return oldPeopleId;
	}

	public void setOldPeopleId(Long oldPeopleId) {
		this.oldPeopleId = oldPeopleId;
	}
	*/

	public String getPosition() {
		if (position == null)
			return "";
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPinyin() {
		if (pinyin == null)
			return "";
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getTaskId() {
		if (taskId == null)
			return "";
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getNameIndex() {
		if (nameIndex == null)
			return "";
		return nameIndex;
	}

	public void setNameIndex(String nameIndex) {
		this.nameIndex = nameIndex;
	}

	public String getNameFirst() {
		if (nameFirst == null)
			return "";
		return nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	public Byte getGender() {
		if (gender == null)
			return 3;//3-未知
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		if (telephone == null)
			return "";
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		if (email == null)
			return "";
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		if (company == null)
			return "";
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		if (address == null)
			return "";
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPortrait() {
		if (portrait == null)
			return "";
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getLocationCity() {
		if (locationCity == null)
			return "";
		return locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getLocationCounty() {
		if (locationCounty == null)
			return "";
		return locationCounty;
	}

	public void setLocationCounty(String locationCounty) {
		this.locationCounty = locationCounty;
	}

	public String getLocationCountry() {
		if (locationCountry == null)
			return "";
		return locationCountry;
	}

	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}

	public List<Basic> getCustomTagList() {
		if (customTagList == null)
			customTagList = new ArrayList<Basic>();
		return customTagList;
	}

	public void setCustomTagList(List<Basic> customTagList) {
		this.customTagList = customTagList;
	}

	public List<PersonalInformation> getPersonalInformationList() {
		if (personalInformationList == null)
			personalInformationList = new ArrayList<PersonalInformation>();
		return personalInformationList;
	}

	public void setPersonalInformationList(
			List<PersonalInformation> personalInformationList) {
		this.personalInformationList = personalInformationList;
	}

	public List<Intention> getInvestmentdemandList() {
		if (investmentdemandList == null)
			investmentdemandList = new ArrayList<Intention>();
		return investmentdemandList;
	}

	public void setInvestmentdemandList(List<Intention> investmentdemandList) {
		this.investmentdemandList = investmentdemandList;
	}

	public List<Intention> getFinancingdemandList() {
		if (financingdemandList == null)
			financingdemandList = new ArrayList<Intention>();
		return financingdemandList;
	}

	public void setFinancingdemandList(List<Intention> financingdemandList) {
		this.financingdemandList = financingdemandList;
	}

	public List<Intention> getExpertdemandList() {
		if (expertdemandList == null)
			expertdemandList = new ArrayList<Intention>();
		return expertdemandList;
	}

	public void setExpertdemandList(List<Intention> expertdemandList) {
		this.expertdemandList = expertdemandList;
	}

	public List<Intention> getExpertIdentityList() {
		if (expertIdentityList == null)
			expertIdentityList = new ArrayList<Intention>();
		return expertIdentityList;
	}

	public void setExpertIdentityList(List<Intention> expertIdentityList) {
		this.expertIdentityList = expertIdentityList;
	}

	public List<Education> getEducationList() {
		if (educationList == null)
			educationList = new ArrayList<Education>();
		return educationList;
	}

	public void setEducationList(List<Education> educationList) {
		this.educationList = educationList;
	}

	public List<WorkExperience> getWorkExperienceList() {
		if (workExperienceList == null)
			workExperienceList = new ArrayList<WorkExperience>();
		return workExperienceList;
	}

	public void setWorkExperienceList(List<WorkExperience> workExperienceList) {
		this.workExperienceList = workExperienceList;
	}

	public List<SocialActivity> getSocialActivityList() {
		if (socialActivityList == null)
			socialActivityList = new ArrayList<SocialActivity>();
		return socialActivityList;
	}

	public void setSocialActivityList(List<SocialActivity> socialActivityList) {
		this.socialActivityList = socialActivityList;
	}

	public List<Basic> getContactInformationList() {
		if (contactInformationList == null)
			contactInformationList = new ArrayList<Basic>();
		return contactInformationList;
	}

	public void setContactInformationList(List<Basic> contactInformationList) {
		this.contactInformationList = contactInformationList;
	}

	public List<PersonName> getPeopleNameList() {
		if (peopleNameList == null)
			peopleNameList = new ArrayList<PersonName>();
		return peopleNameList;
	}

	public void setPeopleNameList(List<PersonName> peopleNameList) {
		this.peopleNameList = peopleNameList;
	}

	public Long getCareerId() {
		if (careerId == null)
			return 0L;
		return careerId;
	}

	public void setCareerId(Long careerId) {
		this.careerId = careerId;
	}

	public Long getRegionId() {
		if (regionId == null)
			return 0L;
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRemark() {
		if (null == remark) {
			remark = "";
		}
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPeopleType() {
		if (peopleType == null)
			return "";
		return peopleType;
	}

	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType;
	}

	public Integer getTypeId() {
		if (typeId == null)
			return 0;
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public List<ProductExp> getProductexps() {
		return productexps;
	}

	public void setProductexps(List<ProductExp> productexps) {
		this.productexps = productexps;
	}

	public String getBigId() {
		return bigId;
	}

	public void setBigId(String bigId) {
		this.bigId = bigId;
	}

	public List<Honouexp> getHonouexps() {
		return honouexps;
	}

	public void setHonouexps(List<Honouexp> honouexps) {
		this.honouexps = honouexps;
	}

	public List<CustomTemp> getCustomTemp() {
		return customTemp;
	}

	public void setCustomTemp(List<CustomTemp> customTemp) {
		this.customTemp = customTemp;
	}

	public String getFirstIndustryDirection() {
		return firstIndustryDirection;
	}

	public void setFirstIndustryDirection(String firstIndustryDirection) {
		this.firstIndustryDirection = firstIndustryDirection;
	}

	public long getFirstIndustryDirectionId() {
		return firstIndustryDirectionId;
	}

	public void setFirstIndustryDirectionId(long firstIndustryDirectionId) {
		this.firstIndustryDirectionId = firstIndustryDirectionId;
	}

	public String getSecondIndustryDirection() {
		return secondIndustryDirection;
	}

	public void setSecondIndustryDirection(String secondIndustryDirection) {
		this.secondIndustryDirection = secondIndustryDirection;
	}

	public long getSecondIndustryDirectionId() {
		return secondIndustryDirectionId;
	}

	public void setSecondIndustryDirectionId(long secondIndustryDirectionId) {
		this.secondIndustryDirectionId = secondIndustryDirectionId;
	}
	
	
	public Map getPermissions() {
		return permissions;
	}

	public void setPermissions(Map permissions) {
		this.permissions = permissions;
	}

	public int getMailType() {
		return mailType;
	}

	public void setMailType(int mailType) {
		this.mailType = mailType;
	}
	
	
	
}
