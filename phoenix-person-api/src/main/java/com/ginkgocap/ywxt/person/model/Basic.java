package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

/**
 * 自定义 、社会关系等
 */
public class Basic implements Serializable {
	private static final long serialVersionUID = -6518814221619289006L;
	public static final Byte PARENT_TYPE_MOBILE = 1;
	public static final Byte PARENT_TYPE_FIXED_PHONE = 2;
	public static final Byte PARENT_TYPE_FAX = 3;
	public static final Byte PARENT_TYPE_EMAIL = 4;
	/**
	 * 自定义名称
	 */
	private String name ;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 父类型：1-手机类型，2-固话类型，3-传真类型，4-邮箱类型，5-主页类型，6-即时通讯类型，7-地址, 9-自定义
	 */
	private String type;
	/**
	 * 子类型
	 *
	 *  手机类型：1-手机，2-电话，3-商务电话，4-主要电话，N-自定义
	 *  固话类型：1-固话，2-家庭电话，3-办公电话，4-主要电话，N-自定义
	 *  传真类型：1-住宅传真，2-商务传真，N-自定义
	 *  邮箱类型：1-主要邮箱，2-商务邮箱，N-自定义
	 * 	主页类型：1-个人主页，2-商务主页，N-自定义
	 * 	通讯类型：1-QQ，2-微信，3-微博，4-Skype，5-facebook，6-twitter，N-自定义
	 * 	地址类型：1-住宅，2-商务，N-自定义
	 * 	自定义类型：1-自定义字段，N-自定义长文本
	 */
	private String subtype;

	/**
	 * 前端用
	 */
	private String isClone;
	

	
	public String getContent() {
		if (content==null){
			content="" ;
		}

		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		if (name==null){
			return "" ;
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		if (type==null){
			type="" ;
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	
	
	
	public String getIsClone() {
		return isClone;
	}

	public void setIsClone(String isClone) {
		this.isClone = isClone;
	}

}
