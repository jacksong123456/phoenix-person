package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

public class Ptemplate implements Serializable {

	private static final long serialVersionUID = -1077856888807434999L;

	private long id;
	private String any;
	private String ctime;
	private String template;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAny() {
		return any;
	}

	public void setAny(String any) {
		this.any = any;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}
