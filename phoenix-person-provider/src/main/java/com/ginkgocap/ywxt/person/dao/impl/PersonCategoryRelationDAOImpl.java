package com.ginkgocap.ywxt.person.dao.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.person.dao.PersonCategoryRelationDAO;
import com.ginkgocap.ywxt.person.model.PersonCategoryRelation;

@Repository("personCategoryRelationDAO")
public class PersonCategoryRelationDAOImpl extends SqlSessionDaoSupport implements
        ApplicationContextAware, PersonCategoryRelationDAO {
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void createPersonCategoryRelation(PersonCategoryRelation personCategoryRelation) {
        getSqlSession().insert("tb_r_person_category.insertPersonCategoryRelation", personCategoryRelation);
    }

    public void createPersonCategoryRelationBatch(List<PersonCategoryRelation> personCategoryRelationList) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", personCategoryRelationList);
        getSqlSession().insert("tb_r_person_category.insertPersonCategoryRelationBatch", map);
    }

    public void updatePersonCategoryRelation(Map<String, Object> parameter) {
        getSqlSession().update("tb_r_person_category.updatePersonCategoryRelation", parameter);
    }

    public List<PersonCategoryRelation> selectFavoriteRelation(Map<String, Object> param) {
        return getSqlSession().selectList("tb_r_person_category.selectFavoriteRelation", param);
    }

    public void cancelFavorite(PersonCategoryRelation relation) {
        getSqlSession().delete("tb_r_person_category.cancelFavoriteRelation", relation);
    }

    public void batchCreate(List<PersonCategoryRelation> personCategoryRelations) throws Exception {
        for (PersonCategoryRelation personCategoryRelation : personCategoryRelations) {
            if (personCategoryRelation.getCategoryId() == null || personCategoryRelation.getCategoryId().equals(0)) {
                throw new Exception("CategoryId数据错误");
            }
            getSqlSession().insert("tb_r_person_category.insertPersonCategoryRelation", personCategoryRelation);
        }
    }

    public int deleteByPerIdAndperType(Long personId, Integer personType) {
        Map map = new HashMap();
        map.put("personId", personId);
        map.put("personType", personType);
        return getSqlSession().delete("tb_r_person_category.deleteByPerIdAndperType", map);
    }

    public int deleteByPerIdAndperTypeAndCtype(Long personId, Integer personType, Integer ctype) {
        Map map = new HashMap();
        map.put("personId", personId);
        map.put("personType", personType);
        map.put("ctype", ctype);
        return getSqlSession().delete("tb_r_person_category.deleteByPerIdAndperType", personId);

    }

    public int deleteByPerIdAndperTyAndCtyAndUid(Long personId, Integer personType, Integer ctype, Long userId) {
        Map map = new HashMap();
        map.put("personId", personId);
        map.put("personType", personType);
        map.put("ctype", ctype);
        map.put("userId", userId);
        return getSqlSession().delete("tb_r_person_category.deleteByPerIdAndperTyAndCtyAndUid", map);

    }

    public List<PersonCategoryRelation> selectByUidAndPerAndPerTy(Long userId, Long personId, Byte personType) {
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("personId", personId);
        map.put("personType", personType);
        return getSqlSession().selectList("tb_r_person_category.selectByUidAndPerAndPerTy", map);
    }
    public int deleteByPerIdAndPerTypeAndUid(Long personId, Integer personType , Long userId){
        Map map = new HashMap();
        map.put("personId", personId);
        map.put("personType", personType);
        map.put("userId", userId);
        return getSqlSession().delete("tb_r_person_category.deleteByPerIdAndPerTypeAndUid", map);

    }

    public Long selectUserCount(Integer personType , Long userId) {
        Map map = new HashMap();
        map.put("personType", personType);
        map.put("userId", userId);
        Map<String  ,Long > countMap =  getSqlSession().selectOne("tb_r_person_category.selectUserCount", map);
        return countMap.get("count");
    }
    
    public Set<Long> selectUser(Integer personType , Long userId) {
        Map map = new HashMap();
        map.put("personType", personType);
        map.put("userId", userId);
        List<Map<String  ,Long >> userMaps =  getSqlSession().selectList("tb_r_person_category.selectUser", map);
        Set<Long> personIds = new HashSet<Long>() ;
        for (Map<String  ,Long > map1:userMaps){
            personIds.add(map1.get("person_id"));
        }
        return personIds;
    }
    public Set<Long> selectCollectUser(Long userId) {
    	Map map = new HashMap();
    	map.put("userId", userId);
    	List<Map<String  ,Long >> userMaps =  getSqlSession().selectList("tb_r_person_category.selectCollectUser", map);
    	Set<Long> personIds = new HashSet<Long>() ;
    	for (Map<String  ,Long > map1:userMaps){
    		personIds.add(map1.get("person_id"));
    	}
    	return personIds;
    }
    public Set<Long> selectPersonUser(Long userId) {
    	Map map = new HashMap();
    	map.put("userId", userId);
    	List<Map<String  ,Long >> userMaps =  getSqlSession().selectList("tb_r_person_category.selectPersonUser", map);
    	Set<Long> personIds = new HashSet<Long>() ;
    	for (Map<String  ,Long > map1:userMaps){
    		personIds.add(map1.get("person_id"));
    	}
    	return personIds;
    }
    
    public List<Long> selectAll() {
        Map map = new HashMap();
        List<Long> userList =  getSqlSession().selectList("tb_r_person_category.selectAll", map);
        return userList;
    }
    public List<Map<String, Object>> selectPersonCategories(Long userId, Long personId, Byte personType) {
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("personId", personId);
        map.put("personType", personType);
        return getSqlSession().selectList("tb_person_category.selectPersonCategories", map);
    }

	public long selectCountByPersonIdForUserId(Map<String,Object> map) {
		return (Long)getSqlSession().selectOne("tb_r_person_category.selectCountByPersonIdForUserId",map);
	}

	@Override
	public Set<Long> selectAllPerson(Long userId) {
		// TODO Auto-generated method stub
    	Map map = new HashMap();
    	map.put("userId", userId);
    	List<Map<String  ,Long >> userMaps =  getSqlSession().selectList("tb_r_person_category.selectAllPerson", map);
    	Set<Long> personIds = new HashSet<Long>() ;
    	for (Map<String  ,Long > map1:userMaps){
    		personIds.add(map1.get("person_id"));
    	}
    	return personIds;
	}

	/**
	 * 通过目录id 查找目录的 近资源
	 */
	@Override
	public long countPersonByCategoryId(Long id,Long userId) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("categoryId", id);
		 Map<String  ,Long > countMap = getSqlSession().selectOne("tb_r_person_category.selectPersonCount", map);
		return countMap.get("count");
	}
}
