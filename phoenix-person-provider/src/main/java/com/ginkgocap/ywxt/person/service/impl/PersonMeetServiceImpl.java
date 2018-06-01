/**
 * 
 */
package com.ginkgocap.ywxt.person.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.dao.CustomerMeetDao;
import com.ginkgocap.ywxt.person.model.CustomerMeet;
import com.ginkgocap.ywxt.person.service.PersonMeetService;



/**
 * 会面记录service层
 * @author tanghuihuang
 *
 * 2015-1-6
 */
@Service("personMeetService")
public class PersonMeetServiceImpl implements PersonMeetService {
	@Resource
	private CustomerMeetDao customerMeetDao;


	public boolean deleteById(long id) {
		return customerMeetDao.deleteById(id);
	}

	
	public CustomerMeet findOne(long arg0) {
		return customerMeetDao.findOne(arg0);
	}

	
	public CustomerMeet saveOrUpdate(CustomerMeet arg0) {
		return customerMeetDao.saveOrUpdate(arg0);
	}

}
