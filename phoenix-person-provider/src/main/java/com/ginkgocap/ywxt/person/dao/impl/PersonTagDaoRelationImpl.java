package com.ginkgocap.ywxt.person.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.person.dao.PersonTagRelationDao;
import com.ginkgocap.ywxt.person.model.PersonTagRelation;
@Repository
public class PersonTagDaoRelationImpl extends SqlSessionDaoSupport implements
        ApplicationContextAware, PersonTagRelationDao {

    private ApplicationContext applicationContext;

    private static final String NAMESPACE = "tb_r_person_tag";

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    public int save(PersonTagRelation personTag) {

        return getSqlSession().insert(NAMESPACE + ".insert", personTag);
    }

    public int update(PersonTagRelation personTag) {
        return getSqlSession().update(NAMESPACE + ".update", personTag);
    }

    public int deleteByPersonId(Long personid) {
        return getSqlSession().delete(NAMESPACE + ".deleteByPersonId", personid);
    }

    public int deleteByPerIdAndperType(Long personId, Byte personType) {
        Map map = new HashMap();
        map.put("personId", personId);
        map.put("personType", personType);
        return getSqlSession().delete(NAMESPACE + ".deleteByPerIdAndperType", map);
    }

    public List<PersonTagRelation> findByPerAndPt(Long personid, Byte personType) {
        Map map = new HashMap();
        map.put("personId", personid);
        map.put("personType", personType);
        return getSqlSession().selectList(NAMESPACE + ".findByPerAndPt", map);
    }

    public List<PersonTagRelation> findPersonIdByTag(Long userId, Long tagId, Long startRow, int pageSize) {
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("tagId", tagId);
        map.put("startRow", startRow);
        map.put("pageSize", pageSize);
        return getSqlSession().selectList(NAMESPACE + ".selectPersonBytagId", map);
    }

    public Integer selectPersonCountByTag(Long userId, Long tagId){
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("tagId", tagId);
        return ((Long)getSqlSession().selectOne(NAMESPACE+".selectPersonCountByTag", map)).intValue();
    }

    /**
     * 根据标签ID删除人脉标签关联数据
     * @param tagId
     * @return
     */
    public int deleteByTagId(Long tagId) {
        return getSqlSession().delete(NAMESPACE + ".deleteByTagId", tagId);
    }
}
