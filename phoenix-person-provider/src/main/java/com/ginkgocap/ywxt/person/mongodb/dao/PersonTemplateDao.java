package com.ginkgocap.ywxt.person.mongodb.dao;

import java.util.List;

import com.ginkgocap.ywxt.person.model.Ptemplate;
import com.ginkgocap.ywxt.person.model.Utemplate;

public interface PersonTemplateDao {

	long insertPeopleTemplate(Ptemplate template);

	boolean updatePeopleTemplate(long pid,String ctime,String any, String template);

	boolean deletePeopleTemplate(long peopleId);

	Ptemplate getPeopleTemplate(long peopleId);

	long insertUserTemplate(Utemplate utemplate);

	boolean updateUserTemplate(String ctime,long templateId, String templateName);

	boolean deleteUserTemplateById(long templateId);

	boolean deleteUserTemplateByUserId(long userId);

	List<Utemplate> findAllUtemplate(long userId);

}
