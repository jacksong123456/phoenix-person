package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

//统计人脉各维度被关注方法
public class PersonCount implements Serializable{
	
	private static final long serialVersionUID = 8435473491100043745L;
	
	private long personId;
	private long readCount;
	private long shareCount;
	private long collectCount;
	private long evaluateCount;
	private int type;
	
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public long getReadCount() {
		return readCount;
	}
	public void setReadCount(long readCount) {
		this.readCount = readCount;
	}
	public long getShareCount() {
		return shareCount;
	}
	public void setShareCount(long shareCount) {
		this.shareCount = shareCount;
	}
	public long getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(long collectCount) {
		this.collectCount = collectCount;
	}
	public long getEvaluateCount() {
		return evaluateCount;
	}
	public void setEvaluateCount(long evaluateCount) {
		this.evaluateCount = evaluateCount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
