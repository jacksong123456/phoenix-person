package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 社会活动
 */
public class SocialActivity implements Serializable {
	/**
	 * 介绍人
	 */
	private String introducer;
	private String abc;
	/**
	 * 老乡
	 */
	private String townsmen;
	/**
	 * 活动类型
	 */
	private List<Basic> activityType;
	/**
	 * 社会活动自定义项
	 */
	private List<Basic> custom;

	public String getIntroducer() {
		if (introducer == null)
			return "";
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	public String getTownsmen() {
		if (townsmen == null)
			return "";
		return townsmen;
	}

	public void setTownsmen(String townsmen) {
		this.townsmen = townsmen;
	}

	public List<Basic> getActivityType() {
		if (activityType == null)
			activityType = new ArrayList<Basic>();
		return activityType;
	}

	public void setActivityType(List<Basic> activityType) {
		this.activityType = activityType;
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
