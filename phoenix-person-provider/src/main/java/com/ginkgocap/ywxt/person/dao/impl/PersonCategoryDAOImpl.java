package com.ginkgocap.ywxt.person.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.person.dao.PersonCategoryDAO;
import com.ginkgocap.ywxt.person.model.PersonCategory;

@Repository("personCategoryDAO")
public class PersonCategoryDAOImpl extends SqlSessionDaoSupport implements
		ApplicationContextAware, PersonCategoryDAO {
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public PersonCategory findById(Long id) {
		return (PersonCategory) getSqlSession().selectOne(
				"tb_person_category.findCategoryById", id);
	}

	public void createPersonCategory(PersonCategory personCategory) {
		getSqlSession().insert("tb_person_category.insertPersonCategory",
				personCategory);
	}

	public void deletePersonCategory(String ids) {
		getSqlSession().delete("tb_person_category.deletePersonCategory", ids);
	}

	public void updatePersonCategory(PersonCategory personCategory) {
		getSqlSession().delete("tb_person_category.updatePersonCategory",
				personCategory);
	}

	public Long selectParentCategroy(Long id) {
		return getSqlSession().selectOne(
				"tb_person_category.selectParentCategory", id);
	}

	public PersonCategory findCategory(PersonCategory personCategory) {
		return getSqlSession().selectOne("tb_person_category.findCategory",
				personCategory);
	}

	public String findCategoryIds(PersonCategory personCategory) {
		return getSqlSession().selectOne("tb_person_category.findCategoryIds",
				personCategory);
	}

	public List<PersonCategory> selectCategory(Map<String, Object> parameter) {
		return getSqlSession().selectList("tb_person_category.selectCategory",
				parameter);
	}

	public List<PersonCategory> selectCategoryBySortId(String sortId) {
		return getSqlSession().selectList(
				"tb_person_category.selectCategoryBySortId", sortId);
	}

	public List<PersonCategory> selectCategoryByPid(Long pid) {
		return getSqlSession().selectList(
				"tb_person_category.selectCategoryByPid", pid);
	}

	public List<PersonCategory> findCategoryByEntity(Map<String,Object> map) {
		return getSqlSession().selectList(
				"tb_person_category.findCategoryByEntity", map);
	}

	public Long findLastId() {
		return getSqlSession().selectOne("tb_person_category.findLastId");
	}
	public List<Map<String, Object>> selectCategories(Collection<Long> categoryIds) {
		if (null == categoryIds || categoryIds.isEmpty())
			return new ArrayList<Map<String, Object>>(1);
		Map map = new HashMap();
		StringBuilder sb = new StringBuilder();
		for (Long id : categoryIds) {
			if (sb.length() > 0) sb.append(",");
			sb.append(id);
		}

		map.put("ids", sb.toString());
		return getSqlSession().selectList("tb_person_category.selectCategories", map);
	}

	public List<PersonCategory> selectCategoryForPage(
			Map<String, Object> parameter) {
		return getSqlSession().selectList("tb_person_category.findCategoryByEntityForPage",
				parameter);
	}

	public Long countCategoryForPage(Map<String, Object> parameter) {
		return getSqlSession().selectOne("tb_person_category.countCategoryByEntityForPage",
				parameter);
	}

	public Long countCategoryByCategoryName(Map<String, Object> parameter) {
		return getSqlSession().selectOne("tb_person_category.countCategoryByCategoryName",parameter);
	}
}
