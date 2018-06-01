package com.ginkgocap.ywxt.person.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.person.dao.PersonTagDao;
import com.ginkgocap.ywxt.person.model.PersonTag;

/**
 * 标签的DAO实现类
 * @author hdy
 * @date 2014-10-21
 */
@Repository
public class PersonTagDaoImpl extends SqlSessionDaoSupport implements ApplicationContextAware,PersonTagDao {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    
    public PersonTag selectByPrimarKey(Long id) {
        return (PersonTag) getSqlSession().selectOne("tb_person_tags.selectByPrimaryKey", id);
    }

    
    public PersonTag insert(PersonTag personTag) {
        Integer result = getSqlSession().insert("tb_person_tags.insertUserTag", personTag);
     	return  personTag ;
    }

    
    public void update(PersonTag personTag) {
        getSqlSession().update("tb_person_tags.updateUserTag", personTag);
    }

    
    public void delete(Long id) {
        getSqlSession().delete("tb_person_tags.deleteUserTag", id);
    }

	
	public void deleteByTagNameAndUserId(Long userId, String tagName) {
		// TODO Auto-generated method stub
		  Map<String, Object> map = new HashMap<String, Object>();
	        map.put("userId", userId);
	        map.put("tagName", tagName);
	        getSqlSession().delete("tb_person_tags.deleteByTagNameAndUserId", map);
		
	}
	
	public List<PersonTag> selectByUserId(Long userId, Long startRow, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
	     map.put("startRow", startRow);
	     map.put("pageSize", pageSize);
		return getSqlSession().selectList("tb_person_tags.selectByUserId", map);
	}
	/**
	 * 根据用户id,标签名查找
	 */
	
	public List<PersonTag> selectByTagNameAndUserId(Long userId, String tagName) {
		// TODO Auto-generated method stub
		  Map<String, Object> map = new HashMap<String, Object>();
	        map.put("userId", userId);
	        map.put("tagName", tagName);
		return getSqlSession().selectList("tb_person_tags.selectByTagNameAndUserId", map);
	}
	/**
	 * 根据用户id获得系统和用户的标签列表
	 */
	
	public List<PersonTag> selectByUserIdAndSys(Long userId, Long startRow,int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
	     map.put("startRow", startRow);
	     map.put("pageSize", pageSize);
		return getSqlSession().selectList("tb_person_tags.selectByUserIdAndSys", map);
	}
	
	public Long countByUserId(Long userId) {
		// TODO Auto-generated method stub
		return  (Long) getSqlSession().selectOne("tb_person_tags.countByUserId", userId);

	}
	
	public Long countByUserIdAndSys(Long userId) {
		// TODO Auto-generated method stub
		return  (Long) getSqlSession().selectOne("tb_person_tags.countByUserIdAndSys", userId);
	}
	
	public List<PersonTag> selectTagNameInUserIdAndSys(Long userId, String tagName) {
		// TODO Auto-generated method stub
		  Map<String, Object> map = new HashMap<String, Object>();
	        map.put("userId", userId);
	        map.put("tagName", tagName);
		return getSqlSession().selectList("tb_person_tags.selectTagNameInUserIdAndSys", map);
	}
	
	public List<PersonTag> searchPrefixPageByUserIdAndSys(Long userId,
			String[] keyword, Long startRow, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("startRow", startRow);
	     map.put("pageSize", pageSize);
	     map.put("keywordArray", keyword);
		return getSqlSession().selectList("tb_person_tags.searchPrefixPageByUserIdAndSys", map);
	}
	
	public List<PersonTag> searchContainPageByUserIdAndSys(Long userId,
			String[] keyword, Long startRow, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("startRow", startRow);
	     map.put("pageSize", pageSize);
	     map.put("keywordArray", keyword);
		return getSqlSession().selectList("tb_person_tags.searchContainPageByUserIdAndSys", map);
	}
	
	public Long countBySys() {
		// TODO Auto-generated method stub
		return (Long) getSqlSession().selectOne("tb_person_tags.countBySys");
	}
	
	public List<PersonTag> selectPageBySys(Long startRow, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", startRow);
	    map.put("pageSize", pageSize);
		return getSqlSession().selectList("tb_person_tags.selectPageBySys", map);
	}
	public List<PersonTag> selectPageByUserId(Long userId  ,  Long startRow, int pageSize){
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId==null){
			userId=0l ;
		}
		map.put("userId", userId);
		map.put("startRow", startRow);
		map.put("pageSize", pageSize);
		return getSqlSession().selectList("tb_person_tags.selectPageByUserId", map);
	}
	public Integer selectCountPageByUserId(Long userId){
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId==null){
			userId=0l ;
		}
		map.put("userId", userId);
		return ((Long)getSqlSession().selectOne("tb_person_tags.selectCountPageByUserId", map)).intValue();
	}

	
	public List<PersonTag> selectByIds(Set<Long> ids) {
		if (ids==null || ids.size()==0){
			return null  ; 
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids" , StringUtils.join(ids, " , "));
		return getSqlSession().selectList("tb_person_tags.selectByIds", map);
	}
	public List<PersonTag> selectCountByIds(Set<Long> ids){
		if (ids==null || ids.size()==0){
			return null  ;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids" , StringUtils.join(ids, " , "));
		return getSqlSession().selectList("tb_person_tags.selectCountByIds", map);
	}

}
