package com.ginkgocap.ywxt.person.mongodb.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonSimple;
import com.ginkgocap.ywxt.person.mongodb.dao.PersonMongodbDao;

@Service
public class PersonMongodbDaoImpl implements PersonMongodbDao {

	public static final String COLLECTION_NAME = "person";

	@Resource(name = "personMongoTemplate")
	private MongoTemplate mongoTemplate;

	public void save(Person person) {
		mongoTemplate.save(person, COLLECTION_NAME);
	}

	public Person get(Long id) {
		return mongoTemplate.findById(id, Person.class);
	}

	public Person getByFromUserIdAndCreateUserId(Long userId) {
		Query query = new Query().addCriteria(new Criteria().and("fromUserId")
				.is(userId).and("createUserId").is(userId));
		return mongoTemplate.findOne(query, Person.class, COLLECTION_NAME);
	}

	public void delete(Long id) {
		Query query = new Query().addCriteria(new Criteria().and("id").is(id));
//		mongoTemplate.remove(query, COLLECTION_NAME);
		mongoTemplate.remove(query, Person.class);
	}

	public Person getByIdAndCreateUserId(Long id, Long createUserId) {
		Query query = new Query().addCriteria(new Criteria().and("id").is(id)
				.and("createUserId").is(createUserId));
		return mongoTemplate.findOne(query, Person.class, COLLECTION_NAME);
	}

	/**
	 * 查找用户createUserId保存的，已转为人脉的用户对应的人脉对象
	 * 
	 * @param userIds
	 *            用户的userId
	 * @param createUserId
	 *            创建人的用户id
	 */
	public List<Person> getUsersByCreateUserId(Collection<Long> userIds,
			Long createUserId) {
		Query query = new Query().addCriteria(new Criteria()
				.and("createUserId").is(createUserId)
				.and("fromUserId").in(userIds)
				.and("virtual").is("1"));
		return mongoTemplate.find(query, Person.class, COLLECTION_NAME);
	}

	public Person getByUserId(Long userId) {
		Query query = new Query().addCriteria(new Criteria().and("fromUserId")
				.is(userId).and("createUserId").is(userId));
		return mongoTemplate.findOne(query, Person.class, COLLECTION_NAME);
	}

	public long getCountByCreateUserId(Long createUserId) {
		Query query = new Query().addCriteria(
				new Criteria().and("createUserId").is(createUserId)).limit(1);
		return mongoTemplate.count(query, COLLECTION_NAME);
	}

	public List<Person> getByNameAndCreateUserId(String name, Long createUserId) {
		Query query = new Query().addCriteria(new Criteria().and("name")
				.is(name).and("createUserId").is(createUserId));
		return mongoTemplate.find(query, Person.class, COLLECTION_NAME);
	}

	public List<Person> getByIds(Collection<Long> ids) {
		if (ids == null || ids.size() == 0) {
			return null;
		}
		Query query = new Query().addCriteria(new Criteria().and("id").in(ids));
		return mongoTemplate.find(query, Person.class, COLLECTION_NAME);
	}

	public void update(Person person) {
		Query query = new Query().addCriteria(new Criteria().and("id").is(
				person.getId()));
		Update update = this.getUpdate(person);
		mongoTemplate.upsert(query, update, COLLECTION_NAME);
	}

