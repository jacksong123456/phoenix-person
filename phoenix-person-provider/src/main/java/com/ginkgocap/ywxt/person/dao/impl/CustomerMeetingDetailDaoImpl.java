package com.ginkgocap.ywxt.person.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.ginkgocap.ywxt.person.dao.CustomerMeetingDetailDao;
import com.ginkgocap.ywxt.person.model.CustomerMeetingDetail;
import com.ginkgocap.ywxt.person.service.PersonIdService;

@Component("customerMeetingDetailDao")
public class CustomerMeetingDetailDaoImpl implements CustomerMeetingDetailDao {
	@Resource(name = "personMongoTemplate")
    private MongoTemplate organMongoTemplate;
	@Resource
	private PersonIdService personIdService;
	
	public CustomerMeetingDetail saveOrUpdate(CustomerMeetingDetail customerMeetingDetail) {
				if(customerMeetingDetail.getId() == null || customerMeetingDetail.getId().equals(0L)){
					customerMeetingDetail.setId(personIdService.getCustomerId());
				}
				organMongoTemplate.save(customerMeetingDetail);

				return customerMeetingDetail;
	}
	
	public boolean deleteById(long id) {
		try {
			Criteria c = Criteria.where("id").is(id);
			Query query = new Query(c);
			organMongoTemplate.remove(query, CustomerMeetingDetail.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public CustomerMeetingDetail findOne(long id) {
		return organMongoTemplate.findById(id, CustomerMeetingDetail.class);
	}
	
	public List<CustomerMeetingDetail> findByCustomerId(String customerId) {
		return null;
	}
	
	public List<CustomerMeetingDetail> findByParams(String repeadType) {
		Criteria c = Criteria.where("repeadType").is(repeadType);
		Query query = new Query(c);
		List<CustomerMeetingDetail> cmds=organMongoTemplate.find(query,CustomerMeetingDetail.class);
		return cmds;
	}
	
}
