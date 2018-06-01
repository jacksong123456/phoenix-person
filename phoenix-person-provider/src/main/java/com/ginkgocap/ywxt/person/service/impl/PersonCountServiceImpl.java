package com.ginkgocap.ywxt.person.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.dao.PersonCountDao;
import com.ginkgocap.ywxt.person.model.PersonCount;
import com.ginkgocap.ywxt.person.service.PersonCountService;

@Service("personCountService")
public class PersonCountServiceImpl implements PersonCountService {
	
	@Autowired
	private PersonCountDao personCountDao;

	public void addPersonCount(long personId, int addType) {
		
		PersonCount pesonCount = personCountDao.getPersonCountById(personId);
		
		if (pesonCount != null) {
			
			strategy(pesonCount,addType);
			
			personCountDao.updatePersonCount(pesonCount);
		
		} else {
			
			pesonCount = new PersonCount();
			
			pesonCount.setPersonId(personId);
			
			strategy(pesonCount,addType);
			
			personCountDao.insertPersonCount(pesonCount);
			
		}
	}
	
	//addType = 1 readCount addType = 2 shareCount addType = 3 collectCount addType = 4 
	private void strategy(PersonCount personCount,int addType) {
		
		switch (addType) {
		case 1:
			personCount.setReadCount(personCount.getReadCount()+1);
			break;
		case 2:
			personCount.setShareCount(personCount.getShareCount()+1);
			break;	
		case 3:
			personCount.setCollectCount(personCount.getCollectCount()+1);
			break;
		case 4:
			personCount.setEvaluateCount(personCount.getEvaluateCount()+1);
			break;	
		default:
			personCount.setReadCount(personCount.getReadCount()+1);
			break;
		}
	}

}
