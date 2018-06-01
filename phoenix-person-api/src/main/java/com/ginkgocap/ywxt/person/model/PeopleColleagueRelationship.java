package com.ginkgocap.ywxt.person.model;


/**
 * 人脉 工作经历 同事关系
 * @author wenbin
 *
 */
public class PeopleColleagueRelationship extends BaseObject {

	private static final long serialVersionUID = -414530209944087623L;
	
	/**主键*/
	private Long id;
	/**类型：1-同事，2-上级，3-下级，N-自定义*/
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