	public Update getUpdate(Person person) {
		/*
		 * update.set("updateTime", new Date());
		 */
		// update.set("createUserId", person.getCreateUserId());
		/*
		 * update.set("fromUserId", person.getFromUserId()); update.set("remark"
		 * , person.getRemark()) ; update.set("oldPeopleId",
		 * person.getOldPeopleId()); update.set("position",
		 * person.getPosition()); update.set("pinyin", person.getPinyin());
		 * update.set("taskId", person.getTaskId()); update.set("nameIndex",
		 * person.getNameIndex()); update.set("nameFirst",
		 * person.getNameFirst()); update.set("gender", person.getGender());
		 * update.set("peopleType", person.getPeopleType());
		 * update.set("telephone", person.getTelephone()); update.set("email",
		 * person.getEmail()); update.set("company", person.getCompany());
		 * update.set("address", person.getAddress()); update.set("portrait",
		 * person.getPortrait()); update.set("locationCity",
		 * person.getLocationCity()); update.set("locationCounty",
		 * person.getLocationCounty()); update.set("locationCountry",
		 * person.getLocationCountry()); update.set("careerId",
		 * person.getCareerId()); update.set("regionId", person.getRegionId());
		 * update.set("typeId", person.getTypeId()); update.set("customTagList",
		 * person.getCustomTagList()); update.set("personalInformationList",
		 * person.getPersonalInformationList());
		 * update.set("investmentdemandList", person.getInvestmentdemandList());
		 * update.set("financingdemandList", person.getFinancingdemandList());
		 * update.set("expertdemandList", person.getExpertdemandList());
		 * update.set("expertIdentityList", person.getExpertIdentityList());
		 * update.set("educationList", person.getEducationList());
		 * update.set("workExperienceList", person.getWorkExperienceList());
		 * update.set("socialActivityList", person.getSocialActivityList());
		 * update.set("contactInformationList",
		 * person.getContactInformationList()); update.set("peopleNameList",
		 * person.getPeopleNameList()); update.set("permIds",
		 * person.getPermIds());
		 */

		Update update = new Update();
		// 修改姓和名和First和Last
		update.set("pinyin", person.getPinyin());
		update.set("nameIndex", person.getNameIndex());
		update.set("nameFirst", person.getNameFirst());
		update.set("peopleNameList", person.getPeopleNameList());
		// 修改性别
		update.set("gender", person.getGender());
		// 修改分类
		update.set("typeId", person.getTypeId());
		
		update.set("firstIndustryDirection", person.getFirstIndustryDirection());
		update.set("firstIndustryDirectionId", person.getFirstIndustryDirectionId());
		update.set("secondIndustryDirection", person.getSecondIndustryDirection());
		update.set("secondIndustryDirectionId", person.getSecondIndustryDirectionId());
		
		// 修改职业
		update.set("careerId", person.getCareerId());
		// 修改电话
		update.set("telephone", person.getTelephone());
		// 修改邮箱
		update.set("email", person.getEmail());
		// 修改公司
		update.set("company", person.getCompany());
		// 修改所在地区
		update.set("regionId", person.getRegionId());
		// 修改详细地址
		update.set("address", person.getAddress());
		// 修改备注
		update.set("remark", person.getRemark());
		// 修改权限
		update.set("permIds", person.getPermIds());
		// 城市
		update.set("locationCity", person.getLocationCity());
		// 关联
		update.set("asso", person.getAsso());
		update.set("permissions", person.getPermissions());
		update.set("taskId", person.getTaskId()); 
		// 修改头像
		if (person.getPortrait() != null
				&& !person.getPortrait().startsWith("http://")) {
			update.set("portrait", person.getPortrait());
		}
		// 0-国内；1-国外
		update.set("country", person.getCountry());
		// 自定义项
		update.set("customTagList", person.getCustomTagList());
		// 职位
		update.set("position", person.getPosition());
		// 分类名称
		update.set("peopleType", person.getPeopleType());
		// 所在国家：国外时表示洲，国内时表示省
		update.set("locationCountry", person.getLocationCountry());
		// 国内：县
		update.set("locationCounty", person.getLocationCounty());
		
		update.set("customTemp", person.getCustomTemp());
		
		// 更新个人信息
		update.set("educationList", person.getEducationList());
		update.set("workExperienceList", person.getWorkExperienceList());
		update.set("contactInformationList", person.getContactInformationList());
		update.set("personalInformationList", person.getPersonalInformationList());

		return update;
	}

	public List<Person> selectByCreatorId(long creatorId, int pageIndex,
			int pageSize) {
		int start = (pageIndex - 1) * pageSize;
		int steps = pageSize;
		Criteria c = Criteria.where("createUserId").is(creatorId);
		Query query = new Query(c);
		if (steps > 0) {
			query.skip(start);
			query.limit(steps);
		}
		List<Person> tl = mongoTemplate.find(query, Person.class);
		return tl;
	}

	public List<Person> selectByUserId(long userId, int pageIndex, int pageSize) {
		int start = (int) ((pageIndex - 1) * pageSize);
		int steps = (int) pageSize;
		Criteria cc = Criteria.where("createUserId").is(userId);
		Query query = new Query(cc);
		query.sort().on("createTime", Order.DESCENDING);
		if (pageSize > 0) {
			query.skip(start);
			query.limit(steps);
		}
		return mongoTemplate.find(query, Person.class, COLLECTION_NAME);
	}

	public List<Person> selectMail(String telephone, Long createrId) {
		Criteria criteria = Criteria.where("telephone").is(telephone)
				.where("createUserId").is(createrId).where("fromUserId")
				.ne(createrId);
		Query query = new Query(criteria);
		return mongoTemplate.find(query, Person.class, COLLECTION_NAME);
	}

