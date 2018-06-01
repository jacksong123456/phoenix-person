package com.ginkgocap.ywxt.person.model;

/**
 * 联系方式公共对象
 * @author wenbin
 *
 */
public class PeopleContactComm extends BaseObject {

	private static final long serialVersionUID = 4163067227295889019L;

	/**主键*/
	private Long id;
	
	/**通讯类型： 1-手机类型，2-固话类型，3-传真类型，4-邮箱类型，5-主页类型，6-通讯类型*/
	private Integer parentType;
	
	/**类型	 *  
	 *  手机类型：1-手机，2-电话，3-商务电话，4-主要电话，N-自定义
	 *  固话类型：1-固话，2-家庭电话，3-办公电话，4-主要电话，N-自定义
	 *  传真类型：1-住宅传真，2-商务传真，N-自定义
	 *  邮箱类型：1-主要邮箱，2-商务邮箱，N-自定义
	 * 	主页类型：1-个人主页，2-商务主页，N-自定义
	 * 	通讯类型：1-QQ，2-微信，3-MSN，4-Skype，5-新浪微博，4-facebook，4-twitter，N-自定义
	 */
	private PeopleSelectTag typeTag;
	/**内容（号码）*/
	private String content;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public PeopleSelectTag getTypeTag() {
		return typeTag;
	}
	public void setTypeTag(PeopleSelectTag typeTag) {
		this.typeTag = typeTag;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
