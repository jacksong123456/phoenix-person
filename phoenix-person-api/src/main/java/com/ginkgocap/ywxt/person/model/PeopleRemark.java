package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

public class PeopleRemark   implements Serializable{
	
	long id;
	long userId;
	long peopleId;
	int  peopleType;
	
	String description="";
	
	int count;  // mybatis 做 saveOrUpdate 用 
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(long peopleId) {
		this.peopleId = peopleId;
	}
	public int getPeopleType() {
		return peopleType;
	}
	public void setPeopleType(int peopleType) {
		this.peopleType = peopleType;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
