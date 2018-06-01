package com.ginkgocap.ywxt.person.model;

import java.util.List;


/**
 * 人脉 工作经历 社会活动
 * @author wenbin
 *
 */
public class PeopleSocialActivity extends BaseObject {

	private static final long serialVersionUID = -7931904431419730697L;
	
	/**主键*/
	private Long id;
	/**活动类型*/
	private List<PeopleActivity> activityList;
	/**介绍人*/
	private String referrals;
	/**老乡*/
	private String fellow;
	/**描述*/
	private String description;
	/**自定义字段*/
	private List<PeoplePersonalLine> personalLineList;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<PeopleActivity> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<PeopleActivity> activityList) {
		this.activityList = activityList;
	}
	public String getReferrals() {
		return referrals;
	}
	public void setReferrals(String referrals) {
		this.referrals = referrals;
	}
	public String getFellow() {
		return fellow;
	}
	public void setFellow(String fellow) {
		this.fellow = fellow;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<PeoplePersonalLine> getPersonalLineList() {
		return personalLineList;
	}
	public void setPersonalLineList(List<PeoplePersonalLine> personalLineList) {
		this.personalLineList = personalLineList;
	}
	
}
