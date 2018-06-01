package com.ginkgocap.ywxt.person.model;

import java.util.List;

/**
 * 人脉
 * @author wenbin
 *
 */
public class PeopleTemp extends BaseObject {

	private static final long serialVersionUID = -2592010496949013425L;

	/**主键*/
	private String id;//主键
	
	/********************基本信息start*****************************/
	/**姓名*/
	private List<PeopleName> peopleNameList;
	/**所在分组*/
	private List<PeopleGroupTemp> peopleGroupList;
	/**关联客户*/
	private List<PeopleCustomer> peopleCustomerList;
	/**性别：1-男，2-女，3-未知*/
	private Integer gender;
	/**头像*/
	private String portrait;
	/**备注*/
	private String remark;
	private String pinyin;//拼音
	private String nameIndex;//姓名首字母
	private String nameFirst;//姓名第一个字母
	/********************基本信息end*****************************/
	
	
	/********************联系方式start*****************************/
	/**手机*/
	private List<PeopleContactComm> contactMobileList;
	/**固话*/
	private List<PeopleContactComm> contactFixedList;
	/**传真*/
	private List<PeopleContactComm> contactFaxList;
	/**邮箱*/
	private List<PeopleContactComm> contactMailList;
	/**主页*/
	private List<PeopleContactComm> contactHomeList;
	/**通讯*/
	private List<PeopleContactComm> contactCommunicationList;
	/**地址*/
	private List<PeopleAddress> contactAddressList;
	/**自定义字段*/
	private List<PeoplePersonalLine> personalLineList;
	/********************联系方式end*****************************/
	
	
	/********************个人情况start*****************************/

	/**民族：1-汉族，2-满族····名称*/  
	private String raceName;
	/**国籍：1-中国，2-美国····名称*/
	private String nationalityName;
	/**信仰：1-佛教，2-道教，3-基督教，4-天主教，5-犹太教，6-伊斯兰教，7-印度教，8-无神论  名称 */
	private String faithName;
	/**籍贯国  国内、国外*/
	private String birthPlaceCountryName;
	/**籍贯省 名称*/
	private String birthPlaceStateName;
	/**籍贯市 名称*/
	private String birthPlaceCityName;
	/**籍贯县 名称*/
	private String birthPlaceCountyName;
//	/**民族：1-汉族，2-满族····*/
//	private Integer race;
//	/**国籍：1-中国，2-美国····*/
//	private Integer nationality;
//	/**信仰：1-佛教，2-道教，3-基督教，4-天主教，5-犹太教，6-伊斯兰教，7-印度教，8-无神论*/
//	private Integer faith;
//	/**籍贯省*/
//	private Integer birthPlaceState;
//	/**籍贯市*/
//	private Integer birthPlaceCity;
//	/**籍贯县*/
//	private Integer birthPlaceCounty;
	/**籍贯地址*/
	private String birthPlaceAddress;
	/**重要日期*/
	private List<PeopleImportantDate> importantDateList;
	/**社会关系*/
	private List<PeopleCommunityRelationship> communityRelationshipList;
	/**身体情况*/
	private String bodySituation;
	/**生活习惯*/
	private String habit;
	/**爱好*/
	private String hobby;
	/**自定义字段*/
	private List<PeoplePersonalLine> situationPersonalLineList;
	/********************个人情况end*****************************/
	
	
	/********************投融资需求start*****************************/
	/**投资需求*/
	private List<PeopleDemandCommon> investmentdemandList;
	/**融资需求*/
	private List<PeopleDemandCommon> financingdemandList;
	/**专家需求*/
	private List<PeopleDemandCommon> expertdemandList;
	/**专家身份*/
	private List<PeopleDemandCommon> expertIdentitydemandList;
	/********************投融资需求end*****************************/
	
	/**教育经历*/
	private List<PeopleEducation> educationList;
	
	/* *工作经历*/
	private List<PeopleWorkExperience> workExperienceList;
	
	/**社会活动*/
	private List<PeopleSocialActivity> socialActivityList;

	
	/**自定义板块*/
	private List<PeoplePersonalPlate> personalPlateList;
	
	/**文件ID 上传附件用*/
	private String taskId;
	
	/**传附件用*/
	private List<FileIndex> fileIndexs;
	
	/**************** 人脉详情需要显示字段start*******************/
	/**性别：1-男，2-女，3-未知*/
	private String genderName;
	
	/**************** 人脉详情需要显示字段end*******************/
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<PeopleName> getPeopleNameList() {
		return peopleNameList;
	}

	public void setPeopleNameList(List<PeopleName> peopleNameList) {
		this.peopleNameList = peopleNameList;
	}

	public List<PeopleGroupTemp> getPeopleGroupList() {
		return peopleGroupList;
	}

	public void setPeopleGroupList(List<PeopleGroupTemp> peopleGroupList) {
		this.peopleGroupList = peopleGroupList;
	}

	public List<PeopleCustomer> getPeopleCustomerList() {
		return peopleCustomerList;
	}

	public void setPeopleCustomerList(List<PeopleCustomer> peopleCustomerList) {
		this.peopleCustomerList = peopleCustomerList;
	}


	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<PeopleContactComm> getContactMobileList() {
		return contactMobileList;
	}

	public void setContactMobileList(List<PeopleContactComm> contactMobileList) {
		this.contactMobileList = contactMobileList;
	}

	public List<PeopleContactComm> getContactFixedList() {
		return contactFixedList;
	}

	public void setContactFixedList(List<PeopleContactComm> contactFixedList) {
		this.contactFixedList = contactFixedList;
	}

	public List<PeopleContactComm> getContactFaxList() {
		return contactFaxList;
	}

