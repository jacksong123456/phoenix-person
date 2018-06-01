package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签和人的关系表
 */
public class PersonTagRelation implements Serializable {
	/**
	 * 人脉id
	 */
	private Long personId;
	/**
	 * 人脉类型 1人脉 2用户
	 */
	private Byte personType;
	/**
	 * 标签id
	 */
	private Long tagId;
	/**
	 * 创建时间
	 */
	private Date ctime;

	public Long getPersonId() {
		if (personId == null)
			return 0L;
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Byte getPersonType() {
		if (personType == null)
			return 0;
		return personType;
	}

	public void setPersonType(Byte personType) {
		this.personType = personType;
	}

	public Long getTagId() {
		if (tagId == null)
			return 0L;
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
