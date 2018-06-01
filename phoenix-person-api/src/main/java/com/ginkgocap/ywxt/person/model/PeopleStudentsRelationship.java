package com.ginkgocap.ywxt.person.model;


/**
 * 人脉 教育经历 同学关系
 * @author wenbin
 *
 */
public class PeopleStudentsRelationship extends BaseObject {

	/**主键*/
	private Long id;
	/**类型：1-同学，2-校友，3-老师，4-校长，N-自定义*/
	private PeopleSelectTag typeTag;
	/**关系描述*/
	private String content;
	
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