	public void setContactFaxList(List<PeopleContactComm> contactFaxList) {
		this.contactFaxList = contactFaxList;
	}

	public List<PeopleContactComm> getContactMailList() {
		return contactMailList;
	}

	public void setContactMailList(List<PeopleContactComm> contactMailList) {
		this.contactMailList = contactMailList;
	}

	public List<PeopleContactComm> getContactHomeList() {
		return contactHomeList;
	}

	public void setContactHomeList(List<PeopleContactComm> contactHomeList) {
		this.contactHomeList = contactHomeList;
	}

	public List<PeopleContactComm> getContactCommunicationList() {
		return contactCommunicationList;
	}

	public void setContactCommunicationList(
			List<PeopleContactComm> contactCommunicationList) {
		this.contactCommunicationList = contactCommunicationList;
	}

	public List<PeopleAddress> getContactAddressList() {
		return contactAddressList;
	}

	public void setContactAddressList(List<PeopleAddress> contactAddressList) {
		this.contactAddressList = contactAddressList;
	}

	public List<PeoplePersonalLine> getPersonalLineList() {
		return personalLineList;
	}

	public void setPersonalLineList(List<PeoplePersonalLine> personalLineList) {
		this.personalLineList = personalLineList;
	}

	public String getBirthPlaceAddress() {
		return birthPlaceAddress;
	}

	public void setBirthPlaceAddress(String birthPlaceAddress) {
		this.birthPlaceAddress = birthPlaceAddress;
	}

	public List<PeopleImportantDate> getImportantDateList() {
		return importantDateList;
	}

	public void setImportantDateList(List<PeopleImportantDate> importantDateList) {
		this.importantDateList = importantDateList;
	}

	public List<PeopleCommunityRelationship> getCommunityRelationshipList() {
		return communityRelationshipList;
	}

	public void setCommunityRelationshipList(
			List<PeopleCommunityRelationship> communityRelationshipList) {
		this.communityRelationshipList = communityRelationshipList;
	}

	public String getBodySituation() {
		return bodySituation;
	}

	public void setBodySituation(String bodySituation) {
		this.bodySituation = bodySituation;
	}

	public String getHabit() {
		return habit;
	}

	public void setHabit(String habit) {
		this.habit = habit;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public List<PeoplePersonalLine> getSituationPersonalLineList() {
		return situationPersonalLineList;
	}

	public void setSituationPersonalLineList(
			List<PeoplePersonalLine> situationPersonalLineList) {
		this.situationPersonalLineList = situationPersonalLineList;
	}

	public List<PeopleDemandCommon> getInvestmentdemandList() {
		return investmentdemandList;
	}

	public void setInvestmentdemandList(
			List<PeopleDemandCommon> investmentdemandList) {
		this.investmentdemandList = investmentdemandList;
	}

	public List<PeopleDemandCommon> getFinancingdemandList() {
		return financingdemandList;
	}

	public void setFinancingdemandList(List<PeopleDemandCommon> financingdemandList) {
		this.financingdemandList = financingdemandList;
	}

	public List<PeopleDemandCommon> getExpertdemandList() {
		return expertdemandList;
	}

	public void setExpertdemandList(List<PeopleDemandCommon> expertdemandList) {
		this.expertdemandList = expertdemandList;
	}

	public List<PeopleDemandCommon> getExpertIdentitydemandList() {
		return expertIdentitydemandList;
	}

	public void setExpertIdentitydemandList(
			List<PeopleDemandCommon> expertIdentitydemandList) {
		this.expertIdentitydemandList = expertIdentitydemandList;
	}

	public List<PeopleEducation> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<PeopleEducation> educationList) {
		this.educationList = educationList;
	}

	public List<PeopleWorkExperience> getWorkExperienceList() {
		return workExperienceList;
	}

	public void setWorkExperienceList(List<PeopleWorkExperience> workExperienceList) {
		this.workExperienceList = workExperienceList;
	}

	public List<PeopleSocialActivity> getSocialActivityList() {
		return socialActivityList;
	}

	public void setSocialActivityList(List<PeopleSocialActivity> socialActivityList) {
		this.socialActivityList = socialActivityList;
	}


	public List<PeoplePersonalPlate> getPersonalPlateList() {
		return personalPlateList;
	}

	public void setPersonalPlateList(List<PeoplePersonalPlate> personalPlateList) {
		this.personalPlateList = personalPlateList;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	public String getFaithName() {
		return faithName;
	}

	public void setFaithName(String faithName) {
		this.faithName = faithName;
	}

	public String getBirthPlaceStateName() {
		return birthPlaceStateName;
	}

	public void setBirthPlaceStateName(String birthPlaceStateName) {
		this.birthPlaceStateName = birthPlaceStateName;
	}

	public String getBirthPlaceCityName() {
		return birthPlaceCityName;
	}

	public void setBirthPlaceCityName(String birthPlaceCityName) {
		this.birthPlaceCityName = birthPlaceCityName;
	}

	public String getBirthPlaceCountyName() {
		return birthPlaceCountyName;
	}

	public void setBirthPlaceCountyName(String birthPlaceCountyName) {
		this.birthPlaceCountyName = birthPlaceCountyName;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public List<FileIndex> getFileIndexs() {
		return fileIndexs;
	}

	public void setFileIndexs(List<FileIndex> fileIndexs) {
		this.fileIndexs = fileIndexs;
	}

	public String getBirthPlaceCountryName() {
		return birthPlaceCountryName;
	}

	public void setBirthPlaceCountryName(String birthPlaceCountryName) {
		this.birthPlaceCountryName = birthPlaceCountryName;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(String nameIndex) {
		this.nameIndex = nameIndex;
	}

	public String getNameFirst() {
		return nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}


	
	

}
