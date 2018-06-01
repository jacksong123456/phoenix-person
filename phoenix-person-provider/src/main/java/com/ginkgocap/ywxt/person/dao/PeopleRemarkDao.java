package com.ginkgocap.ywxt.person.dao;

import com.ginkgocap.ywxt.person.model.PeopleRemark;

public interface PeopleRemarkDao {
	
	public void addPeopleRemark(PeopleRemark  peopleRemark );
	
	public void updatePeopleRemark(PeopleRemark  peopleRemark );
	
	
	public void saveOrUpdate(PeopleRemark  peopleRemark );
	public PeopleRemark getPeopleRemark(PeopleRemark  peopleRemark );
	

}
