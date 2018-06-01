package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

/**
 * 等待迁移到metdata 行业方向实体bean 2015-11-14 新需求，新增字段。 当前负责产品：闫晓林 陈延肖
 * 产品不同意延期妥善处理
 * 只好为力实现而实现
 * */

public class IndustryDirection implements Serializable {

	private static final long serialVersionUID = 2519683304728040093L;

	private int id;
	private String name;
	private int pid;
	private String sortId;
	private int flag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
