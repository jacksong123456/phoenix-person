package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/3/26.
 */
public class PermissPeople implements Serializable {
	
	private static final long serialVersionUID = -1595824880668577624L;
	
	private Long id;
	private String name;
	private String picPath;
	private int type;
	

	public Long getId() {
		if (id == null)
			return 0L;
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		if (name == null)
			return "";
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
