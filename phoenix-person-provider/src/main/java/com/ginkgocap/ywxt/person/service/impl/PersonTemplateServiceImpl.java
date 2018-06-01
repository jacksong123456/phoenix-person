package com.ginkgocap.ywxt.person.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.MongoIds;
import com.ginkgocap.ywxt.person.model.Ptemplate;
import com.ginkgocap.ywxt.person.model.Utemplate;
import com.ginkgocap.ywxt.person.mongodb.dao.PersonTemplateDao;
import com.ginkgocap.ywxt.person.service.PersonTemplateService;

@SuppressWarnings("restriction")
@Service("personTemplateService")
public class PersonTemplateServiceImpl implements PersonTemplateService {
	
	@Autowired
	private PersonTemplateDao personTemplateDao;
	
	@Resource(name = "personMongoTemplate")
	private MongoTemplate personMongoTemplate;
	
	public long insertPeopleTemplate(long peopleid, String any, String template) {
		Ptemplate pt = new Ptemplate();
		pt.setAny(any);
		pt.setId(peopleid);
		pt.setCtime(getDate());
		pt.setTemplate(template);
		return personTemplateDao.insertPeopleTemplate(pt);
	}

	public boolean updatePeopleTemplate(long peopleid, String any, String template) {
		return personTemplateDao.updatePeopleTemplate(peopleid, getDate(), any, template);
		
	}

	public boolean deletePeopleTemplate(long peopleId) {
		return personTemplateDao.deletePeopleTemplate(peopleId);
	}

	public Ptemplate getPeopleTemplate(long peopleId) {
		return personTemplateDao.getPeopleTemplate(peopleId);
	}

	public long insertUserTemplate(String template, String templateName,
			long userId) {
		Utemplate ut = new Utemplate();
		ut.setCtime(getDate());
		ut.setId(getPersonIncreasedId());
		ut.setUserId(userId);
		ut.setTemplate(template);
		ut.setTemplateName(templateName);
		return personTemplateDao.insertUserTemplate(ut);
	}

	public boolean updateUserTemplate(long templateId, String templateName) {
		return personTemplateDao.updateUserTemplate(getDate(), templateId, templateName);
	}

	public boolean deleteUserTemplateById(long templateId) {
		return personTemplateDao.deleteUserTemplateById(templateId);
	}

	public boolean deleteUserTemplateByUserId(long userId) {
		return personTemplateDao.deleteUserTemplateByUserId(userId);
	}

	public List<Utemplate> findAllUtemplate(long userId) {
		return personTemplateDao.findAllUtemplate(userId);
	}
	
	private Long getPersonIncreasedId() {
		String key = "person";
		String collectionName = "MongoIds";
		Criteria c = Criteria.where("name").is(key);
		Query query = new Query(c);
		Update update = new Update();
		update.inc("cid", 1);
		MongoIds mongoIds = personMongoTemplate.findAndModify(query, update,
				MongoIds.class, collectionName);
		if (mongoIds == null) {
			mongoIds = new MongoIds();
			mongoIds.setCid(1L);
			mongoIds.setName(key);
			personMongoTemplate.insert(mongoIds, collectionName);
		}
		if (mongoIds.getCid().intValue() == Integer.MAX_VALUE) {// 线上历史数据中最小的人脉id是Integer.MAX_VALUE，除此之外的最小的id是1的16次方级别的。在此处理Integer.MAX_VALUE后，老数据的id不用修改
			mongoIds = personMongoTemplate.findAndModify(query, update,
					MongoIds.class, collectionName);
		}
		return mongoIds.getCid();
	}
	
	private String getDate() {
		return (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}

}
