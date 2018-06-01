package com.ginkgocap.ywxt.person.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.dao.PersonSimpleDao;
import com.ginkgocap.ywxt.person.model.Basic;
import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonSimple;
import com.ginkgocap.ywxt.person.mongodb.dao.PersonMongodbDao;
import com.ginkgocap.ywxt.person.service.PersonSimpleService;

@Service("personSimpleService")
public class PersonSimpleServiceImpl implements PersonSimpleService {

	@Autowired
	PersonSimpleDao personSimpleDao;
	@Autowired
	PersonMongodbDao personMongodbDao ;

	static Logger logger = LoggerFactory.getLogger(PersonSimpleServiceImpl.class);

	public List<PersonSimple> getMobilePhonesAndFixedPhones(List<PersonSimple> personSimples){
		if (personSimples!=null && personSimples.size()>0) {
			Person person ;
			List<Basic> contactInformationList  ;
			List<Map<String ,String>> mobilePhones  ;
			List<Map<String ,String>> fixedPhones  ;
			Map<String ,String> phoneMap ;
			Byte parentType = null;
			Set<Long> personIds = new HashSet<Long>() ;
			for (PersonSimple personSimpleTmp : personSimples){
				personIds.add(personSimpleTmp.getPersonid()) ;
			}
			List<Person> persons = personMongodbDao.getByIds(personIds) ;
			Map<Long , Person> personMap = new HashMap<Long, Person>() ;
			for (Person personTmp: persons){
				personMap.put(personTmp.getId() , personTmp) ;
			}
			//获得电话号码
			for (PersonSimple personSimple : personSimples) {
				person = personMap.get(personSimple.getPersonid());
				if (person!=null){
					contactInformationList = person.getContactInformationList() ;
					if  (null != contactInformationList &&  contactInformationList.size()> 0){
						//手机号
						mobilePhones = new ArrayList<Map<String ,String>>() ;
						//固定电话
						fixedPhones = new ArrayList<Map<String ,String>>() ;
						for (Basic phone : contactInformationList){
							if (phone.getType()!=null){
								try {
									parentType = Byte.parseByte(phone.getType()) ;
								}catch (Exception e){

								}

							}
							phoneMap = new HashMap<String, String>() ;
							if (parentType!=null){
								phoneMap.put("name" ,"") ;
								phoneMap.put("mobile" ,phone.getContent() ) ;
								if (parentType.equals((byte)1)){
									mobilePhones.add(phoneMap)  ;
								}else{
									fixedPhones.add(phoneMap) ;
								}
							}
						}
						personSimple.setListFixedPhone(fixedPhones);
						personSimple.setListMobilePhone(mobilePhones);
					}
				}
			}
		}
		return personSimples ;
	}


	public List<PersonSimple> findByPeIdsAndPeTypeAndCtime(List<Long> personIds , Integer personType , String updateDate){
		List<PersonSimple> personSimples = personSimpleDao.findByPeIdsAndPeTypeAndCtime(personIds , personType , updateDate);
		return getMobilePhonesAndFixedPhones(personSimples) ;
	}

	public List<PersonSimple> findByPeIdsAndPetypeAndCaIdAndtagId(List<Long> personids ,Short persontype    ,  Long categoryId , Long tagId ){
		if (personids==null || personids.size()==0){
			return null ;
		}
		List<PersonSimple> personSimples =  personSimpleDao.findByPeIdsAndPetypeAndCaIdAndtagId(personids, persontype, categoryId, tagId) ;

		return  personSimples ;
	}



	/**
	 * 10．好友/人脉列表
	 * 
	 * @param categoryId
	 *            目录id
	 * @param tagId
	 *            标签id
	 * @param index
	 *            当前页数
	 * @param size
	 *            几条分页
	 * @return
	 */
/*	public List<PersonSimple> findByCategoryIdAndTid(Long userId,
			Long categoryId, Long tagId, Integer index, Integer size) throws Exception {
        if (index==null || size==null){
            throw new Exception("数据错误") ;
        }
		Page page = new Page();
		page.setSize((long) size);
		page.setPage((long) index);
		List<PersonSimple> personSimples =  this.findByCategoryIdAndTid(userId, categoryId, tagId,
				page.getStartNumber(), page.getSize());

		return  personSimples ;
	}*/

	/**
	 * 保存到人脉简表中
	 * @param personSimple
	 * @return
	 */
	public int savePerson(PersonSimple personSimple) {
		return this.save(personSimple);
	}

	/**
	 * 10．好友/人脉列表
	 * 
	 * @param userId
	 * @param categoryId
	 * @param tagId

	 * @return
	 */
	public List<PersonSimple> findByCategoryIdAndTid(Long userId,
			Long categoryId, Long tagId, String udate ) {
		if (categoryId!=null && 0l==categoryId){
			categoryId=null ;
		}
		if (tagId!=null && 0l==tagId){
			tagId = null ;
		}
		List<PersonSimple> personSimples ;
		if (categoryId != null && tagId != null) {
			personSimples =  personSimpleDao.findByCategoryIdAndTid(userId, categoryId,
					tagId,udate );
		} else if (categoryId == null && tagId == null) {
			personSimples =  personSimpleDao.findByUserId(userId,udate );
		} else if (categoryId != null) {
			personSimples =  personSimpleDao.findByCategory(userId , categoryId ,udate);
		} else {
			personSimples =  personSimpleDao.findByTid(userId, tagId,udate);
		}
		for (PersonSimple personSimple :personSimples){
			try {
				if (personSimple.getCtime().getTime()>personSimple.getCreatetime().getTime()){
					personSimple.setCreatetime(personSimple.getCtime());
				}
			}catch (Exception e){
				logger.error("setCreatetime error:"+e);
			}
		}
		return this.getMobilePhonesAndFixedPhones(personSimples) ;
	}
	/**
	 * 保存简表
	 * @param personSimple
	 * @return
	 */
	private int save(PersonSimple personSimple) {
		personSimple.setCreatetime(new Date());
		return this.personSimpleDao.save(personSimple);
	}

        // 这个方法作废
	public Set<Long> selectPerson(int personType, Long userId, int ctype,
			String keyowrd, int order, String poepleRecordWord,
			long poepleRecordId, int size) {
		return personSimpleDao.selectPerson(personType, userId, ctype, keyowrd, order, poepleRecordWord, poepleRecordId, size);
	}

	public long selectPersonCount(int personType, Long userId, int ctype,
			String keyowrd) {
		return personSimpleDao.selectPersonCount(personType, userId, ctype, keyowrd);
	}

        /**
         * orderRule = 1 sort by time desc , orderRule = 2 sort by pinyin      majingfei
         **/
	public List<Long> selectPerson(int orderRule,int personType, Long userId, int ctype,
			String keyowrd, int order, int page, int size) {
		return personSimpleDao.selectPerson(orderRule,personType, userId, ctype, keyowrd, order,page, size);
	}
	
	
}
