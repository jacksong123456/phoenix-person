package com.ginkgocap.ywxt.person.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.person.dao.PersonCountDao;
import com.ginkgocap.ywxt.person.model.PersonCount;

@Repository
public class PersonCountDaoImpl extends SqlSessionDaoSupport implements
        ApplicationContextAware, PersonCountDao {
	
    private ApplicationContext applicationContext;
    private static final String NAMESPACE = "person_count";

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

	public PersonCount getPersonCountById(long personId) {
		Map map = new HashMap();
        map.put("personId", personId);
		return getSqlSession().selectOne(NAMESPACE+".findByPersonId",map);
	}

	public void insertPersonCount(PersonCount personCount) {
		personCount.setType(1);
		getSqlSession().insert(NAMESPACE+".insertPersonCount", personCount);
	}

	public void updatePersonCount(PersonCount personCount) {
		getSqlSession().update(NAMESPACE + ".updatePersonCount", personCount);
	}

   
}
