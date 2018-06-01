package com.ginkgocap.ywxt.person.model;

/**
 * Created by Administrator on 2015/3/26.
 */
public class PersonPermissionExt {
	private PermIds permIds;
	private Boolean dule;

	public Boolean getDule() {
		if (dule == null)
			return false;
		return dule;
	}

	public void setDule(Boolean dule) {
		this.dule = dule;
	}

	public PermIds getPermIds() {
		if (permIds == null)
			permIds = new PermIds();
		return permIds;
	}

	public void setPermIds(PermIds permIds) {
		this.permIds = permIds;
	}
}
