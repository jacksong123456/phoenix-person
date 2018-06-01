package com.ginkgocap.ywxt.person.model;

/**
 * 人脉 个人情况 重要日期
 * @author wenbin
 *
 */
public class PeopleImportantDate extends BaseObject {

	private static final long serialVersionUID = -8248869086739542704L;
	
	/**主键*/
	private Long id;
	/**类型：1-生日，2-周年纪念日*/
	private PeopleSelectTag typeTag;
	/**日期*/
	private String date;
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
