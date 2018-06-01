package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

/**
 * 标签类型
 */
public class TypeTag implements Serializable {
	private static final long serialVersionUID = -4662106043586962685L;
	/**
	 * 标签id
	 */
	private Long id;
	/**
	 * 类型，0-默认，1-自定义
	 */
	private Byte type;
	public static Byte TYPE_DEFINED = 0;// 默认、预定义好的
	/**
	 * 标签名称
	 */
	private String name;

	public Long getId() {
		if (id == null)
			return 0L;
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getType() {
		if (type == null)
			return 0;
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getName() {
		if (name == null)
			return "";
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
