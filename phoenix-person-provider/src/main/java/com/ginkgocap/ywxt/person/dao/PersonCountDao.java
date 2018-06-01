package com.ginkgocap.ywxt.person.dao;

import com.ginkgocap.ywxt.person.model.PersonCount;

public interface PersonCountDao {
	
	PersonCount getPersonCountById(long personId);
	
	void insertPersonCount(PersonCount personCount);
	
	void updatePersonCount(PersonCount personCount);

}
