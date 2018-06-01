package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作经历
 */
public class WorkExperience implements Serializable {
	
	private static final long serialVersionUID = 5217672006002780474L;

	//开始时间
	private String stime;
	
	//结束时间
	private String etime;

	//开始时间
	private String startTime;

	//结束时间
	private String endTime;
	
	/**
	 * 公司
	 */
	private String company;
	/**
	 * 公司行业
	 */
	private String companyIndustry;
	/**
	 * 部门
	 */
	private String department;
	/**
	 * 职务
	 */
	private String position;
	/**
	 * 证明人
	 */
	private String certifier;
	/**
	 * 证明人联系方式
	 */
	private String certifierPhone;
	
	/**
	 * 描述
	 * */
	private String desc;
	
	/**
	 * 同事关系
	 */
	private List<Basic> colleagueRelationshipList;
	/**
	 * 工作经历自定义项
	 */
	private List<Basic> custom;
	/**将本经历作为当前身份*/
	private boolean currentStatus;

	public String getCompany() {
		if (company == null)
			return "";
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyIndustry() {
		if (companyIndustry == null)
			return "";
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public String getDepartment() {
		if (department == null)
			return "";
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		if (position == null)
			return "";
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCertifier() {
		if (certifier == null)
			return "";
		return certifier;
	}

	public void setCertifier(String certifier) {
		this.certifier = certifier;
	}

	public String getCertifierPhone() {
		if (certifierPhone == null)
			return "";
		return certifierPhone;
	}

	public void setCertifierPhone(String certifierPhone) {
		this.certifierPhone = certifierPhone;
	}

	public List<Basic> getColleagueRelationshipList() {
		if (colleagueRelationshipList == null)
			colleagueRelationshipList = new ArrayList<Basic>();
		return colleagueRelationshipList;
	}

	public void setColleagueRelationshipList(
			List<Basic> colleagueRelationshipList) {
		this.colleagueRelationshipList = colleagueRelationshipList;
	}

	public List<Basic> getCustom() {
		if (custom == null)
			custom = new ArrayList<Basic>();
		return custom;
	}

	public void setCustom(List<Basic> custom) {
		this.custom = custom;
	}

	public boolean isCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(boolean currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStime() {
		return stime != null ? stime : startTime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime != null ? etime : endTime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
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

}
