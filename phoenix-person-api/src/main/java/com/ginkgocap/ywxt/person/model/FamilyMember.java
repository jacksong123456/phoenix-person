package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

/**
 * 家庭成员
 * @author caizhigang
 *
 */
public class FamilyMember implements Serializable{

	/**
	 * 名字
	 */
	String name="";
	
	
	/**
	 * 关系
	 */
	String relation="";


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRelation() {
		return relation;
	}


	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	
	
	
}
