package com.ginkgocap.ywxt.person.model;

/**
 * 人脉名称
 * @author wenbin
 *
 */
public class PeopleName extends BaseObject{

	private static final long serialVersionUID = -1776727966678686811L;
	/**主键*/
	private Long id;
	/**姓名类型：1-中文名，2-英文名，N-自定义*/
	private PeopleSelectTag typeTag;
	/**姓名*/
	private String name;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PeopleSelectTag getTypeTag() {
		return typeTag;
	}
	public void setTypeTag(PeopleSelectTag typeTag) {
		this.typeTag = typeTag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
