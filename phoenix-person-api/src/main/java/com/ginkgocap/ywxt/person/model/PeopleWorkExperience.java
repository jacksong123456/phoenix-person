package com.ginkgocap.ywxt.person.model;

import java.util.List;

/**
 * 人脉：工作经历
 * @author wenbin
 *
 */
public class PeopleWorkExperience extends BaseObject {

	private static final long serialVersionUID = 2485688432817917896L;
	
	/**主键*/
	private Long id;
	/**时间起*/
	private String startTime;
	/**时间止*/
	private String endTime;
	/**公司名称*/
	private String company;
	/**公司行业*/
	private String companyIndustry;
	/**证明人*/
	private String references;
	/**联系电话*/
	private String tel;
	/**部门*/
	private String department;
	/**职务*/
	private String position;
	/**描述*/
	private String description;
	
	/**将本经历作为当前身份*/
	private boolean currentStatus;
	/**同事关系*/
	private List<PeopleColleagueRelationship> colleagueRelationshipList;
	/**自定义字段*/
	private List<PeoplePersonalLine> personalLineList;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompanyIndustry() {
		return companyIndustry;
	}
	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}
	public String getReferences() {
		return references;
	}
	public void setReferences(String references) {
		this.references = references;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(boolean currentStatus) {
		this.currentStatus = currentStatus;
	}
	public List<PeopleColleagueRelationship> getColleagueRelationshipList() {
		return colleagueRelationshipList;
	}
	public void setColleagueRelationshipList(
			List<PeopleColleagueRelationship> colleagueRelationshipList) {
		this.colleagueRelationshipList = colleagueRelationshipList;
	}
	public List<PeoplePersonalLine> getPersonalLineList() {
		return personalLineList;
	}
	public void setPersonalLineList(List<PeoplePersonalLine> personalLineList) {
		this.personalLineList = personalLineList;
	}
	
}
