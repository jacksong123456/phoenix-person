package com.ginkgocap.ywxt.person.service.impl;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.model.PersonReport;
import com.ginkgocap.ywxt.person.service.PersonReportService;

@Service("personReportService")
public class PersonReportServiceImpl implements PersonReportService {

	@Resource(name = "personMongoTemplate")
	private MongoTemplate personMongoTemplate;

	public void save(PersonReport report) {
		personMongoTemplate.save(report, COLLECTION_NAME);
	}
}