	public List<Person> discoverPersonList(String region,String industry,List<Long> permissonId,int index,int size) {
		// TODO Auto-generated method stub
		Criteria criteria2 = null;
		Criteria criteria3 = null;
		Criteria criteria1 = null;
		criteria1 = new Criteria().orOperator(Criteria.where("virtual").is("1"),Criteria.where("permissions.publicFlag").is(1));
	    if(StringUtils.isNotEmpty(region)){
	    	criteria2 = new Criteria().orOperator(Criteria.where("locationCity").is(region),Criteria.where("locationCounty").is(region));
	    }
	    if(StringUtils.isNotEmpty(industry)){
	    	criteria3 = new Criteria().orOperator(Criteria.where("firstIndustryDirection").is(industry),Criteria.where("secondIndustryDirection").is(industry));
	    }
		Criteria criteria4 = null;
		if(criteria2==null){
			if(criteria3==null){
				criteria4= new Criteria().andOperator(criteria1);	
			}else{
				criteria4= new Criteria().andOperator(criteria1,criteria3);
			}
		}else if(criteria2!=null){
			if(criteria3==null){
				criteria4= new Criteria().andOperator(criteria1,criteria2);	
			}else{
				criteria4= new Criteria().andOperator(criteria1,criteria3,criteria2);
			}
		}

		Query query = new Query(criteria4);
		query.sort().on("createTime", Order.DESCENDING);
		if (index > 0) {
			query.skip((index-1)*size);
			query.limit(size);
		}
		return mongoTemplate.find(query, Person.class, COLLECTION_NAME);
	}

	public Long discoverPersonCount(String region,String industry,List<Long> permissonId) {
		// TODO Auto-generated method stub	
		Criteria criteria2 = null;
		Criteria criteria3 = null;
		Criteria criteria1 = null;
		criteria1 = new Criteria().orOperator(Criteria.where("virtual").is("1"),Criteria.where("permissions.publicFlag").is(1));
	    if(StringUtils.isNotEmpty(region)){
	    	criteria2 = new Criteria().orOperator(Criteria.where("locationCity").is(region),Criteria.where("locationCounty").is(region));
	    }
	    if(StringUtils.isNotEmpty(industry)){
	    	criteria3 = new Criteria().orOperator(Criteria.where("firstIndustryDirection").is(industry),Criteria.where("secondIndustryDirection").is(industry));
	    }
		Criteria criteria4 = null;
		if(criteria2==null){
			if(criteria3==null){
				criteria4= new Criteria().andOperator(criteria1);	
			}else{
				criteria4= new Criteria().andOperator(criteria1,criteria3);
			}
		}else if(criteria2!=null){
			if(criteria3==null){
				criteria4= new Criteria().andOperator(criteria1,criteria2);	
			}else{
				criteria4= new Criteria().andOperator(criteria1,criteria3,criteria2);
			}
		}

		Query query = new Query(criteria4);
		return mongoTemplate.count(query, Person.class);
	}

	@Override
	public List<Person> listAddressBook(long userId, int mailType) {
		// TODO Auto-generated method stub
		Criteria criteria2 = Criteria.where("mailType").is(mailType);
		Criteria criteria3 = Criteria.where("createUserId").is(userId);
		Criteria criteria1 = new Criteria().andOperator(criteria2,criteria3);
		Query query = new Query(criteria1);
		query.sort().on("createTime", Order.ASCENDING);
		return mongoTemplate.find(query, Person.class, COLLECTION_NAME);
	}

	@Override
	public boolean isExist(long userId, int mailType, String mobile) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if(StringUtils.isNotEmpty(mobile)){
			Criteria criteria2 = null;
			Criteria criteria3 = null;
			Criteria criteria1 = null;
			criteria1 = Criteria.where("mailType").is(mailType);
			criteria2 = Criteria.where("createUserId").is(userId);
			criteria3 = Criteria.where("contactInformationList.content").is(mobile);
			Criteria criteria = new Criteria().andOperator(criteria1,criteria2,criteria3);
			Query query = new Query(criteria);
			Person person = mongoTemplate.findOne(query,Person.class,COLLECTION_NAME);
			if(person!=null){
				flag=true;
			}
		}
		return flag;
	}

	@Override
	public Person getPersonByCreateUserId(long createUserId, String virtual) {
		Query query = new Query().addCriteria(new Criteria().and("createUserId")
				.is(createUserId).and("virtual").is(virtual));
		return mongoTemplate.findOne(query, Person.class, COLLECTION_NAME);
	}
}
