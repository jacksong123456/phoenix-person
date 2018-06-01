/**
 * 
 */
package com.ginkgocap.ywxt.person.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.dao.CustomerMeetingDetailDao;
import com.ginkgocap.ywxt.person.model.CustomerMeetingDetail;
import com.ginkgocap.ywxt.person.service.PersonMeetingDetailService;
import com.ginkgocap.ywxt.util.DateFunc;
import com.ginkgocap.ywxt.util.PageUtil;


/**
 * 会面记录service层
 * @author tanghuihuang
 *
 * 2015-1-6
 */
@Service("personMeetingDetailService")
public class PersonMeetingDetailServiceImpl implements PersonMeetingDetailService {
	@Resource
	private CustomerMeetingDetailDao customerMeetingDetailDao;

	@Resource(name = "personMongoTemplate")
    private MongoTemplate organMongoTemplate;

	
	public boolean deleteById(long arg0) {
		return customerMeetingDetailDao.deleteById(arg0);
	}

	
	public CustomerMeetingDetail findOne(long arg0) {
		return customerMeetingDetailDao.findOne(arg0);
	}

	
	public CustomerMeetingDetail saveOrUpdate(CustomerMeetingDetail cm) {
		cm.setPersonType(2);
		if(cm.getId() == null || cm.getId().equals(0L)){
			cm.setCtime(DateFunc.getDate());
		}
		return customerMeetingDetailDao.saveOrUpdate(cm);
	}
	
	public Map<String, Object> findByParams(String repeadType, int currentPage, int pageSize) {
		Criteria c = null;
		if("-1".equals(repeadType)){//查询全部
			c=Criteria.where("repeadType").ne(repeadType);
		}else if("-2".equals(repeadType)){
			c=Criteria.where("repeadType").ne(0);
		}else{
			c=Criteria.where("repeadType").is(repeadType);
		}
        Query query = new Query(c);
        long count = organMongoTemplate.count(query, CustomerMeetingDetail.class);
        PageUtil page = new PageUtil((int) count,currentPage,pageSize);
        query = new Query(c);
        query.skip(page.getPageStartRow()-1);
        query.limit(pageSize);
        List<CustomerMeetingDetail> lt = organMongoTemplate.find(query, CustomerMeetingDetail.class);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("page", page);
        result.put("results", lt);
        return result;
	}
	public Map<String, Object> findByPersonId(long personId, int currentPage, int pageSize){
		Criteria c = Criteria.where("personId").is(personId);
		 Query query = new Query(c);
        long count = organMongoTemplate.count(query, CustomerMeetingDetail.class);
        PageUtil page = new PageUtil((int) count,currentPage,pageSize);
        query = new Query(c);
        query.skip(page.getPageStartRow()-1);
        query.limit(pageSize);
        List<CustomerMeetingDetail> cmdList = organMongoTemplate.find(query, CustomerMeetingDetail.class);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("page", page);
        result.put("results", cmdList);
        return result;
	}
}
