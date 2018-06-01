package com.ginkgocap.ywxt.person.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.person.dao.PersonMeetDao;
import com.ginkgocap.ywxt.person.model.PersonMeet;


@Repository
public class PersonMeetDaoImpl  extends SqlSessionDaoSupport implements
        ApplicationContextAware,PersonMeetDao {
    private ApplicationContext applicationContext;
    private static final String NAMESPACE=  "com.ginkgocap.ywxt.person.model.PersonMeet" ;
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
    public List<PersonMeet> findByPersonid(Long personid){
        return getSqlSession().selectList(NAMESPACE+".findByPersonid"  , personid) ;
    }
    public PersonMeet findByCidAndMid(Long cid , Long meetId){
        Map map = new HashMap() ;
        map.put("cid" , cid) ;
        map.put("meetId" , meetId) ;
        return getSqlSession().selectOne(NAMESPACE+".findByCidAndMid" , map) ;
    }


    public int deleteByCidAndMeetId(Long cid , Long meetId){
        Map map = new HashMap() ;
        map.put("cid" , cid) ;
        map.put("meetId" , meetId) ;
        return getSqlSession().delete(NAMESPACE+".deleteByCidAndMeetId" , map) ;
    }
    public int save(PersonMeet personMeet){
        personMeet.setCtime(new Date());
        return getSqlSession().insert(NAMESPACE+".insert" , personMeet) ;
    }

    public int updateByCidAndMeetId(PersonMeet personMeet){
        return getSqlSession().update(NAMESPACE+".updateByMeetIdAndCid"  , personMeet);
    }
    public int deleteByPersonId(Long personId){
        return getSqlSession().delete(NAMESPACE+".deleteByPersonId" ,personId) ;
    }
    public int deleteByPerIdAndperType(Long personId , Byte personType){
        Map map = new HashMap()  ;
        map.put("personId" , personId) ;
        map.put("personType" , personType) ;
        return getSqlSession().insert(NAMESPACE+".deleteByPerIdAndperType", map);
    }
}
