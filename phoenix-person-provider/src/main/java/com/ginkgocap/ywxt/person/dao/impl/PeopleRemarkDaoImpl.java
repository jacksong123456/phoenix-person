package com.ginkgocap.ywxt.person.dao.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.person.dao.PeopleRemarkDao;
import com.ginkgocap.ywxt.person.model.PeopleRemark;

@Repository
public class PeopleRemarkDaoImpl  extends SqlSessionDaoSupport implements
ApplicationContextAware, PeopleRemarkDao {

    private ApplicationContext applicationContext;
    private static final String NAMESPACE = "people_remark";
	
	
	
	public void addPeopleRemark(PeopleRemark peopleRemark) {
		// TODO Auto-generated method stub
		
		   getSqlSession().insert(NAMESPACE+".addPeopleRemark", peopleRemark);
	}

	public void updatePeopleRemark(PeopleRemark peopleRemark) {
		// TODO Auto-generated method stub
		 getSqlSession().insert(NAMESPACE+".updatePeopleRemark", peopleRemark);
	}

	public PeopleRemark getPeopleRemark(PeopleRemark peopleRemark) {
		// TODO Auto-generated method stub
		return  getSqlSession().selectOne(NAMESPACE+".getPeopleRemark", peopleRemark);
	}

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}

	public void saveOrUpdate(PeopleRemark peopleRemark) {
		// TODO Auto-generated method stub
		  getSqlSession().insert(NAMESPACE+".saveOrUpdate", peopleRemark);
	}



	
	

}
