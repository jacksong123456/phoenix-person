package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

/**
 * 作品
 */
public class ProductExp implements Serializable {

	/**作品内容*/
	private String achievement;

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
}
