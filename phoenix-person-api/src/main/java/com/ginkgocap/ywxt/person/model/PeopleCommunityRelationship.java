package com.ginkgocap.ywxt.person.model;

/**
 * 人脉 个人情况 社会关系
 * @author wenbin
 *
 */
public class PeopleCommunityRelationship extends BaseObject {

	private static final long serialVersionUID = -6285194705065611887L;
	
	/**主键*/
	private Long id;
	/**类型：1-配偶，2-父亲，3-母亲，4-兄弟，5-姐妹  6-同居伴侣，7-子女，8-经理，9-助手，10-合作伙伴，11-介绍人，N-自定义*/
	private PeopleSelectTag typeTag;
	/**内容*/
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
