package com.ginkgocap.ywxt.person.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.dao.PeopleRemarkDao;
import com.ginkgocap.ywxt.person.dao.PersonSimpleDao;
import com.ginkgocap.ywxt.person.model.PeopleRemark;
import com.ginkgocap.ywxt.person.service.PeopleRemarkService;


@Service("peopleRemarkService")
public class PeopleRemarkServiceImpl   implements PeopleRemarkService{
	
	@Autowired
	PeopleRemarkDao peopleRemarkDao;

	public void addPeopleRemark(PeopleRemark peopleRemark) {
		// TODO Auto-generated method stub
		peopleRemarkDao.addPeopleRemark(peopleRemark);
	}

	public void updatePeopleRemark(PeopleRemark peopleRemark) {
		// TODO Auto-generated method stub
		peopleRemarkDao.updatePeopleRemark(peopleRemark);
	}

	public PeopleRemark getPeopleRemark(PeopleRemark peopleRemark) {
		// TODO Auto-generated method stub
		return peopleRemarkDao.getPeopleRemark(peopleRemark);
	}

	public void savePeopleRemark(PeopleRemark peopleRemark) {
		// TODO Auto-generated method stub
		
	}

	public void saveOrUpdate(PeopleRemark peopleRemark) {
		// TODO Auto-generated method stub
		 peopleRemarkDao.saveOrUpdate(peopleRemark);
	}

}
