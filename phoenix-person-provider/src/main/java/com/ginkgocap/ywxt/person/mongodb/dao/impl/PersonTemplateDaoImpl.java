package com.ginkgocap.ywxt.person.mongodb.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.model.Ptemplate;
import com.ginkgocap.ywxt.person.model.Utemplate;
import com.ginkgocap.ywxt.person.mongodb.dao.PersonTemplateDao;

@SuppressWarnings("restriction")
@Service
public class PersonTemplateDaoImpl implements PersonTemplateDao {

	public static final String USER_TEMPLATE_COLLECTION_NAME = "utemplate";
	public static final String PEOPLE_TEMPLATE_COLLECTION_NAME = "ptemplate";

	@Resource(name = "personMongoTemplate")
	private MongoTemplate mongoTemplate;

	public long insertPeopleTemplate(Ptemplate ptemplate) {
		mongoTemplate.save(ptemplate, PEOPLE_TEMPLATE_COLLECTION_NAME);
		return ptemplate.getId();
	}

	public boolean updatePeopleTemplate(long peopleId, String ctime, String any,
			String template) {
		Query query = new Query().addCriteria(new Criteria().and("id").is(peopleId));
		Update update = this.getPtemplateUpdate(ctime, any, template);
		try {
			mongoTemplate
					.upsert(query, update, PEOPLE_TEMPLATE_COLLECTION_NAME);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean deletePeopleTemplate(long peopleId) {
		Query query = new Query().addCriteria(new Criteria().and("id").is(peopleId));
		try {
			mongoTemplate.remove(query, Ptemplate.class);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Ptemplate getPeopleTemplate(long peopleId) {
		return mongoTemplate.findById(peopleId, Ptemplate.class);
	}

	public long insertUserTemplate(Utemplate utemplate) {
		mongoTemplate.save(utemplate, USER_TEMPLATE_COLLECTION_NAME);
		return utemplate.getId();
	}

	public boolean updateUserTemplate(String ctime, long templateId,
			String templateName) {
		Query query = new Query().addCriteria(new Criteria().and("id").is(templateId));
		Update update = this.getUtemplateUpdate(ctime, templateName);
		try {
		mongoTemplate
					.upsert(query, update, USER_TEMPLATE_COLLECTION_NAME);
		} catch (Exception e) {
			return false;
		}	
		return true;
	}

	public boolean deleteUserTemplateById(long templateId) {
		Query query = new Query().addCriteria(new Criteria().and("id").is(templateId));
		try {
			mongoTemplate.remove(query, Utemplate.class);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean deleteUserTemplateByUserId(long userId) {
		Query query = new Query().addCriteria(new Criteria().and("userId").is(userId));
		try {
			mongoTemplate.remove(query, Utemplate.class);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public List<Utemplate> findAllUtemplate(long userId) {
		Query query = new Query().addCriteria(new Criteria().and("userId").is(userId));
		return mongoTemplate.find(query, Utemplate.class, USER_TEMPLATE_COLLECTION_NAME);
	}

	private Update getUtemplateUpdate(String ctime, String templateName) {
		Update update = new Update();
		update.set("ctime", ctime);
		update.set("templateName", templateName);
		return update;
	}

	private Update getPtemplateUpdate(String ctime, String any, String template) {
		Update update = new Update();
		update.set("any", any);
		update.set("ctime", ctime);
		update.set("template", template);
		return update;
	}

}
