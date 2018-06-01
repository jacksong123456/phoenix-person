package com.ginkgocap.ywxt.person.service.impl;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.MongoIds;
import com.ginkgocap.ywxt.person.service.PersonIdService;

@Service("personIdService")
public class PersonIdServiceImpl implements PersonIdService {
	@Resource(name = "personMongoTemplate")
    private MongoTemplate organMongoTemplate;

	/**
	 * 获取会面id
	 * @return
	 */
	public long getCustomerId() {
		Criteria c = Criteria.where("name").is("MeetId");
		Update update =new Update();
		Query query = new Query(c);
		update.inc("cid", 1);
		String collectionName = "MongoIds";
		MongoIds mongoiId = organMongoTemplate.findAndModify(query, update, MongoIds.class, collectionName);
		if(mongoiId==null){
			mongoiId = new MongoIds();
			mongoiId.setCid(1l);
			mongoiId.setName("MeetId");
			organMongoTemplate.insert(mongoiId, collectionName);
		}
		return mongoiId.getCid();
	}

}
