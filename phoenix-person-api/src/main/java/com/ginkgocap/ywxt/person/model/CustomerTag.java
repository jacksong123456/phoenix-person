package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
* <p>Title: CustomerTag.java<／p> 
* <p>Description: 客户标签<／p> 
* @author wfl
* @date 2014-12-29 
* @version 1.0
 */
public class CustomerTag implements  Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tagId; //标签id
	  private String tagName;//标签名称
	  
	public String getTagId()	{
		if (tagId==null){
			tagId ="" ;
		}
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return  StringUtils.trimToEmpty(tagName);
	}
	public void setTagName(String tagName) {
		this.tagName = StringUtils.trimToEmpty(tagName);
	}
	  
}
