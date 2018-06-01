package com.ginkgocap.ywxt.person.service;

public interface PersonCountService {
	
	//添加人脉统计维度方法
	/**
	 * addType = 1 readCount
	 * addType = 2 shareCount
	 * addType = 3 collectCount
	 * addType = 4 evaluateCount
	 * */
	void addPersonCount(long personId,int addType);

}
