package com.ginkgocap.ywxt.person.service;

import com.ginkgocap.ywxt.person.model.PeopleRemark;

public interface PeopleRemarkService {

	public void addPeopleRemark(PeopleRemark  peopleRemark );
	
	public void updatePeopleRemark(PeopleRemark  peopleRemark );

	public PeopleRemark getPeopleRemark(PeopleRemark  peopleRemark );
	
	public void saveOrUpdate(PeopleRemark  peopleRemark );
	
	
}
