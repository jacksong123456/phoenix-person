package com.ginkgocap.ywxt.person.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.ginkgocap.ywxt.person.dao.CustomerMeetDao;
import com.ginkgocap.ywxt.person.model.CustomerMeet;
import com.ginkgocap.ywxt.person.service.PersonIdService;



@Component("customerMeetDao")
public class CustomerMeetDaoImpl implements CustomerMeetDao {
	@Resource(name = "personMongoTemplate")
    private MongoTemplate organMongoTemplate;
	@Resource
	private PersonIdService personIdService;
	
	public CustomerMeet saveOrUpdate(CustomerMeet customerMeet) {
				if(customerMeet.getId()==0){
					customerMeet.setId(personIdService.getCustomerId());
				}
				organMongoTemplate.save(customerMeet);
				return customerMeet;
	}
	
	public boolean deleteById(long id) {
		try {
			Criteria c = Criteria.where("id").is(id);
			Query query = new Query(c);
			organMongoTemplate.remove(query, CustomerMeet.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteByPersonId(long id) {
		try {
			Criteria c = Criteria.where("personId").is(id);
			Query query = new Query(c);
			organMongoTemplate.remove(query, CustomerMeet.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public CustomerMeet findOne(long id) {
		return organMongoTemplate.findById(id, CustomerMeet.class);
	}
	
	public List<CustomerMeet> findByCustomerId(String customerId) {
		return null;
	}
	
}
