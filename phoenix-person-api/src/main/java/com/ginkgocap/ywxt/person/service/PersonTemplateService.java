package com.ginkgocap.ywxt.person.service;

import java.util.List;

import com.ginkgocap.ywxt.person.model.Ptemplate;
import com.ginkgocap.ywxt.person.model.Utemplate;

/**
 * zhangzhen 2015-06-03 人脉模板接口
 * */

public interface PersonTemplateService {

	// 保存人脉模板方法
	long insertPeopleTemplate(long peopleid, String any, String template);

	// 修改人脉模板方法
	boolean updatePeopleTemplate(long peopleid, String any, String template);

	// 删除人脉模板方法
	boolean deletePeopleTemplate(long peopleId);

	// 查询人脉模板方法
	Ptemplate getPeopleTemplate(long peopleId);

	// 保存用户模板方法
	long insertUserTemplate(String template,String templateName,long userId);

	// 更新用户模板方法 <主要用于更新模板名称>
	boolean updateUserTemplate(long templateId,String templateName);

	// 删除用户模板方法
	boolean deleteUserTemplateById(long templateId);

	// 删除用户模板方法
	boolean deleteUserTemplateByUserId(long userId);

	// 获取所有当前用户模板
	List<Utemplate> findAllUtemplate(long userId);
}
