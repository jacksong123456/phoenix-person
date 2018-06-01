package com.ginkgocap.ywxt.person.model;


/**
 * 人脉 社会活动 活动类型
 * @author wenbin
 *
 */
public class PeopleActivity extends BaseObject {

	private static final long serialVersionUID = 7033294052932641457L;
	/**主键*/
	private Long id;
	/**类型：1-社团，2-组织，3-党派，4-政治团体，5-慈善机构，N-自定义*/
	private PeopleSelectTag typeTag;
	/**描述*/
	private String content;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public PeopleSelectTag getTypeTag() {
		return typeTag;
	}
	public void setTypeTag(PeopleSelectTag typeTag) {
		this.typeTag = typeTag;
	}
	
}
