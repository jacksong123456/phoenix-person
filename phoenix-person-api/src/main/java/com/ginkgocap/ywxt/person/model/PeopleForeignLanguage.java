package com.ginkgocap.ywxt.person.model;


/**
 * 人脉 教育经历 外语语种
 * @author wenbin
 *
 */
public class PeopleForeignLanguage extends BaseObject {

	private static final long serialVersionUID = 1142114251549329786L;
	
	/**主键*/
	private Long id;
	/**类型：1-英语，2-日语，3-法语，4-德语，5-西班牙语···*/
	private String type;
	/**级别类型：1-四级，2-六级，3-八级···*/
	private String levelType;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	
}
