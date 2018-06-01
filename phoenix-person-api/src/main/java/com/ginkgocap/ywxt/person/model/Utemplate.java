package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

public class Utemplate implements Serializable {

	private static final long serialVersionUID = -6332765844491151899L;

	private long id;

	private String ctime;

	private String template;
	
	private boolean modelState;

	private String templateName;

	private long userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isModelState() {
		return modelState;
	}

	public void setModelState(boolean modelState) {
		this.modelState = modelState;
	}

}
