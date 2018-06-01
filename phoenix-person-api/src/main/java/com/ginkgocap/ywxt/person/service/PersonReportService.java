package com.ginkgocap.ywxt.person.service;

import com.ginkgocap.ywxt.person.model.PersonReport;

public interface PersonReportService {
	String COLLECTION_NAME = "report";
	void save(PersonReport report);
}
