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

import com.ginkgocap.ywxt.person.dao.PersonPermissionDao;
import com.ginkgocap.ywxt.person.model.PersonPermission;


@Repository
public class PersonPermissionDaoImpl extends SqlSessionDaoSupport implements
        ApplicationContextAware  ,  PersonPermissionDao {
    private static final String NAMESPACE = "com.ginkgocap.ywxt.person.model.PersonPermission" ;
    private ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
    public int save(PersonPermission personPermission){
        personPermission.setCreatetime(new Date());
        return getSqlSession().insert(NAMESPACE+".insert" , personPermission) ;
    }
    public void batchSave(List<PersonPermission> personPermissions){
        for (PersonPermission personPermission :personPermissions){
            this.save(personPermission) ;
        }
    }
    public int deleteByPersonId(Long peronid){
        return getSqlSession().delete(NAMESPACE+".deleteByPersonId" , peronid) ;
    }
    public int deleteByPerIdAndperType(Long personId , Byte personType){
        Map map = new HashMap()  ;
        map.put("personId" , personId) ;
        map.put("personType" , personType) ;
        return getSqlSession().delete(NAMESPACE + ".deleteByPerIdAndperType", map);
    }
    public List<PersonPermission> findByPersonid(Long personid){
        return getSqlSession().selectList(NAMESPACE+".findByPersonid"  , personid) ;
    }

    public List<PersonPermission> findByPerAndPeTy(Long personId , Byte personType){
        Map map = new HashMap() ;
        map.put("personId" , personId) ;
        map.put("personType" , personType) ;
        return getSqlSession().selectList(NAMESPACE+".findByPerAndPeTy" , map)  ;
    }
    public int deleteByPerson(Long personId, Short personType ,Long sendId ) {
        Map map = new HashMap();
        map.put("personId", personId);
        map.put("personType", personType);
        map.put("isPush" , 0) ;
        map.put("sendId" ,sendId ) ;
        return getSqlSession().delete(NAMESPACE + ".deleteByPerson", map);
    }
    public PersonPermission findByPerAndPeTyAndRec(Long personId , Short personType , Long receiveId){
        Map map = new HashMap() ;
        map.put("personId" , personId) ;
        map.put("personType" , personType) ;
        map.put("receiveId" , receiveId) ;
        return getSqlSession().selectOne(NAMESPACE+".findByPerAndPeTyAndRec" , map) ;
    }
    public int  deleteBySendIdAndPersonId(Long sendId , Long  personId , Byte personType ,Short isPush ){
        Map map = new HashMap() ;
        map.put("sendId",sendId) ;
        map.put("personId" , personId) ;
        map.put("personType" , personType) ;
        map.put("isPush" , isPush) ;
        return getSqlSession().delete(NAMESPACE+".deleteBySendIdAndPersonId" , map) ;

    }
}
