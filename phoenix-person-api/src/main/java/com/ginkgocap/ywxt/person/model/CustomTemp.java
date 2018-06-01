package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.List;

//自定义模块项
public class CustomTemp implements Serializable {

	private static final long serialVersionUID = 8542002297263035815L;

	// 自定义模板名称
	private String name;

	// 模型状态
	private boolean modelState;

	// 备用项字段
	private List<Basic> customUnits;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isModelState() {
		return modelState;
	}

	public void setModelState(boolean modelState) {
		this.modelState = modelState;
	}

	public List<Basic> getCustomUnits() {
		return customUnits;
	}

	public void setCustomUnits(List<Basic> customUnits) {
		this.customUnits = customUnits;
	}

}
