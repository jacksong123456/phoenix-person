package com.ginkgocap.ywxt.person.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.person.dao.PersonTagDao;
import com.ginkgocap.ywxt.person.dao.PersonTagRelationDao;
import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonTag;
import com.ginkgocap.ywxt.person.model.PersonTagRelation;
import com.ginkgocap.ywxt.person.mongodb.dao.PersonMongodbDao;
import com.ginkgocap.ywxt.person.service.PersonTagService;

@Service("personTagService")
public class PersonTagServiceImpl implements PersonTagService {

	@Autowired
	private PersonTagDao personTagDao;

	@Autowired
	private PersonMongodbDao personMongodbDao;

	@Autowired
	private PersonTagRelationDao personTagRelationDao;

	private Logger logger = LoggerFactory.getLogger(getClass());

	
	public PersonTag selectById(Long id) {
		return personTagDao.selectByPrimarKey(id);
	}

	
	public PersonTag insert(PersonTag personTag) {
		// TODO Auto-generated method stub
		List<PersonTag> list = personTagDao.selectByTagNameAndUserId(personTag.getUserId(), personTag.getTagName());
		//如果用户已经存在标签 ，不保存
		if(list.size() > 0) {
			PersonTag pt = list.get(0);
			pt.setUserId(-1l);
			return pt;
		} 
		return personTagDao.insert(personTag);
	}



	
	public void update(PersonTag personTag) {
		// TODO Auto-generated method stub
		personTagDao.update(personTag);
		return ;

	}


	
	public void delete(Long id) {
		// TODO Auto-generated method stub
		personTagDao.delete(id);
		personTagRelationDao.deleteByTagId(id);
	}


	
	public void deleteByTagNameAndUserId(Long userId, String tagName) {
		// TODO Auto-generated method stub
		personTagDao.deleteByTagNameAndUserId(userId, tagName);

	}

	
	public List<PersonTag> selectByNameAndUserId(Long userId, String tagName) {
		// TODO Auto-generated method stub
		return personTagDao.selectByTagNameAndUserId(userId, tagName);
	}
	/**
	 * 根据用户id查询分页
	 */
	
	public List<PersonTag> selectPageByUserId(Long userId, Long startRow,
											  int pageSize) {
		// TODO Auto-generated method stub
		return 	personTagDao.selectByUserId(userId, startRow, pageSize);
	}

	
	public List<PersonTag> selectPageByUserIdAndSys(Long userId, Long startRow,
													int pageSize) {
		// TODO Auto-generated method stub
		return personTagDao.selectByUserIdAndSys(userId, startRow, pageSize);
	}

	
	public Long countByUserId(Long userId) {
		// TODO Auto-generated method stub
		Long count = personTagDao.countByUserId(userId);
		return count;
	}

	
	public Long countByUserIdAndSys(Long userId) {
		// TODO Auto-generated method stub
		Long count = personTagDao.countByUserIdAndSys(userId);
		return count;
	}

	
	public List<PersonTag> selectTagNameInUserIdAndSys(Long userId, String tagName) {
		// TODO Auto-generated method stub
		return personTagDao.selectTagNameInUserIdAndSys(userId, tagName);
	}

	
	public List<PersonTag> searchPrefixPageByUserIdAndSys(Long userId,
														  String[] keyword, Long startRow, int pageSize) {
		// TODO Auto-generated method stub
		return personTagDao.searchPrefixPageByUserIdAndSys(userId, keyword, startRow, pageSize);
	}

	
	public List<PersonTag> searchContainPageByUserIdAndSys(Long userId, String[] keyword, Long startRow, int pageSize) {
		return personTagDao.searchContainPageByUserIdAndSys(userId, keyword, startRow, pageSize);
	}

	
	@Transactional(rollbackFor = Exception.class)
	public List<PersonTag> insert(List<PersonTag> personTagList) {
		List<PersonTag> personTags = new ArrayList<PersonTag>() ;

		for (int i = 0; i < personTagList.size(); i++) {
			personTags.add(this.insert(personTagList.get(i)));
		}
		return personTags;
	}

	
	public Long countBySys() {
		// TODO Auto-generated method stub
		return personTagDao.countBySys();
	}

	
	public List<PersonTag> selectPageBySys(Long startRow, int pageSize) {
		// TODO Auto-generated method stub
		return personTagDao.selectPageBySys(startRow, pageSize);
	}


	public List<PersonTag> selectByUserId(Long userId  ,  Long startRow, int pageSize) {
		// TODO Auto-generated method stub
		return personTagDao.selectPageByUserId(userId ,  startRow, pageSize);
	}

	
	public Integer selectCountPageByUserId(Long userId) {
		return personTagDao.selectCountPageByUserId(userId);
	}

	
	public List<Person> findPersonByTag(Long userId, Long tagId, Long startRow, int pageSize) {
		List<PersonTagRelation> personIds = personTagRelationDao.findPersonIdByTag(userId, tagId, startRow, pageSize);
		Set<Long> ids = new HashSet<Long>();
		for(PersonTagRelation personTagRelation : personIds){
			ids.add(personTagRelation.getPersonId());
		}
		return personMongodbDao.getByIds(ids);
	}

	
	public Integer selectPersonCountByTag(Long userId, Long tagId) {
		Integer count = personTagRelationDao.selectPersonCountByTag(userId, tagId);
		if(null == count){
			return 0;
		}
		return count;
	}
	
	public List<PersonTag> selectByIds(Set<Long> ids) {
		return personTagDao.selectByIds(ids);
	}

	
	public List<PersonTag> selectCountByIds(Set<Long> ids) {
		return personTagDao.selectCountByIds(ids);
	}

}
