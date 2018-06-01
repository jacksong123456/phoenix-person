package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: PermIds.java
 * @Package com.ginkgocap.ywxt.demand.form.property
 * @Description:
 * @author haiyan
 * @date 2015-3-6 下午3:21:12
 */
public class PermIds implements Serializable {
	/**
	  *
	 */
	private static final long serialVersionUID = -4893442325385620545L;
	private Boolean dule;
	private List<PersonPermDales> dales;
	private List<PersonPermZhongles> zhongles;
	private List<PersonPermXiaoles> xiaoles;

	/**
	 * @return the dule
	 */
	public Boolean getDule() {
		if (dule == null)
			return false;
		return dule;
	}

	/**
	 * @param dule
	 *            the dule to set
	 */
	public void setDule(Boolean dule) {
		this.dule = dule;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<PersonPermDales> getDales() {
		if (dales == null)
			dales = new ArrayList<PersonPermDales>();
		return dales;
	}

	public void setDales(List<PersonPermDales> dales) {
		this.dales = dales;
	}

	public List<PersonPermZhongles> getZhongles() {
		if (zhongles == null)
			zhongles = new ArrayList<PersonPermZhongles>();
		return zhongles;
	}

	public void setZhongles(List<PersonPermZhongles> zhongles) {
		this.zhongles = zhongles;
	}

	public List<PersonPermXiaoles> getXiaoles() {
		if (xiaoles == null)
			xiaoles = new ArrayList<PersonPermXiaoles>();
		return xiaoles;
	}

	public void setXiaoles(List<PersonPermXiaoles> xiaoles) {
		this.xiaoles = xiaoles;
	}
}
