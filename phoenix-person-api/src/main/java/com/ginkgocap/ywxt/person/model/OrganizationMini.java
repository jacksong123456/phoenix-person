package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

//组织简易耦合对象
public class OrganizationMini implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private boolean isOnline;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

}
