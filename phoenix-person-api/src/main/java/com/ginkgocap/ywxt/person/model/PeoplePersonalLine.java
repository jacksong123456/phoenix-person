package com.ginkgocap.ywxt.person.model;


/**
 * 人脉通用自定义标签
 * @author wenbin
 *
 */
public class PeoplePersonalLine extends BaseObject {

	private static final long serialVersionUID = -689880968897406943L;
	
	
	/**主键*/
	private Long id;
	/**所属模块： 1-联系方式，2-个人情况，3-投资需求，4-融资需求，5-专家需求，6-专家身份，7-教育经历，8-工作经历，9-社会活动，10-会面情况，99-自定义板块*/
	private Integer parentType;
	/**自定义名称代码*/
	private String code;
	/**自定义名称*/
	private String name;
	/**内容*/
	private String content;
	
	public static final int PARENTTYPE_CONTACT = 1;
	public static final int PARENTTYPE_SITUATIONPERSONAL = 2;
	public static final int PARENTTYPE_INVESTMENTDEMAND = 3;
	public static final int PARENTTYPE_FINANCINGDEMAND = 4;
	public static final int PARENTTYPE_EXPERTDEMAND = 5;
	public static final int PARENTTYPE_EXPERTIDENTITYDEMAND = 6;
	public static final int PARENTTYPE_EDUCATION = 7;
	public static final int PARENTTYPE_WORKEXPERIENCE = 8;
	public static final int PARENTTYPE_SOCIALACTIVITY = 9;
	public static final int PARENTTYPE_MEETING = 10;
	public static final int PARENTTYPE_PERSONALPLATE = 99;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentType() {
		return parentType;
	}
	public void setParentType(Integer parentType) {
		this.parentType = parentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
