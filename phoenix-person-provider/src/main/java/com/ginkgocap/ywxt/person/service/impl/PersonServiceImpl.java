package com.ginkgocap.ywxt.person.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.metadata.model.CodeRegion;
import com.ginkgocap.ywxt.metadata.service.region.CodeRegionService;
import com.ginkgocap.ywxt.person.MongoIds;
import com.ginkgocap.ywxt.person.dao.CustomerMeetDao;
import com.ginkgocap.ywxt.person.dao.PersonCategoryRelationDAO;
import com.ginkgocap.ywxt.person.dao.PersonPermissionDao;
import com.ginkgocap.ywxt.person.dao.PersonSimpleDao;
import com.ginkgocap.ywxt.person.dao.PersonTagRelationDao;
import com.ginkgocap.ywxt.person.model.Address;
import com.ginkgocap.ywxt.person.model.Basic;
import com.ginkgocap.ywxt.person.model.Education;
import com.ginkgocap.ywxt.person.model.FamilyMember;
import com.ginkgocap.ywxt.person.model.ForeignLanguage;
import com.ginkgocap.ywxt.person.model.IndustryDirection;
import com.ginkgocap.ywxt.person.model.Intention;
import com.ginkgocap.ywxt.person.model.PeopleActivity;
import com.ginkgocap.ywxt.person.model.PeopleAddress;
import com.ginkgocap.ywxt.person.model.PeopleColleagueRelationship;
import com.ginkgocap.ywxt.person.model.PeopleCommunityRelationship;
import com.ginkgocap.ywxt.person.model.PeopleContactComm;
import com.ginkgocap.ywxt.person.model.PeopleDemandCommon;
import com.ginkgocap.ywxt.person.model.PeopleEducation;
import com.ginkgocap.ywxt.person.model.PeopleForeignLanguage;
import com.ginkgocap.ywxt.person.model.PeopleImportantDate;
import com.ginkgocap.ywxt.person.model.PeopleName;
import com.ginkgocap.ywxt.person.model.PeoplePersonalLine;
import com.ginkgocap.ywxt.person.model.PeoplePersonalPlate;
import com.ginkgocap.ywxt.person.model.PeopleSelectTag;
import com.ginkgocap.ywxt.person.model.PeopleSocialActivity;
import com.ginkgocap.ywxt.person.model.PeopleStudentsRelationship;
import com.ginkgocap.ywxt.person.model.PeopleTemp;
import com.ginkgocap.ywxt.person.model.PeopleWorkExperience;
import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonCategoryRelation;
import com.ginkgocap.ywxt.person.model.PersonName;
import com.ginkgocap.ywxt.person.model.PersonSimple;
import com.ginkgocap.ywxt.person.model.PersonTagRelation;
import com.ginkgocap.ywxt.person.model.PersonalInformation;
import com.ginkgocap.ywxt.person.model.SocialActivity;
import com.ginkgocap.ywxt.person.model.TypeTag;
import com.ginkgocap.ywxt.person.model.WorkExperience;
import com.ginkgocap.ywxt.person.mongodb.dao.PersonMongodbDao;
import com.ginkgocap.ywxt.person.service.CodeSortService;
import com.ginkgocap.ywxt.person.service.PersonCategoryRelationService;
import com.ginkgocap.ywxt.person.service.PersonService;
import com.ginkgocap.ywxt.person.service.PersonSimpleService;
import com.ginkgocap.ywxt.person.service.PersonTagRelationService;
import com.ginkgocap.ywxt.person.thread.NoticeThreadPool;
import com.ginkgocap.ywxt.person.util.Page;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.user.service.UserConfigService;
import com.ginkgocap.ywxt.user.service.UserService;
import com.ginkgocap.ywxt.util.HttpClientHelper;
import com.ginkgocap.ywxt.util.MakeTaskId;
import com.ginkgocap.ywxt.util.PinyinUtils;
import com.gintong.rocketmq.api.DefaultMessageService;
import com.gintong.rocketmq.api.enums.TopicType;
import com.gintong.rocketmq.api.model.RocketSendResult;
import com.gintong.rocketmq.api.utils.FlagTypeUtils;

//import com.ginkgocap.ywxt.person.mongodb.dao.PeopleMongodbDao;
/*import com.ginkgocap.ywxt.demand.util.DemandConstants;*/

@Service("personService")
public class PersonServiceImpl implements PersonService
{
	static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	public static final Long NO_GROUP_ID = -1l;
	Lock lock = new ReentrantLock();
	@Autowired
	private PersonMongodbDao personMongodbDao;
	@Autowired
	private PersonTagRelationDao personTagDao;
	@Autowired
	private PersonPermissionDao personPermissionDao;
	@Resource(name = "personMongoTemplate")
	private MongoTemplate personMongoTemplate;
	@Resource(name = "oldPeopleTemplate")
	private MongoTemplate oldPeopleTemplate;
	@Autowired
	private PersonSimpleService personSimpleService;
	@Autowired
	private PersonSimpleDao personSimpleDao;
	@Autowired
	private PersonTagRelationService personTagService;
	@Autowired
	private PersonCategoryRelationService personCategoryRelationService;
	@Autowired
	private CodeRegionService codeRegionService;
	@Autowired
	private CodeSortService codeSortService;
	@Autowired
	private PersonCategoryRelationDAO personCategoryRelationDAO;
	@Autowired
	private CustomerMeetDao customerMeetDao;
	@Autowired
	private NoticeThreadPool noticeThreadPool;
	@Autowired
	private UserService userService;
	@Resource
	private HashMap propertyMap;
	@Autowired
	private DefaultMessageService defaultMessageService;
	@Autowired
	private UserConfigService userConfigService;
	/**
	 * 去掉暂时不处理的字段
	 * @param person
	 * @return
	 */
	private Person convert(Person person) {
		if (null == person) {
			return null;
		}
		person.setProductexps(null);
		person.setHonouexps(null);
		List<WorkExperience> workExperienceList = person.getWorkExperienceList();
		if (CollectionUtils.isNotEmpty(workExperienceList)) {
			for (WorkExperience workExp : workExperienceList) {
				if (workExp != null) {
					final String stime = workExp.getStime();
					if (needConvert(stime)) {
						workExp.setStime(formatCSTDate(stime));
					}
					final String etime = workExp.getEtime();
					if (needConvert(etime)) {
						workExp.setEtime(formatCSTDate(etime));
					}
				}
			}
		}

		return person;
	}

	/**
	 * 获取人脉id
	 * 
	 * @return
	 */
	public Long getPersonIncreasedId() {
		String key = "person";
		String collectionName = "MongoIds";
		Criteria c = Criteria.where("name").is(key);
		Query query = new Query(c);
		Update update = new Update();
		update.inc("cid", 1);
		MongoIds mongoIds = null;
		lock.lock();
		try {
			mongoIds = personMongoTemplate.findAndModify(query, update,	MongoIds.class, collectionName);
		} finally {
			lock.unlock();
		}
		if (mongoIds == null) {
			mongoIds = new MongoIds();
			mongoIds.setCid(1L);
			mongoIds.setName(key);
			personMongoTemplate.insert(mongoIds, collectionName);
		}
		if (mongoIds.getCid().intValue() == Integer.MAX_VALUE) {// 线上历史数据中最小的人脉id是Integer.MAX_VALUE，除此之外的最小的id是1的16次方级别的。在此处理Integer.MAX_VALUE后，老数据的id不用修改
			mongoIds = personMongoTemplate.findAndModify(query, update,	MongoIds.class, collectionName);
		}
		return mongoIds.getCid();
	}

	/**
	 * 保存
	 */
	public Person save(Person person) throws Exception {
		if (person == null) {
			throw new Exception("person不能为空");
		}
		person.setId(this.getPersonIncreasedId());
		
		/**
		 * 问题是getPersonIncreasedId方法与原person_simple表中的数据冲突
		 * MongoIds 数据表 cid 与现有不同步导致
		 * 毕竟personId不是主键一样的存在，就算相同也不会报错
		 * */
		if(personSimpleDao.findPersonExist(person.getId()) > 0) {
			
			//存在问题，生成的id 数据库中不应该存在
			logger.error("save person error:repetition Key"+person.getId());
			//强制退出。。。
			return new Person();
			
		}
		
		person = this.genName(person);
		person.setCreateTime(new Date());
		person.setUpdateTime(new Date());
		person.setVirtual(person.isUser() ? Person.TYPE_VIRTUAL_USER : Person.TYPE_VIRTUAL_PERSON);
		
		boolean mongoDBFlag = true;
		try {
			personMongodbDao.save(person);
		} catch (Throwable e) {
			mongoDBFlag = false;
		}
		
		PersonSimple personSimple = null;
		if(mongoDBFlag) {
			personSimple = this.personConver(person,new PersonSimple());
		try {
			personSimpleService.savePerson(personSimple);
		} catch (Throwable e) {
			personMongodbDao.delete(person.getId());
			return new Person();
			}
		
		} else {
			return new Person();
		}
		
		pushIndexByMQ(FlagTypeUtils.createConnectionsFlag(),person.getId()+"",makeIndexData(person,personSimple));
		
		return convert(person);
	}
	
	private String makeIndexData(Person person,PersonSimple personSimple)
	{
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("name", personSimple.getName1()+personSimple.getName2());
		if(person.getCreateUserId()==0) {
			data.put("virtual", 2); // 1:用户;2:人脉
			System.out.println("personSimple.getPersontype3="+personSimple.getPersontype()+",virtual="+data.get("virtual"));
		} else {
			data.put("virtual", personSimple.getPersontype()); // 1:用户;2:人脉
			System.out.println("personSimple.getPersontype4="+personSimple.getPersontype()+",virtual="+data.get("virtual"));
		}
		data.put("createUserId", person.getCreateUserId()); // 创建者用户id，也可以是当前用户的id
		data.put("id", person.getId()); // 防止人脉id与用户id重复，故需要一个业务逻辑做判断（交付前端），如果 virtual = 1,使用creatUserId属性
		data.put("portrait", person.getPortrait());
		data.put("fromUserId", person.getFromUserId());
		int genderType = person.getGender(); //性别 1-男，2-女，3-未知
		data.put("genderType", genderType);
		String genderName = "未知";
		if(genderType == 1) genderName = "男";
		if(genderType == 2) genderName = "女";
		data.put("gender", genderName);

		data.put("company", person.getCompany());
		data.put("position", person.getPosition());
		Map perMap=person.getPermissions();

		if(perMap!=null&&perMap.size()>0){

		
			if("1".equals(perMap.get("publicFlag").toString())){
				data.put("publicflag", "1");
			}else{
				data.put("publicflag", "0");
			}
			
			if("1".equals(perMap.get("connectFlag").toString())){
				data.put("connectflag", "1");
			}else{
				data.put("connectflag", "0");
			}
			
			if("1".equals(perMap.get("shareFlag").toString())){
				data.put("shareflag", "1");
			}else{
				data.put("shareflag", "0");
			}
			
			
		}else{
			if(person.isUser()){
				data.put("publicflag", "1");
			}else{
				data.put("publicflag", "0");
			}
			
			data.put("connectflag", "1");
			data.put("shareflag", "1");
			
		}

		data.put("typeId", person.getTypeId());
		data.put("peopleType", person.getPeopleType());
		
		data.put("regionId", person.getRegionId());
		data.put("locationCity", person.getLocationCity());
		data.put("locationCountry", person.getLocationCountry());
		data.put("locationCounty", person.getLocationCounty());
		
		data.put("firstIndustryDirection", person.getFirstIndustryDirection());
		data.put("firstIndustryDirectionId", person.getFirstIndustryDirectionId());
		data.put("secondIndustryDirection", person.getSecondIndustryDirection());
		data.put("secondIndustryDirectionId", person.getSecondIndustryDirectionId());
		data.put("createTime", person.getCreateTime().getTime());
		data.put("updateTime", person.getUpdateTime().getTime());
		data.put("nameFirst", person.getNameFirst());

		// 以下是新增字段
        data.put("premark", person.getRemark());
        System.out.println("----------开始处理新增字段------------------");
        List<Basic> contacts= person.getContactInformationList();
        if(contacts!=null&&contacts.size()>0){
        	logger.debug("联系人:"+contacts.size()+"个");
        	System.out.println("联系人:"+contacts.size()+"个");
        	
        	StringBuffer pcontact_website=new StringBuffer();
        	StringBuffer pcontact_companywebsite=new StringBuffer();
        	StringBuffer pcontact_personwebsite=new StringBuffer();
        	
        	StringBuffer pcontact_phone=new StringBuffer();
        	StringBuffer pcontact_workphone=new StringBuffer();
        	StringBuffer pcontact_homephone=new StringBuffer();
        	StringBuffer pcontact_workfax=new StringBuffer();
        	StringBuffer pcontact_homefax=new StringBuffer();
        	
        	
        	StringBuffer pcontact_email=new StringBuffer();
        	StringBuffer pcontact_workemail=new StringBuffer();
        	StringBuffer pcontact_homeemail=new StringBuffer();
        	
        	StringBuffer pcontact_weixin=new StringBuffer();
        	StringBuffer pcontact_weibo=new StringBuffer();
        	StringBuffer pcontact_qq=new StringBuffer();
        	StringBuffer pcontact_renren=new StringBuffer();
        	StringBuffer pcontact_facebook=new StringBuffer();
        	StringBuffer pcontact_twitter=new StringBuffer();
        	StringBuffer pcontact_linkedin=new StringBuffer();
        	StringBuffer pcontact_whatsapp=new StringBuffer();
        	
        	StringBuffer pcontact_address=new StringBuffer();
        	StringBuffer pcontact_homeaddress=new StringBuffer();
        	StringBuffer pcontact_companyaddress=new StringBuffer();

        	for(Basic  contact:contacts){
        		
        		System.out.println("contact:----"+contact.getContent()+"");
        		if("1".equals(contact.getType())){// 电话
        			
        			if("1".equals(contact.getSubtype())){
        				pcontact_phone.append(contact.getContent()+",");
        				
        			}else if("2".equals(contact.getSubtype())){
        				pcontact_workphone.append(contact.getContent()+",");
        			}else if("3".equals(contact.getSubtype())){
        				pcontact_homephone.append(contact.getContent()+",");
        			}else if("4".equals(contact.getSubtype())){
        				pcontact_workfax.append(contact.getContent()+",");
        			}else if("5".equals(contact.getSubtype())){
        				pcontact_homefax.append(contact.getContent()+",");
        			}
        			
        		}else if("4".equals(contact.getType())){// 邮箱
        			
        			if("1".equals(contact.getSubtype())){
        				pcontact_email.append(contact.getContent()+",");
        				
        			}else if("2".equals(contact.getSubtype())){
        				pcontact_workemail.append(contact.getContent()+",");
        			}else if("3".equals(contact.getSubtype())){
        				pcontact_homeemail.append(contact.getContent()+",");
        			}
        			
        		}else if("5".equals(contact.getType())){//网址
        			if("1".equals(contact.getSubtype())){
        				pcontact_website.append(contact.getContent()+",");
        				
        			}else if("2".equals(contact.getSubtype())){
        				pcontact_companywebsite.append(contact.getContent()+",");
        			}else if("3".equals(contact.getSubtype())){
        				pcontact_personwebsite.append(contact.getContent()+",");
        			}
        		}
				else if("6".equals(contact.getType())){// 社交账号

        			if("1".equals(contact.getSubtype())){
        				pcontact_qq.append(contact.getContent()+",");
        				
        			}else if("2".equals(contact.getSubtype())){
        				pcontact_weixin.append(contact.getContent()+",");
        			}else if("3".equals(contact.getSubtype())){
        				pcontact_weibo.append(contact.getContent()+",");
        			}else if("4".equals(contact.getSubtype())){
        				pcontact_weixin.append(contact.getContent()+",");
        			}else if("5".equals(contact.getSubtype())){
        				pcontact_facebook.append(contact.getContent()+",");
        			}else if("6".equals(contact.getSubtype())){
        				pcontact_twitter.append(contact.getContent()+",");
        			}else if("7".equals(contact.getSubtype())){
        				pcontact_linkedin.append(contact.getContent()+",");
        			}else if("8".equals(contact.getSubtype())){
        				pcontact_whatsapp.append(contact.getContent()+",");
        			}

        		}else if("7".equals(contact.getType())){//地址
        			
        			if("1".equals(contact.getSubtype())){
        				pcontact_address.append(contact.getContent()+",");
        				
        			}else if("2".equals(contact.getSubtype())){
        				pcontact_companyaddress.append(contact.getContent()+",");
        			}else if("3".equals(contact.getSubtype())){
        				pcontact_homeaddress.append(contact.getContent()+",");
        			}
        		}
        	}
        	
        	data.put("pcontact_website", pcontact_website.toString());
    		data.put("pcontact_companywebsite", pcontact_companywebsite.toString());
    		data.put("pcontact_personwebsite", pcontact_personwebsite.toString());
    		
    		data.put("pcontact_phone", pcontact_phone.toString());
    		data.put("pcontact_workphone", pcontact_workphone.toString());
    		data.put("pcontact_homephone", pcontact_homephone.toString());
    		data.put("pcontact_workfax", pcontact_workfax.toString());
    		data.put("pcontact_homefax", pcontact_homefax.toString());
    		
    		data.put("pcontact_email", pcontact_email.toString());
    		System.out.println("pcontact_email:"+pcontact_email.toString());
    		data.put("pcontact_workemail", pcontact_workemail.toString());
    		data.put("pcontact_homeemail", pcontact_homeemail.toString());
    		
    		
    		data.put("pcontact_weixin", pcontact_weixin.toString());
    		data.put("pcontact_weibo", pcontact_weibo.toString());
    		data.put("pcontact_qq", pcontact_qq.toString());
    		data.put("pcontact_renren", pcontact_renren.toString());
    		data.put("pcontact_facebook", pcontact_facebook.toString());
    		data.put("pcontact_twitter", pcontact_twitter.toString());
    		data.put("pcontact_linkedin", pcontact_linkedin.toString());
    		data.put("pcontact_whatsapp", pcontact_whatsapp.toString());
    		
    		data.put("pcontact_address", pcontact_address.toString());
    		data.put("pcontact_homeaddress", pcontact_homeaddress.toString());
    		data.put("pcontact_companyaddress", pcontact_companyaddress.toString());
        }

       List<Education>  educationList=person.getEducationList();
        
       if(educationList!=null&&educationList.size()>0){
    	   
    	   StringBuffer peducation_School=new StringBuffer();
    	   StringBuffer peducation_specialty=new StringBuffer();
    	   
    	   for (Education education:educationList){
    		   peducation_School.append(education.getSchool()+",");
    		   peducation_specialty.append(education.getSpecialty()+",");
    	   }
    	   
    	   data.put("peducation_college", peducation_School.toString());
   		   data.put("peducation_specialty", peducation_specialty.toString());
       }
        
       List<PersonalInformation> personalInformationList=  person.getPersonalInformationList();
       if(personalInformationList!=null&&personalInformationList.size()>0){

    	   StringBuffer personal_birthday=new StringBuffer(); 
    	   StringBuffer personal_birthplace=new StringBuffer(); 
    	   StringBuffer personal_bloodtype=new StringBuffer(); 
    	   StringBuffer personal_religiousbelief=new StringBuffer(); 
    	   StringBuffer personal_emotionalstate=new StringBuffer(); 
    	   StringBuffer personal_goodat=new StringBuffer(); 
    	   StringBuffer personal_hobby=new StringBuffer(); 
    	   StringBuffer familymembers_relation=new StringBuffer(); 
    	   StringBuffer familymembers_name=new StringBuffer(); 
    	   
    	   for(PersonalInformation personalInformation:personalInformationList){
    		   List<Basic> keyDateList = personalInformation.getKeyDate();
    		   if(keyDateList!=null && keyDateList.size()>0){
    			   for (Basic basic : keyDateList) {
    				   if("出生日期".equals(basic.getName())){
    					   personal_birthday.append(basic.getContent()+",");
    				   }
				   }
    		   }
    		   personal_birthplace.append(personalInformation.getBirthCountry()+"-"+personalInformation.getBirthCity()+"-"+personalInformation.getBirthCounty()+",");
    		   personal_bloodtype.append(personalInformation.getBloodType()+",");
    		   personal_religiousbelief.append(personalInformation.getReligiousBelief()+",");
    		   personal_emotionalstate.append(personalInformation.getEmotionalState()+",");
    		   personal_goodat.append(personalInformation.getGoodAt()+",");
    		   personal_hobby.append(personalInformation.getHobby()+",");
    		   List<FamilyMember> familyMembers=personalInformation.getFamilyMembers();
    		   if(familyMembers!=null&&familyMembers.size()>0){
    			   for(FamilyMember familyMember: familyMembers ){
    				   familymembers_relation.append(familyMember.getRelation()+",");
    				   familymembers_name.append(familyMember.getName()+",");
    			   }
    		   }
    		  
    	   }
    	   
    	    data.put("personal_birthday", personal_birthday.toString());
	   		data.put("personal_birthplace", personal_birthplace.toString());
	   		data.put("personal_bloodtype", personal_bloodtype.toString());
	   		data.put("personal_religiousbelief",personal_religiousbelief.toString());
	   		data.put("personal_emotionalstate", personal_emotionalstate.toString());
	   		data.put("personal_goodat", personal_goodat.toString());
	   		data.put("personal_hobby", personal_hobby.toString());
	   		data.put("familymembers_relation", familymembers_relation.toString());
   		    data.put("familymembers_name", familymembers_name.toString());
       }

       List<WorkExperience>  workExperiences= person.getWorkExperienceList();
	   if(workExperiences!=null&&workExperiences.size()>0){
		   
		   StringBuffer workexperience_company=new StringBuffer();
		   StringBuffer  workexperience_position=new StringBuffer();
		   
		   for(WorkExperience  workExperience:workExperiences){
			   workexperience_company.append(workExperience.getCompany()+",");
			   workexperience_position.append(workExperience.getPosition()+",");
		   }
		   
			data.put("workexperience_company", workexperience_company.toString());
		    data.put("workexperience_position", workexperience_position.toString());
	   }
		
	    // 以下是标推荐殊字段
		data.put("mytype", "");
		data.put("tags", "");
		data.put("friends", "");
		data.put("scores", "");
		data.put("reasons", "");
		data.put("bigId", person.getBigId());
		data.put("email", person.getEmail());
		data.put("telephone", person.getTelephone());
        
        logger.debug(JSONObject.fromObject(data).toString());
        System.out.println(JSONObject.fromObject(data).toString());
		
		return JSONObject.fromObject(data).toString();
	}
	
	

	/**
	 * 保存人脉到mongo、保存人脉信息简表 保存标签关系、保存目录关系
	 * 
	 * @param person
	 *            人脉对象
	 * @param personTagIds
	 *            人脉标签对象
	 * @param personCategoryRelations
	 *            人脉、好友目录List
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public Person create(Person person, List<Integer> personTagIds,
			List<PersonCategoryRelation> personCategoryRelations,
			Byte personType, Long userId) throws Exception {
		if (person == null) {
			throw new Exception("person 不能为空");
		}
		if (person.getCreateUserId() == null) {
			throw new Exception("缺少创建人id");
		}
		personType = person.isUser() ? PersonSimple.PERSON_TYPE_USER
				.byteValue() : PersonSimple.PERSON_TYPE_PEOPLE.byteValue();
		person.setVirtual(person.isUser() ? Person.TYPE_VIRTUAL_USER
				: Person.TYPE_VIRTUAL_PERSON);
		person = this.save(person);
		// 保存关联信息:保存标签关系、保存目录关系
		saveAssociated(person, personTagIds, personCategoryRelations,
				personType, userId);
		return convert(person);
	}

	/**
	 * 创建 1．创建人脉接口
	 * 
	 * @param person
	 *            人脉对象
	 * @param personTagIds
	 *            人脉标签对象
	 * @param categoryIds
	 *            人脉、好友目录id list 在mongodb中添加人脉并且在简表中添加，标签关联表，目录关联表添加信息
	 */
	public Person create(Person person, List<Integer> personTagIds,
			Set<Long> categoryIds, Byte personType, Long createUserId)
			throws Exception {
		if (person == null || createUserId == null) {
			throw new Exception("数据错误");
		}
		person.setCreateUserId(createUserId);
		// 数组id转成目录关联model
		List<PersonCategoryRelation> personCategoryRelations = this
				.idsToPersonCategoryRelations(categoryIds);
		return create(person, personTagIds, personCategoryRelations, personType, createUserId);
	}

	/**
	 * 2．更新人脉
	 * 
	 * @param person
	 *            人脉对象
	 * @param personType
	 *            类型 1、用户2人脉 修改mongodb中人脉并且修改简表，删除标签关联表，删除目录关联表；
	 *            然后新增标签关联表记录、新增目录关联表记录； 然后删除权限关联表记录
	 */
	@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
	public Person update(Person person, List<Integer> personTagIds,
			List<PersonCategoryRelation> personCategoryRelations,
			Short personType, Long userId) throws Exception {
		if (person == null) {
			throw new Exception("person不能为空");
		}
		person = this.genName(person);
		if (person.getId() == null) {
			throw new Exception("修改时人脉对象id不能为空");
		}
		Person person1 = this.get(person.getId());
		if (person1 == null) {
			throw new Exception("不存在该数据");
		}
		if (person.getPortrait()!=null&& person.getPortrait().startsWith("http://")) {
				
			person.setPortrait(person1.getPortrait());
		}

		personType = person.isUser() ? PersonSimple.PERSON_TYPE_USER
				: PersonSimple.PERSON_TYPE_PEOPLE;

		// 更新人脉
		this.updateBasic(person);
		// 删除标签关联
		personTagDao.deleteByPerIdAndperType(person.getId(),
				personType.byteValue());
		// 删除权限
		personPermissionDao.deleteByPerson(person.getId(), personType,
				person.getCreateUserId());
		// 删除目录关联
		personCategoryRelationDAO.deleteByPerIdAndPerTypeAndUid(person.getId(),
				Integer.valueOf(personType), person.getCreateUserId());
		// 保存关联信息
		saveAssociated(person, personTagIds, personCategoryRelations,
				personType.byteValue(), userId);
		return convert(person);
	}

	/**
	 * 2.更新人脉
	 * 
	 * @param person
	 *            人脉对象 persontype类型 1、用户2人脉
	 * @param personTagIds
	 *            标签
	 * @param personCategoryRelationIds
	 *            目录ids
	 * @param persontype
	 * @param createUserId
	 *            创建人脉id 修改mongodb中人脉并且修改简表，删除标签关联表，删除目录关联表添加信息，新增标签关联表新增目录关联表
	 */
	public Person update(Person person, List<Integer> personTagIds,
			Set<Long> personCategoryRelationIds, Short persontype,
			Long createUserId) throws Exception {
		if (person == null || createUserId == null) {
			throw new Exception("数据错误");
		}
		person.setCreateUserId(createUserId);
		List<PersonCategoryRelation> personCategoryRelations = this
				.idsToPersonCategoryRelations(personCategoryRelationIds);
		return update(person, personTagIds, personCategoryRelations, persontype, createUserId);
	}

	/**
	 * 3．删除人脉
	 * 
	 * @param personid
	 *            人脉对象id
	 * @param createUserId
	 *            创建人 删除人脉，删除关联数据，删除人脉简表
	 */
	public void delete(Long personid, Long createUserId, Byte personType)
			throws Exception {
		if (personid == null || createUserId == null) {
			throw new Exception("数据错误");
		}
		Person person = personMongodbDao.get(personid);
		if (person == null) {
			throw new Exception("不存在此person");
		}
		if (personType == null) {
			personType = Persontype.person.code;
		}
		if (person != null) {
			// 如果创建人不当前用则删除收藏
			if (!person.getCreateUserId().equals(createUserId)) {
				personCategoryRelationService.deleteCollec(person.getId(),
						Integer.valueOf(personType), createUserId);
				return;
			}
		}
		// 如果是创建人删除关联
		this.deleteAssociated(personid, createUserId, personType);
	}

	/**
	 * 4．人脉详情
	 * 
	 * @param id
	 *            人脉id
	 * @return
	 * @throws Exception
	 */
	public Person get(Long id) throws Exception {
		if (id == null) {
			throw new Exception("数据错误");
		}
		return convert(personMongodbDao.get(id));
	}

	/**
	 * 根据人脉id集合，查询人脉对象
	 */
	public List<Person> getByIds(Collection<Long> ids) throws Exception {
		List<Person> list = personMongodbDao.getByIds(ids);
		if (null == list || list.isEmpty())
			return new ArrayList<Person>(1);
		for (Person person : list) {
			convert(person);
		}
		return list;
	}

	/**
	 * 20．转为人脉
	 * 
	 * @param id
	 *            人脉id
	 * @param createUserId
	 *            创建人id
	 * @param fromUserId
	 *            转换人id 通过id或者创建人获取人脉如果人脉的创建人是自己则已经创建过不许再转，否则复制一份修改创建人和创建时间
	 */
	public Person copy(Long id, Long createUserId, Long fromUserId)
			throws Exception {
		if (id == null && fromUserId == null) {
			throw new Exception("personId 和 userId 不能全为空");
		}
		if (createUserId == null) {
			throw new Exception("创建人不能为空");
		}
		Person person;
		if (id != null) {
			// 如果有id通过id获取人脉
			person = personMongodbDao.get(id);
		} else {
			// 否则通过用户id获得人脉
			person = personMongodbDao
					.getByFromUserIdAndCreateUserId(fromUserId);
		}
		if (person == null) {
			throw new Exception("不存在此人脉");
		}
		if (createUserId.equals(person.getCreateUserId())) {
			throw new Exception("你已经创建过此人脉");
		}
		// person.setFromUserId(person.getFromUserId());
		person.setCreateUserId(createUserId);
		person.setVirtual(Person.TYPE_VIRTUAL_PERSON);//是人脉

		person = save(person);
		// 创建目录
		PersonCategoryRelation personCategoryRelation = new PersonCategoryRelation();

//		if (person.isUser()) {
//			personCategoryRelation.setPersonType(PersonSimple.PERSON_TYPE_USER
//					.intValue());
//		} else {
		//此方法用于转为人脉，转换后的都是人脉
			personCategoryRelation
					.setPersonType(PersonSimple.PERSON_TYPE_PEOPLE.intValue());
//		}

		personCategoryRelation.setUserId(createUserId);
		personCategoryRelation
				.setCategoryId(PersonCategoryRelationService.NO_GROUP_CATID);
		personCategoryRelation.setPersonId(person.getId());
		personCategoryRelation
				.setCtype(PersonCategoryRelationService.Ctype.create.code);
		personCategoryRelationDAO
				.createPersonCategoryRelation(personCategoryRelation);
		return convert(person);
	}

	/**
	 * 发送者为当前用户接收者不为当前用户并且不是大数据推送的
	 */
	public List<PersonSimple> indexList(Long userId, Long typeId,
			Long regionId, Long careerId, Integer index, Integer size, String keyword,String careIndustryIds)
			throws Exception {
		if (typeId != null && 0l == typeId) {
			typeId = null;
		}
		if (regionId != null && 0l == regionId) {
			regionId = null;
		}
		if (careerId != null && 0l == careerId) {
			careerId = null;
		}
		if(StringUtils.isBlank(keyword)) {
			keyword = null;
		}

		if (userId == null || size == null || index == null) {
			throw new Exception("数据错误");
		}
		Page page = new Page();
		page.setSize((long) size);
		page.setPage((long) index);
		// 获取这个区域下面所有区域id
		Set<Long> regionIds = this.getRegionIds(regionId);
		// 获取所有子项的ids
		Set<Long> careerIds = codeSortService.getChildIdsById(careerId);
		if (careerId != null
				&& (careerIds == null || !careerIds.contains(careerId))) {
			careerIds = new HashSet<Long>();
			careerIds.add(careerId);
		}
		List<PersonSimple> list;
		
		String[] arr = null;

		if(userId != -1) {
			User user = userService.selectByPrimaryKey(userId);
			if (null != user && StringUtils.isNotBlank(user.getCareIndustryIds())) {
				arr = user.getCareIndustryIds().split(",");
			}
		} else {
//			arr = careIndustryIds.split(",");
		}
		if (null == arr) {
			arr = new String[0];
		}
		List<Long> industrys = new ArrayList<Long>();
		for (String str : arr) {
			if (StringUtils.isBlank(str))
				continue;
			industrys.add(Long.valueOf(str));
		}
//		list = personSimpleDao.findByIndustry(industrys, typeId,
//				regionIds, careerIds, page.getStartNumber(),
//				page.getSize(),keyword);
//		if (list == null)
			list = new ArrayList<PersonSimple>();
//		List<Long> personIds = new ArrayList<Long>();
//		for (PersonSimple ps : list) {//PersonSimple表中的personid字段存的全部是人脉id（persontype为1用户时，存的是用户对应的人脉对象的id）
//			personIds.add(ps.getPersonid());
//		}
//		list = new ArrayList<PersonSimple>();
//		List<Person> personList = null;
//		//查人脉
//		if (personIds.size() > 0) {
//			personList = personMongodbDao.getByIds(personIds);
//		}
//		if (null != personList) {
//			list = new ArrayList<PersonSimple>();
//			for (Person per : personList) {
//				PersonSimple simple = this.personConver(per, new PersonSimple());
//				setPhones(simple, per);
//				list.add(simple);
//			}
//			return list;
//		}
		return list;
//		return personSimpleService.getMobilePhonesAndFixedPhones(list);
//		return personSimpleService
//				.getMobilePhonesAndFixedPhones(personSimpleDao
//						.findByRecAndSendAndPush(userId, userId,
//								PersonSimpleDao.IsPush.YES.code, typeId,
//								regionIds, careerIds, page.getStartNumber(),
//								page.getSize()));
	}
	private void setPhones(PersonSimple simple, Person person) {
		List<Basic> contactInformationList = person.getContactInformationList() ;
		if  (null == contactInformationList ||  contactInformationList.size() == 0) {
			return;
		}
		List<Map<String ,String>> mobilePhones = new ArrayList<Map<String ,String>>() ;//手机号
		List<Map<String ,String>> fixedPhones = new ArrayList<Map<String ,String>>() ;//固定电话
		for (Basic phone : contactInformationList) {
			if (StringUtils.isBlank(phone.getType()) || StringUtils.isBlank(phone.getContent()))
				continue;
			Map<String, String> phoneMap = new HashMap<String, String>() ;
			phoneMap.put("mobile", phone.getContent());
			if (phone.getType().equals("1")){
				phoneMap.put("name" ,"手机") ;
				mobilePhones.add(phoneMap)  ;
			}else if (phone.getType().equals("2")){
				phoneMap.put("name" ,"固话") ;
				fixedPhones.add(phoneMap) ;
			}
		}
		simple.setListFixedPhone(fixedPhones);
		simple.setListMobilePhone(mobilePhones);
	}

	/**
	 * 发送者为当前用户接收者不为当前用户并且不是大数据推送的数量
	 */
	public Long indexListCount(Long userId, Long typeId, Long regionId,
			Long careerId,String keyword,String careIndustryIds) throws Exception {
		if (typeId != null && 0l == typeId) {
			typeId = null;
		}
		if (regionId != null && 0l == regionId) {
			regionId = null;
		}
		if (careerId != null && 0l == careerId) {
			careerId = null;
		}
		if(StringUtils.isBlank(keyword)) {
			keyword = null;
		}
		// 获取这个区域下面所有区域id
		Set<Long> regionIds = this.getRegionIds(regionId);
		// 获取所有子项的ids
		Set<Long> careerIds = codeSortService.getChildIdsById(careerId);
		if (careerId != null
				&& (careerIds == null || !careerIds.contains(careerId))) {
			careerIds = new HashSet<Long>();
			careerIds.add(careerId);
		}
		List<Long> industrys = new ArrayList<Long>();
		String[] arr = null;

		if(userId != -1) {
			User user = userService.selectByPrimaryKey(userId);
			if (null != user && StringUtils.isNotBlank(user.getCareIndustryIds())) {
				arr = user.getCareIndustryIds().split(",");
			}
		} else {
//			arr = careIndustryIds.split(",");
		}
		if (null == arr) {
			arr = new String[0];
		}
		for (String str : arr) {
			if (StringUtils.isBlank(str))
				continue;
			industrys.add(Long.valueOf(str));
		}
		return personSimpleDao.findByIndustryCount(industrys, typeId, regionIds, null, keyword);
//		return personSimpleDao.findCountByRecAndSendAndPush(userId, userId,
//				PersonSimpleDao.IsPush.YES.code, typeId, regionIds, careerIds);
	}

	/**
	 * 24．可合并资料的人脉列表
	 * 
	 * @param basePeopleId
	 * @param createUserId
	 *            获取选中的同名的人脉
	 */
	public List<Person> showMerges(Long basePeopleId, Long createUserId,
			Byte personType) throws Exception {
		if (basePeopleId == null || createUserId == null) {
			throw new Exception("数据错误");
		}

		Person person = personMongodbDao.get(basePeopleId);
		if (person == null) {
			throw new Exception("该人脉已被删除");
		}
		if (person.getCreateUserId() == null
				|| !person.getCreateUserId().equals(createUserId)) {
			throw new Exception("该人脉不是您创建的");
		}
		String name1 = null;
		String name2 = null;

		{
			// 获取名称
			List<PersonName> peopleNameList = person.getPeopleNameList();
			if (peopleNameList != null && peopleNameList.size() > 0) {
				PersonName personName = peopleNameList.get(0);
				if (personName != null) {
					name1 = personName.getLastname();
					name2 = personName.getFirstname();
				}
			}
		}

		// 从人脉简表查询同名同姓的人
		List<PersonSimple> personSimples = personSimpleDao.findByUIdAndN1AndN2(
				createUserId, name1, name2);
		if (personSimples == null || personSimples.size() == 0) {
			return new ArrayList<Person>();
		}
		// 获取人脉id
		Set<Long> personIds = new HashSet<Long>();
		for (PersonSimple personSimple : personSimples) {
			personIds.add(personSimple.getPersonid());
		}
		// 根据id List 获取人脉list
		List<Person> list = personMongodbDao.getByIds(personIds);
		if (null == list || list.isEmpty())
			return new ArrayList<Person>(1);
		for (Person per : list) {
			convert(per);
		}
		return list;
	}

	/**
	 * 25．合并人脉资料
	 * 
	 * @param otherPeopleId
	 *            被合并的人脉对象的ID
	 * @param people
	 *            合并后的人脉对象
	 * @param createUserId
	 *            创建人的id 删除被合并的人脉和关联信息，修改合并后的人脉
	 */
	@Transactional(rollbackFor = Exception.class)
	public void merge(Long otherPeopleId, Person people, Long createUserId)
			throws Exception {
		if (otherPeopleId == null || people == null || createUserId == null) {
			throw new Exception("数据错误");
		}
		if (people.getId() == null) {
			throw new Exception("人脉ID不能为空");
		}
		Person thisPerson = personMongodbDao.get(people.getId());
		if (thisPerson == null) {
			throw new Exception("该人脉已被删除");
		}
		if (thisPerson.getCreateUserId() == null
				|| thisPerson.getCreateUserId() != createUserId) {
			throw new Exception("此人脉不是当前用户创建的");
		}

		Person otherPerson = personMongodbDao.get(otherPeopleId);
		if (otherPerson == null) {
			throw new Exception("不存在被合并的人脉对象");
		}

		// 替换以前信息
		this.updateBasic(people);
		if (otherPerson.getCreateUserId().equals(createUserId)
				&& !otherPerson.isUser()) {
			// 如果是当前用户创建的人脉，删除关联信息及人脉对象
			this.deleteAssociated(otherPerson.getId(), createUserId);
		} else {
			// 否则就是当前用户收藏的人脉，删除收藏信息
			personCategoryRelationService.deleteCollec(otherPeopleId,
					Persontype.person.code.intValue(), createUserId);
		}
	}

	/**
	 * 创建或者修改
	 * 
	 * @param person
	 *            人脉
	 * @param personTagIds
	 *            标签
	 * @param categoryIds
	 *            目录id
	 * @param personType
	 *            类型
	 * @param userId
	 *            用户id 如果没有人脉id 创建，有id修改 创建 人脉往mongodb插入数据
	 *            ，人脉简表插入数据关联表插入数据，权限表插入数据并发布活动
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public Person createOrUpdate(Person person, List<Integer> personTagIds,Set<Long> categoryIds, Byte personType, Long userId)throws Exception {
		System.out.println("传入参数person："+person.getPermissions().get("publicFlag"));
		Person returnPerson;
		if (categoryIds == null || categoryIds.size() == 0) {
			categoryIds = new HashSet<Long>();
			categoryIds.add(NO_GROUP_ID);
		}
		List<PersonCategoryRelation> personCategoryRelations = this
				.idsToPersonCategoryRelations(categoryIds);
		if (person.getId() == null || person.getId() < 1) {
			returnPerson = this.create(person, personTagIds,
					personCategoryRelations, personType, userId);
		} else {
			// 修改
			/*if (person.isUser()) {//用户无目录
				personCategoryRelations = null;
				personType = PersonSimple.PERSON_TYPE_USER.byteValue();
			}*/
			System.out.println("----------修改renmai ---------");
//			System.out.println("----------"+person.isUser()+" ---------");
			System.out.println("传入参数person："+person.getPermissions().get("publicFlag"));
			returnPerson = this.update(person, personTagIds,personCategoryRelations, personType.shortValue(), userId);
		}
		// 创建权限
//		if (returnPerson.isUser() == false) {// 目前的需求只要求人脉创建和修改的时候有动态、权限控制
//			personPermissionService.permissControl(person.getPermIds(),
//					returnPerson, userId);
//			noticeThreadPool.permissControl(person.getPermIds(), returnPerson, userId);
		//}  老权限 么用了 注释掉
		return convert(returnPerson);
	}

	/**
	 * 根据id和创建人获得人脉
	 */
	public Person getByIdAndCreateUserId(Long id, Long createUserId) {
		return convert(personMongodbDao.getByIdAndCreateUserId(id, createUserId));
	}

	/**
	 * 查找用户createUserId保存的，已转为人脉的用户对应的人脉对象
	 */
	public List<Person> getUsersByCreateUserId(Collection<Long> userIds,
			Long createUserId) {
		List<Person> list = personMongodbDao.getUsersByCreateUserId(userIds, createUserId);
		if (null == list || list.isEmpty())
			return new ArrayList<Person>(1);
		for (Person person : list) {
			convert(person);
		}
		return list;
	}

	/**
	 * 根据userId（tb_use表的id字段）获得人脉
	 */
	public Person getByUserId(Long userId) {
		return convert(personMongodbDao.getByUserId(userId));
	}

	private int getCTypeForPerson(Person person, long userId) {
		//判斷personType 0-用户;1-人脉
		if("0".equals(person.getVirtual())){
			return PersonCategoryRelation.Ctype.friend.code;//我保存的
		}
		
		if (userId != person.getCreateUserId().longValue()) {
			return PersonCategoryRelation.Ctype.ref.code;//我收藏的
		}
		//是当前用户创建的
		if (person.getFromPersonId() != null && person.getFromPersonId().longValue() > 0) {
			return PersonCategoryRelation.Ctype.saved.code;//我保存的
		}
		
		return PersonCategoryRelation.Ctype.create.code;//我创建的
	}

	/**
	 * 保存关联:保存标签关系、保存目录关系
	 * 
	 * @param person
	 *            人脉对象
	 * @param personCategoryRelations
	 *            目录关联
	 * @param personType
	 *            类型 1用户 2人脉
	 * @throws Exception
	 */
	private void saveAssociated(Person person, List<Integer> personTagIds,
			List<PersonCategoryRelation> personCategoryRelations,
			Byte personType, Long userId) throws Exception {
		if (null != personTagIds && personTagIds.size() > 0) {
			PersonTagRelation personTag;
			Date date = new Date();
			try {
				// 保存标签
				for (Object id : personTagIds) {
					if (null == id || id.toString().length() == 0)
						continue;
					personTag = new PersonTagRelation();
					personTag.setPersonType(personType);
					personTag.setCtime(date);
					personTag.setPersonId(person.getId());
					personTag.setTagId(Long.valueOf(id.toString()));
					personTagService.save(personTag);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("personTag:添加失败");
			}
		}
		try {
			if (personCategoryRelations != null
					&& personCategoryRelations.size() > 0) {
				int ctype = getCTypeForPerson(person, userId);
				for (PersonCategoryRelation personCategoryRelation : personCategoryRelations) {
					personCategoryRelation.setPersonType(Integer
							.valueOf(personType));
					personCategoryRelation.setCtype(ctype);
					personCategoryRelation.setPersonId(person.getId());
					//personCategoryRelation.setUserId(person.getCreateUserId());
					personCategoryRelation.setUserId(userId);
				}
				// 保存目录关联
				personCategoryRelationDAO.batchCreate(personCategoryRelations);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("personCategoryRelation:添加失败");
		}
	}

	/**
	 * 删除人脉和关联信息
	 * 
	 * @param id
	 *            人脉id
	 * @param createUserId
	 *            创建人id
	 */
	@Transactional(rollbackFor = Exception.class)
	private void deleteAssociated(Long id, Long createUserId, Byte personType)
			throws Exception {
		if (id == null || createUserId == null || personType == null) {
			throw new Exception("id createUserId personType不能为空");
		}
		// 删除目录关联
		personCategoryRelationDAO.deleteByPerIdAndPerTypeAndUid(id,
				Integer.valueOf(personType), createUserId);
		// 删除标签
		personTagDao.deleteByPerIdAndperType(id, personType);
		// 删除权限
		personPermissionDao.deleteByPerIdAndperType(id, personType);
		// personMeetDao.deleteByPerIdAndperType(id, personType);
		// 删除人脉对象
		personMongodbDao.delete(id);
		// 删除简表
		personSimpleDao.deleteByPerIdAndPerType(id, Short.valueOf(personType));

		customerMeetDao.deleteByPersonId(id);
		
		Map<String,Long> map = new HashMap<String,Long>(1);
		
		map.put("id", id);
		
		pushIndexByMQ(FlagTypeUtils.deleteConnectionsFlag(),id+"",JSONObject.fromObject(map).toString());
	}

	/**
	 * 保存人脉和目录关系
	 * 
	 * @param person
	 * @param personCategoryRelationIds
	 */
	public void savePersonAndCategory(Person person,
			Set<Long> personCategoryRelationIds, long userId) {
		try {
			person = this.save(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (personCategoryRelationIds == null
				|| personCategoryRelationIds.size() == 0) {
			personCategoryRelationIds = new HashSet<Long>();
			personCategoryRelationIds.add(NO_GROUP_ID);
		}
		List<PersonCategoryRelation> personCategoryRelations = this
				.idsToPersonCategoryRelations(personCategoryRelationIds);
		if (personCategoryRelations != null
				&& personCategoryRelations.size() > 0) {
			int ctype = getCTypeForPerson(person, userId);
			for (PersonCategoryRelation personCategoryRelation : personCategoryRelations) {
				personCategoryRelation
						.setPersonType(person.isUser() ? PersonSimple.PERSON_TYPE_USER
								.intValue() : PersonSimple.PERSON_TYPE_PEOPLE
								.intValue());
				personCategoryRelation.setCtype(ctype);
				personCategoryRelation.setPersonId(person.getId());
				personCategoryRelation.setUserId(person.getCreateUserId());
			}
			// 保存目录关联
			try {
				personCategoryRelationDAO.batchCreate(personCategoryRelations);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除关联
	 */
	private void deleteAssociated(Long id, Long createUserId) throws Exception {
		this.deleteAssociated(id, createUserId, Persontype.person.code);
	}

	/**
	 * 修改人脉并且修改简表
	 */
	public void updateBasic(Person person) throws Exception {
		
		System.out.println("修改 调用人脉------"+person.getId());
		personMongodbDao.update(person);
		// 更新人脉简表
		PersonSimple personSimple = this.personConver(person,
				new PersonSimple());
		personSimpleDao.updateByPerIdAndperType(personSimple);
		
		pushIndexByMQ(FlagTypeUtils.updateConnectionsFlag(),person.getId()+"",makeIndexData(person,personSimple));
	}

	public void updateName(Person person, String nameStr) throws Exception {
		List<PersonName> list = person.getPeopleNameList();
		if (null == list) {
			list = new ArrayList<PersonName>();
		}
		person.setPeopleNameList(list);
		if (list.size() < 1) {
			list.add(new PersonName());
		}
		PersonName name = list.get(0);
		name.setFirstname(nameStr);
		name.setParentType(PersonName.PARENT_TYPE_ZH);// 1-中文名
		if (null == name.getTypeTag())
			name.setTypeTag(new TypeTag());

		TypeTag tag = name.getTypeTag();
		tag.setName("中文名");
		tag.setType(TypeTag.TYPE_DEFINED);

		person.setNameIndex(PinyinUtils.stringToHeads(nameStr));
		person.setNameFirst(StringUtils.substring(person.getNameIndex(), 0, 1));
		person.setPinyin(PinyinUtils.stringToQuanPin(nameStr));
		personMongodbDao.update(person);
		// 更新人脉简表
		PersonSimple personSimple = this.personConver(person,
				new PersonSimple());
		int updateState = personSimpleDao.updateByPerIdAndperType(personSimple);
		System.out.println("updateState="+updateState);
		if(updateState==1){
			pushIndexByMQ(FlagTypeUtils.updateConnectionsFlag(),person.getId()+"",makeIndexData(person,personSimple));
		}
		if (updateState == 0) {
			throw new Exception("更新personSimple失败");
		}
	}

	/**
	 * ids 转人脉关联目录对象
	 * 
	 * @param categoryIds
	 * @return
	 */
	private List<PersonCategoryRelation> idsToPersonCategoryRelations(
			Set<Long> categoryIds) {
		List<PersonCategoryRelation> personCategoryRelations = new ArrayList<PersonCategoryRelation>();
		if (null == categoryIds)
			return personCategoryRelations;
		PersonCategoryRelation personCategoryRelation;
		for (Long categoryId : categoryIds) {
			personCategoryRelation = new PersonCategoryRelation();
			personCategoryRelation.setCategoryId(categoryId);
			personCategoryRelations.add(personCategoryRelation);
		}
		return personCategoryRelations;
	}

	/**
	 * 对象转换人脉对象转人脉信息简对象
	 * 
	 * @param person
	 * @param personSimple
	 * @return
	 */
	private PersonSimple personConver(Person person, PersonSimple personSimple)
			throws Exception {
		if (person == null || personSimple == null) {
			throw new Exception("personSimple person 不能为空");
		}
		if (person.isUser()) {
//			personSimple.setPersonid(person.getCreateUserId());
			personSimple.setPersontype(PersonSimple.PERSON_TYPE_USER);
			System.out.println("personSimple.setPersontype1="+personSimple.getPersontype()+",person.getVirtual1="+person.getVirtual());
		} else {
//			personSimple.setPersonid(person.getId());
			personSimple.setPersontype(PersonSimple.PERSON_TYPE_PEOPLE);
			System.out.println("personSimple.setPersontype2="+personSimple.getPersontype()+",person.getVirtual2="+person.getVirtual());
		}

		personSimple.setFirstIndustryDirectionId(person.getFirstIndustryDirectionId());
		personSimple.setSecondIndustryDirectionId(person.getSecondIndustryDirectionId());
		personSimple.setPinyin(substring(person.getPinyin(), 100));
		personSimple.setGender(person.getGender().intValue());
		personSimple.setCompany(substring(person.getCompany(), 100));
		personSimple.setPosition(substring(person.getPosition(), 100));
		personSimple.setPicpath(person.getPortrait());
		personSimple.setPhone(substring(person.getTelephone(), 20));
		personSimple.setCareerId(person.getCareerId());
		personSimple.setCreator(person.getCreateUserId());
		personSimple.setCreatetime(new Date());
		personSimple.setCountry(substring(person.getLocationCountry(), 100));
		personSimple.setCity(substring(person.getLocationCity(), 100));
		personSimple.setCounty(substring(person.getLocationCounty(), 100));
		personSimple.setTypeName(substring(person.getPeopleType(), 100));
		if (person.getTypeId() != null) {
			personSimple.setTypeId(person.getTypeId().longValue());
		}
		personSimple.setRegionId(person.getRegionId());
		personSimple.setPersonid(person.getId());
		if (null == personSimple.getCareerId())
			personSimple.setCareerId(0L);
		if (null == personSimple.getRegionId())
			personSimple.setRegionId(0L);
		if (null == personSimple.getTypeId())
			personSimple.setTypeId(0L);
		try {
			if (person.getPeopleNameList() != null
					&& person.getPeopleNameList().get(0) != null) {
				personSimple.setName1(substring(person.getPeopleNameList().get(0)
						.getLastname(), 100));
				personSimple.setName2(substring(person.getPeopleNameList().get(0)
						.getFirstname(),100));
			}
		} catch (Exception e) {

		}

		return personSimple;
	}

	/**
	 * 截取字符串
	 * @param str 字符串
	 * @param subNum 截取的数量
	 * @return
	 */
	private String substring(String str , Integer subNum){
		if (str==null){
			return null;
		}
		if (str.equals("")){
			return "" ;
		}
		if (str.length()<=subNum){
			return str ;
		}
		return str.substring(0 , subNum) ;
	}
	/**
	 * 获取名称
	 * 
	 * @param person
	 * @return
	 */
	private Person genName(Person person) {
		if (person != null && person.getPeopleNameList() != null
				&& person.getPeopleNameList().size() > 0) {
			PersonName personName = person.getPeopleNameList().get(0);
			String name = StringUtils.trimToEmpty(personName.getLastname())
					+ StringUtils.trimToEmpty(personName.getFirstname());
			String nameAll = PinyinUtils.stringToQuanPin(name);
			String nameIndex = PinyinUtils.stringToHeads(name);
			String nameFirst = StringUtils.substring(nameIndex, 0, 1);
			person.setNameIndex(nameIndex);
			person.setNameFirst(nameFirst);
			person.setPinyin(nameAll);
		}
		return person;
	}

	/**
	 * 获取这个区域下面所有区域id
	 * 
	 * @param id
	 *            区域id
	 * @return
	 */
	private Set<Long> getRegionIds(Long id) {
		if (id == null) {
			return null;
		}
		Set<Long> codeIds = new HashSet<Long>();
		codeIds.add(id);
		// 获取所有区域
		if (id.equals(0l)) {
			List<CodeRegion> codeRegions = codeRegionService.selectAllCountry();
			if (codeRegions != null && codeRegions.size() > 0) {
				for (CodeRegion codeRegion : codeRegions) {
					codeIds.add(codeRegion.getId());
				}
			}
		} else {
			// 子区域下所有子区域
			List<CodeRegion> codeRegions = codeRegionService
					.selectByParentId(id);
			List<CodeRegion> codeRegions1;
			if (codeRegions != null && codeRegions.size() > 0) {
				for (CodeRegion codeRegion : codeRegions) {
					codeIds.add(codeRegion.getId());
					codeRegions1 = codeRegionService
							.selectByParentId(codeRegion.getId());
					if (codeRegions1 != null && codeRegions1.size() > 0) {
						for (CodeRegion codeRegion1 : codeRegions1) {
							codeIds.add(codeRegion1.getId());
						}
					}
				}
			}
		}
		return codeIds;
	}

	/*
	 * public Map<String, Object> searchPeople(String query, String coreName,
	 * int currentPage, int pageSize, Map params) throws Exception { int start =
	 * (currentPage - 1) * pageSize; SearchBean searchBean = new
	 * SearchBean(query); searchBean.setStart(start);
	 * searchBean.setRows(pageSize); searchBean.setCoreName(coreName);
	 * 
	 * searchBean.addSort("score", "desc"); searchBean.addSort("createTime",
	 * "desc");
	 * 
	 * if (params != null && params.size() > 0) { Iterator iter =
	 * params.entrySet().iterator(); while (iter.hasNext()) { Map.Entry entry =
	 * (Map.Entry) iter.next(); searchBean.addFilter(entry.getKey().toString(),
	 * entry.getValue().toString()); } } Map<String, Object> map = new
	 * HashMap<String, Object>(); List<PeopleDoc> plist = new
	 * ArrayList<PeopleDoc>(); ListResult result =
	 * searcherService.search(searchBean); PageUtil page=null; if(result != null
	 * && result.size() > 0){ List<Object> list = result.getResult(); for (int i
	 * = 0; i < list.size(); i++) { PeopleDoc doc = (PeopleDoc) list.get(i);
	 * Person person =this.get(Long.parseLong(doc.getId())); if(person != null){
	 * String nameAll=null; if (person != null && person.getPeopleNameList() !=
	 * null && person.getPeopleNameList().size() > 0) { PersonName personName =
	 * person.getPeopleNameList().get(0); String name =
	 * StringUtils.trimToEmpty(personName.getLastname()) +
	 * StringUtils.trimToEmpty(personName.getFirstname()); nameAll =
	 * PinyinUtils.stringToQuanPin(name); } doc.setName(nameAll);
	 * if(doc.getMobile()==null||doc.getMobile().trim().equals("")) { //
	 * doc.setMobile(person.getMobile()); }
	 * 
	 * doc.setEmail(person.getEmail());
	 * 
	 * if(doc.getIncPhone()==null||doc.getIncPhone().trim().equals("")) { //
	 * doc.setIncPhone(person.getIncPhone()); } //公司名称
	 * doc.setIncName(person.getCompany());
	 * 
	 * plist.add(doc); }
	 * 
	 * } page=new PageUtil(new Long(result.getSum()).intValue(),
	 * currentPage,pageSize); } if(page==null) page=new
	 * PageUtil(0,currentPage,pageSize); map.put("resultBean", plist);
	 * map.put("page", page); return map; }
	 */

	public List<Person> selectByCreatorId(long creatorId, int pageIndex,
			int pageSize) {
		List<Person> list = personMongodbDao.selectByCreatorId(creatorId, pageIndex,
				pageSize);
		if (null == list || list.isEmpty())
			return new ArrayList<Person>(1);
		for (Person person : list) {
			convert(person);
		}
		return list;
	}

	/**
	 * 获取我创建和我收藏的人脉列表
	 * 
	 * @param userid
	 *            当前用户id
	 * @return
	 */
	public List<Person> getUsers(Long userid) {
		// 获取收藏用户的人脉id
		Set<Long> personIds = personCategoryRelationDAO.selectUser(
				Persontype.person.code.intValue(), userid);
		List<Person> list = personMongodbDao.getByIds(personIds);
		if (null == list || list.isEmpty())
			return new ArrayList<Person>(1);
		for (Person person : list) {
			convert(person);
		}
		return list;
	}

	public List<Person> selectByUserId(long userId, int pageIndex, int pageSize) {
		List<Person> list = personMongodbDao.selectByUserId(userId, pageIndex, pageSize);
		if (null == list || list.isEmpty())
			return new ArrayList<Person>(1);
		for (Person person : list) {
			convert(person);
		}
		return list;
	}

	/**
	 * 导入通讯录判断
	 * 
	 * @param telephone
	 *            （电话号码）
	 * @param createrId
	 *            （创建者ID）
	 * @return
	 */
	public Boolean hasMail(String telephone, Long createrId) {
		List<Person> list = personMongodbDao.selectMail(telephone, createrId);
		if (null != list && list.size() > 0) {
			return false;
		}
		return true;
	}
	
	private Date toDate(String str) {
		if (StringUtils.isBlank(str) || "开始时间".equals(str)
				|| "结束时间".equals(str) || str.contains("今"))
			return new Date();
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
		} catch (ParseException e) {
			try {
				return new SimpleDateFormat("yyyy-MM").parse(str);
			} catch (ParseException e1) {
				try {
					return new SimpleDateFormat("yyyy-MM-dd").parse(str);
				} catch (ParseException x) {
					try {
						return new SimpleDateFormat("yyyy.MM").parse(str);
					} catch (ParseException x1) {
						try {
							return new SimpleDateFormat("yyyy.MM.dd")
									.parse(str);
						} catch (ParseException x2) {
							System.out.println("时间格式错误：" + str + ","
									+ x2.getMessage());
						}
					}
				}
			}
		}
		return null;
	}

	public Person peopleToPerson(PeopleTemp peopleTemp) {
		Person person = new Person();
		// 性别
		person.setGender((byte) convertGender(peopleTemp.getGender()));

		// 处理姓名
		List<PeopleName> peopleNames = peopleTemp.getPeopleNameList();
		if (peopleNames != null && peopleNames.size() > 0) {
			PersonName personName;
			List<PersonName> personNames = new ArrayList<PersonName>();
			PeopleSelectTag peopleSelectTag;
			TypeTag typeTag;
			for (PeopleName peopleName : peopleNames) {
				if (StringUtils.isBlank(peopleName.getName()))
					continue;

				personName = new PersonName();
				personName.setFirstname(peopleName.getName());// 名
				personName.setLastname("");// 姓
				personName.setParentType(Byte.valueOf(TYPE_DEFAULT));// 新人脉的姓名无类型，第一个姓名为中文名
				peopleSelectTag = peopleName.getTypeTag();
				if (peopleSelectTag != null) {
					typeTag = new TypeTag();
					typeTag.setName(peopleSelectTag.getName());
					typeTag.setId(peopleSelectTag.getId());
					if (peopleSelectTag.getType() != null) {
						typeTag.setType(peopleSelectTag.getType().byteValue());
					}
					personName.setTypeTag(typeTag);
				}
				personNames.add(personName);
			}
			person.setPeopleNameList(personNames);
		}

		person.setPinyin(peopleTemp.getPinyin());
		person.setNameIndex(peopleTemp.getNameIndex());
		person.setNameFirst(peopleTemp.getNameFirst());
		person.setRemark(peopleTemp.getRemark());// 个人简介
		person.setPortrait(peopleTemp.getPortrait());// 头像
		//
		// //更新时间
		// String updateTimeStr = peopleTemp.getUpdateTime() ;
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date updateTime = null;
		// if (updateTimeStr!=null){
		// try {
		// updateTime = simpleDateFormat.parse(updateTimeStr);
		// }catch (Exception e){ }
		// }
		// if (updateTime!=null){
		// person.setUpdateTime(updateTime);
		// }
		//
		// //创建时间
		// String createTimeStr = peopleTemp.getCreateTime() ;
		// Date createTime = null ;
		// if (createTimeStr!=null){
		// try {
		// createTime = simpleDateFormat.parse(createTimeStr);
		// }catch (Exception e){ }
		// }
		// person.setCreateTime(createTime);
		// //创建人
		// person.setCreateUserId(peopleTemp.getCreateUserId());

		// 工作经历
		List<PeopleWorkExperience> peopleWorkExperiences = peopleTemp
				.getWorkExperienceList();
		PeopleWorkExperience peopleWorkExperience = null;
		if (peopleWorkExperiences != null && peopleWorkExperiences.size() > 0) {
			Collections.sort(peopleWorkExperiences,
					new Comparator<PeopleWorkExperience>() {
						public int compare(PeopleWorkExperience o1,
								PeopleWorkExperience o2) {
							if (o1.isCurrentStatus())
								return 1;
							if (o2.isCurrentStatus())
								return -1;
							if (StringUtils.isBlank(o1.getEndTime()))
								return 1;// o1比较大
							if (StringUtils.isBlank(o2.getEndTime()))
								return -1;

							return o1.getEndTime().compareTo(o2.getEndTime());
						}
					});
			peopleWorkExperience = peopleWorkExperiences
					.get(peopleWorkExperiences.size() - 1);
			if (peopleWorkExperience != null) {
				person.setPosition(peopleWorkExperience.getPosition());// 职务
				person.setCompany(peopleWorkExperience.getCompany());// 公司
			}

			List<WorkExperience> list = new ArrayList<WorkExperience>();
			person.setWorkExperienceList(list);
			for (PeopleWorkExperience work : peopleWorkExperiences) {
				WorkExperience we = new WorkExperience();
				list.add(we);
				
				we.setStime(new SimpleDateFormat("yyyy-MM").format(toDate(work.getStartTime())));
				we.setEtime(new SimpleDateFormat("yyyy-MM").format(toDate(work.getEndTime())));
				we.setCompany(work.getCompany());// 单位名称
				we.setCompanyIndustry(work.getCompanyIndustry());// 单位行业
				we.setCertifier(work.getReferences());// 证明人
				we.setCertifierPhone(work.getTel());// 联系电话
				we.setDepartment(work.getDepartment());// 部门
				we.setPosition(work.getPosition());// 职务
				we.setCustom(new ArrayList<Basic>());// 自定义字段
				we.setCurrentStatus(work.isCurrentStatus());
				if (null != work.getPersonalLineList()) {
					for (PeoplePersonalLine pp : work.getPersonalLineList()) {
						Basic basic = new Basic();
						we.getCustom().add(basic);
						basic.setContent(pp.getContent());
						basic.setType(TYPE_CUSTOM);
						basic.setSubtype(TYPE_CUSTOM);
						basic.setName(pp.getName());
					}
				}
				// 同事关系
				if (null != work.getColleagueRelationshipList()) {
					we.setColleagueRelationshipList(new ArrayList<Basic>());
					for (PeopleColleagueRelationship pp : work
							.getColleagueRelationshipList()) {
						Basic basic = new Basic();
						we.getColleagueRelationshipList().add(basic);
						basic.setType(TYPE_CUSTOM);
						basic.setSubtype(TYPE_CUSTOM);
						if (null != pp.getTypeTag()) {
							basic.setName(pp.getTypeTag().getName());
							// 1-同事，2-上级，3-下级，N-自定义
							if (null != pp.getTypeTag().getType()
									&& pp.getTypeTag().getType() == 1) {
							} else if (null != pp.getTypeTag().getCode()
									&& "1,2,3".contains(pp.getTypeTag()
											.getCode())) {
								basic.setType(pp.getTypeTag().getCode());
								basic.setSubtype(TYPE_DEFAULT);
							}
						}
						basic.setContent(pp.getContent());
					}
				}
				// 描述
				if (StringUtils.isNotBlank(work.getDescription())) {
					Basic basic = new Basic();
					basic.setName("描述");
					basic.setType(TYPE_CUSTOM);
					basic.setSubtype(TYPE_CUSTOM);
					basic.setContent(work.getDescription());
					we.getCustom().add(basic);
				}
			}
		}

		person.setTaskId(peopleTemp.getTaskId());// 附件
		if (StringUtils.isBlank(person.getTaskId())) {
			person.setTaskId(MakeTaskId.getTaskId());
		}

		// 联系方式
		person.setContactInformationList(new ArrayList<Basic>());
		List<PeopleContactComm> contactList = new ArrayList<PeopleContactComm>();
		{
			if (null != peopleTemp.getContactCommunicationList())
				contactList.addAll(peopleTemp.getContactCommunicationList());
			if (null != peopleTemp.getContactFaxList())
				contactList.addAll(peopleTemp.getContactFaxList());
			if (null != peopleTemp.getContactFixedList())
				contactList.addAll(peopleTemp.getContactFixedList());
			if (null != peopleTemp.getContactHomeList())
				contactList.addAll(peopleTemp.getContactHomeList());
			if (null != peopleTemp.getContactMailList())
				contactList.addAll(peopleTemp.getContactMailList());
			if (null != peopleTemp.getContactMobileList())
				contactList.addAll(peopleTemp.getContactMobileList());
		}
		for (PeopleContactComm pp : contactList) {
			Basic basic = new Basic();
			person.getContactInformationList().add(basic);
			basic.setContent(pp.getContent());

			/**
			 * 通讯类型： 1-手机类型，2-固话类型，3-传真类型，4-邮箱类型，5-主页类型，6-即时通讯类型，7-地址, N-自定义
			 * public Integer parentType;
			 * 
			 * 手机类型：1-手机，2-电话，3-商务电话，4-主要电话，N-自定义
			 * 固话类型：1-固话，2-家庭电话，3-办公电话，4-主要电话，N-自定义 传真类型：1-住宅传真，2-商务传真，N-自定义
			 * 邮箱类型：1-主要邮箱，2-商务邮箱，N-自定义 主页类型：1-个人主页，2-商务主页，N-自定义
			 * 通讯类型：1-QQ，2-微信，3-微博，4-Skype，5-facebook，6-twitter，N-自定义
			 * 地址类型：1-住宅，2-商务，N-自定义 自定义类型：1-自定义字段，N-自定义长文本
			 */
			basic.setType(TYPE_CUSTOM);
			basic.setSubtype(TYPE_CUSTOM);
			if (null != pp.getParentType() && pp.getParentType() <= 7
					&& pp.getParentType() >= 1) {
				basic.setType(pp.getParentType().toString());
				if (null != pp.getTypeTag().getType()
						&& pp.getTypeTag().getType() == 1) {
				} else {
					if (null != pp.getTypeTag()
							&& StringUtils
									.isNotBlank(pp.getTypeTag().getCode())
							&& pp.getTypeTag().getCode().length() == 1)
						basic.setSubtype(pp.getTypeTag().getCode());
				}
			}
			if (null != pp.getTypeTag()) {
				basic.setName(pp.getTypeTag().getName());
			}
		}

		// 从联系方式取电话
		List<PeopleContactComm> peopleContactComms = peopleTemp
				.getContactMobileList();
		PeopleContactComm peopleContactComm = null;
		if (peopleContactComms != null && peopleContactComms.size() > 0) {
			peopleContactComm = peopleContactComms.get(0);
			if (peopleContactComm != null) {
				person.setTelephone(peopleContactComm.getContent());// 电话
			}
		}
		// 从联系方式取邮箱
		List<PeopleContactComm> contactMailList = peopleTemp
				.getContactMailList();
		PeopleContactComm contactMail = null;
		if (contactMailList != null && contactMailList.size() > 0) {
			contactMail = contactMailList.get(0);
			if (contactMail != null) {
				person.setEmail(contactMail.getContent());
			}
		}

		// 地址
		List<PeopleAddress> peopleAddresses = peopleTemp
				.getContactAddressList();
		PeopleAddress peopleAddress = null;
		if (peopleAddresses != null && peopleAddresses.size() > 0) {
			peopleAddress = peopleAddresses.get(0);
			if (peopleAddress != null) {
				person.setAddress(peopleAddress.getAddress());
				person.setLocationCountry(peopleAddress.getStateName());
				person.setLocationCity(peopleAddress.getCityName());
				person.setLocationCounty(peopleAddress.getCountyName());
			}
			for (PeopleAddress pp : peopleAddresses) {
				Basic basic = new Basic();
				person.getContactInformationList().add(basic);
				basic.setType("7");
				basic.setSubtype(TYPE_CUSTOM);// 1-住宅地址；2-商务地址；N-自定义
				basic.setContent(StringUtils.trimToEmpty(pp.getStateName())
						+ StringUtils.trimToEmpty(pp.getCityName())
						+ StringUtils.trimToEmpty(pp.getCountyName())
						+ StringUtils.trimToEmpty(pp.getAddress()));
				if (null != pp.getTypeTag()
						&& null != pp.getTypeTag().getCode()
						&& "1,2".contains(pp.getTypeTag().getCode())) {
					basic.setSubtype(pp.getTypeTag().getCode());
				}
				if (null != pp.getTypeTag())
					basic.setName(pp.getTypeTag().getName());
			}
		}

		// 联系方式中的自定义字段
		List<PeoplePersonalLine> peoplePersonalLines = peopleTemp
				.getPersonalLineList();
		if (peoplePersonalLines != null && peoplePersonalLines.size() > 0) {
			for (PeoplePersonalLine peoplePersonalLine : peoplePersonalLines) {
				Basic basic = new Basic();
				basic.setName(peoplePersonalLine.getName());
				basic.setContent(peoplePersonalLine.getContent());
				basic.setType(TYPE_CUSTOM);
				basic.setSubtype(TYPE_CUSTOM);
				person.getContactInformationList().add(basic);
			}
		}

		// 基本信息中的所在地，是指公司的所在地，原来是存储在tb_user表中
		if (StringUtils.isBlank(person.getLocationCity()))
			person.setLocationCity(peopleTemp.getBirthPlaceCityName());
		if (StringUtils.isBlank(person.getLocationCounty()))
			person.setLocationCounty(peopleTemp.getBirthPlaceStateName());
		if (StringUtils.isBlank(person.getLocationCountry()))
			person.setLocationCountry(peopleTemp.getNationalityName());

		// 个人情况
		person.setCustomTagList(new ArrayList<Basic>());
		List<PersonalInformation> personalInformationList = new ArrayList<PersonalInformation>();
		PersonalInformation personalInformation = new PersonalInformation();
		personalInformation.setRaceName(peopleTemp.getRaceName());// 名族
		// personalInformation.setBirthCountry(peopleTemp.getNationalityName());//国籍
		personalInformation.setBirthPlaceCountryName(peopleTemp
				.getBirthPlaceCountryName());// 0-国内；1-国外
		personalInformation
				.setBirthCountry(peopleTemp.getBirthPlaceStateName());// 国内：省；国外：洲
		personalInformation.setBirthCity(peopleTemp.getBirthPlaceCityName());// 国内：市；国外：国家
		personalInformation
				.setBirthCounty(peopleTemp.getBirthPlaceCountyName());// 县
		personalInformation.setBirthPlaceAddress(peopleTemp
				.getBirthPlaceAddress());// 具体地址
		personalInformation.setFaithName(peopleTemp.getFaithName());// 信仰
		personalInformation.setHealth(peopleTemp.getBodySituation());// 身体情况
		personalInformation.setHabit(peopleTemp.getHabit());// 生活习惯
		personalInformation.setHobby(peopleTemp.getHobby());// 爱好
		List<PeopleImportantDate> importantDateList = peopleTemp
				.getImportantDateList();// 重要日期
		if (importantDateList != null) {
			personalInformation.setKeyDate(new ArrayList<Basic>());
			for (PeopleImportantDate date : importantDateList) {
				Basic basic = new Basic();
				personalInformation.getKeyDate().add(basic);
				basic.setContent(date.getDate());
				basic.setType(TYPE_CUSTOM);
				basic.setSubtype(TYPE_CUSTOM);
				if (null != date.getTypeTag()) {
					basic.setName(date.getTypeTag().getName());
					if (null != date.getTypeTag().getType()
							&& date.getTypeTag().getType() == 1)
						;// 自定义
					else if ("1".equals(date.getTypeTag().getCode())
							|| "2".equals(date.getTypeTag().getCode())) {
						basic.setType(date.getTypeTag().getCode());// 1-生日；2-纪念日
						basic.setSubtype(TYPE_DEFAULT);
					}
				}
			}
		}
		List<PeopleCommunityRelationship> relationshipList = peopleTemp
				.getCommunityRelationshipList();// 社会关系(在个人情况中)
		if (relationshipList != null) {
			personalInformation.setSocialRelations(new ArrayList<Basic>());
			for (PeopleCommunityRelationship re : relationshipList) {
				Basic basic = new Basic();
				personalInformation.getSocialRelations().add(basic);
				basic.setContent(re.getContent());
				basic.setType(TYPE_CUSTOM);
				basic.setSubtype(TYPE_CUSTOM);
				if (null != re.getTypeTag()) {
					// "1配偶", "2父亲", "3母亲", "4兄弟", "5姐妹", "6同居伴侣",
					// //"7子女", "8经理", "9助手", "10合作伙伴", "11介绍人", "N自定义"
					if (null != re.getTypeTag().getType()
							&& re.getTypeTag().getType() == 1)
						;// 自定义
					else if (StringUtils.isNotBlank(re.getTypeTag().getCode())
							&& StringUtils.isNumeric(re.getTypeTag().getCode())) {
						int code = Integer.valueOf(re.getTypeTag().getCode());
						if (code >= 1 && code <= 11) {
							basic.setType(String.valueOf(code));
							basic.setSubtype(TYPE_DEFAULT);
						}
					}
					basic.setName(re.getTypeTag().getName());
				}
			}
		}
		List<PeoplePersonalLine> lineList = peopleTemp
				.getSituationPersonalLineList();// 个人情况自定义字段
		if (lineList != null) {
			personalInformation.setCustom(new ArrayList<Basic>());
			for (PeoplePersonalLine re : lineList) {
				Basic basic = new Basic();
				personalInformation.getCustom().add(basic);
				basic.setContent(re.getContent());
				basic.setName(re.getName());
				basic.setType(TYPE_CUSTOM);
				basic.setSubtype(TYPE_CUSTOM);
			}
		}
		personalInformationList.add(personalInformation);
		person.setPersonalInformationList(personalInformationList);

		person.setInvestmentdemandList(peopleDemandCommonsToIntentions(peopleTemp
				.getInvestmentdemandList()));// 投资意向
		person.setFinancingdemandList(peopleDemandCommonsToIntentions(peopleTemp
				.getFinancingdemandList()));// 融资意向
		person.setExpertdemandList(peopleDemandCommonsToIntentions(peopleTemp
				.getExpertdemandList()));// 专家需求
		person.setExpertIdentityList(peopleDemandCommonsToIntentions(peopleTemp
				.getExpertIdentitydemandList()));// 专家身份
		person.setEducationList(peopleEducationsToEducations(peopleTemp
				.getEducationList()));// 教育经历

		// 社会活动
		List<PeopleSocialActivity> socialActivityList = peopleTemp
				.getSocialActivityList();
		person.setSocialActivityList(new ArrayList<SocialActivity>());
		if (socialActivityList != null && socialActivityList.size() > 0) {
			person.setSocialActivityList(new ArrayList<SocialActivity>());
			for (PeopleSocialActivity pp : socialActivityList) {
				SocialActivity socialActivity = new SocialActivity();
				person.getSocialActivityList().add(socialActivity);
				socialActivity.setTownsmen(pp.getFellow());// 老乡
				socialActivity.setIntroducer(pp.getReferrals());// 介绍人
				socialActivity.setCustom(new ArrayList<Basic>());
				socialActivity.setActivityType(new ArrayList<Basic>());
				if (pp.getActivityList() != null
						&& pp.getActivityList().size() > 0) {
					for (PeopleActivity aa : pp.getActivityList()) {
						Basic basic = new Basic();
						socialActivity.getActivityType().add(basic);
						basic.setContent(aa.getContent());
						basic.setType(TYPE_CUSTOM);
						basic.setSubtype(TYPE_CUSTOM);
						if (null != aa.getTypeTag()) {
							basic.setName(aa.getTypeTag().getName());
							// 1-社团，2-组织，3-党派，4-政治团体，5-慈善机构，N-自定义
							if (null != aa.getTypeTag().getType()
									&& 1 == aa.getTypeTag().getType())
								;
							else if (null != aa.getTypeTag().getCode()
									&& aa.getTypeTag().getCode().length() == 1) {
								basic.setType(aa.getTypeTag().getCode());
								basic.setSubtype(TYPE_DEFAULT);
							}
						}
					}
				}
				if (StringUtils.isNotBlank(pp.getDescription())) {
					Basic basic = new Basic();
					basic.setContent(pp.getDescription());
					basic.setName("描述");
					basic.setType(TYPE_CUSTOM);
					basic.setSubtype(TYPE_CUSTOM);
				}
			}
		}
		// 自定义模块
		List<PeoplePersonalPlate> personalPlateList = peopleTemp
				.getPersonalPlateList();
		if (null != personalPlateList) {
			for (PeoplePersonalPlate pp : personalPlateList) {
				List<PeoplePersonalLine> personalLineList = pp
						.getPersonalLineList();
				if (null != personalLineList) {
					for (PeoplePersonalLine peoplePersonalLine : personalLineList) {
						Basic basic = new Basic();
						basic.setName(peoplePersonalLine.getName());
						basic.setContent(peoplePersonalLine.getContent());
						basic.setType(TYPE_CUSTOM);
						basic.setSubtype(TYPE_CUSTOM);
						// if (peoplePersonalLine.getParentType()!=null) {
						// basic.setType(peoplePersonalLine.getParentType().toString());
						// }
						person.getCustomTagList().add(basic);
					}
				}
			}
		}

		return person;
	}

	private List<Education> peopleEducationsToEducations(
			List<PeopleEducation> peopleEducations) {
		if (peopleEducations == null || peopleEducations.size() == 0) {
			return null;
		}
		List<Education> educations = new ArrayList<Education>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		for (PeopleEducation peopleEducation : peopleEducations) {
			Education education = new Education();
			// 起止日期
			String startTimeStr = peopleEducation.getStartTime();
			if (startTimeStr != null) {
				try {
					Date startTime = simpleDateFormat.parse(startTimeStr);
					if (startTime != null) {
						education.setStime(new SimpleDateFormat("yyyy-MM").format(startTime));
					}
				} catch (Exception e) {
				}
			}
			String endTimeStr = peopleEducation.getEndTime();
			if (endTimeStr != null) {
				try {
					Date endTime = simpleDateFormat.parse(endTimeStr);
					if (endTime != null) {
						education.setEtime(new SimpleDateFormat("yyyy-MM").format(endTime));
					}
				} catch (Exception e) {
				}
			}
			education.setSchool(peopleEducation.getSchool());// 学校
			education.setSpecialty(peopleEducation.getSpecialty());// 专业
			if(StringUtils.isNotEmpty(peopleEducation.getCollege())){
				education.setCollege(peopleEducation.getCollege()); // 学院
			}
			// 中文：保密 MBA 博士 硕士 本科 专科 中专 高中 初中 小学
			education.setEducationalBackgroundType(peopleEducation
					.getEducationalBackgroundType());// 学历
			// 博士学位 硕士学位 学士学位
			education.setDegreeType(peopleEducation.getDegreeType());// 学位
			// 1-是；0-否
			education.setStudyAbroadType(peopleEducation.getStudyAbroadType());// 海外学习
			education.setDesc(peopleEducation.getDescription());// 描述
			// 同学关系
			List<PeopleStudentsRelationship> peopleStudentsRelationships = peopleEducation
					.getStudentsRelationshipList();
			if (peopleStudentsRelationships != null
					&& peopleStudentsRelationships.size() > 0) {
				List<Basic> studentsRelationshipList = new ArrayList<Basic>();
				for (PeopleStudentsRelationship peopleStudentsRelationship : peopleStudentsRelationships) {
					Basic basic = new Basic();
					basic.setContent(peopleStudentsRelationship.getContent());
					basic.setType(TYPE_CUSTOM);
					basic.setSubtype(TYPE_CUSTOM);
					if (peopleStudentsRelationship.getTypeTag() != null) {
						basic.setName(peopleStudentsRelationship.getTypeTag()
								.getName());
						if (null != peopleStudentsRelationship.getTypeTag()
								.getType()
								&& "1".equals(peopleStudentsRelationship
										.getTypeTag().getType().toString()))
							;// 自定义
						// 1-同学；2-校友
						else if (StringUtils
								.isNotBlank(peopleStudentsRelationship
										.getTypeTag().getCode())
								&& StringUtils
										.isNumeric(peopleStudentsRelationship
												.getTypeTag().getCode())) {
							int code = Integer
									.valueOf(peopleStudentsRelationship
											.getTypeTag().getCode());
							if (code >= 1 && code <= 2) {
								basic.setType(String.valueOf(code));
								basic.setSubtype(TYPE_DEFAULT);
							}
						}
					}
					studentsRelationshipList.add(basic);
				}
				education.setStudentsRelationshipList(studentsRelationshipList);
			}
			// 外语等级
			List<PeopleForeignLanguage> foreignLanguageList = peopleEducation
					.getForeignLanguageList();
			if (foreignLanguageList != null && foreignLanguageList.size() > 0) {
				List<ForeignLanguage> foreignLanguageListTmp = new ArrayList<ForeignLanguage>();
				ForeignLanguage foreignLanguage;
				for (PeopleForeignLanguage peopleForeignLanguage : foreignLanguageList) {
					foreignLanguage = new ForeignLanguage();
					if (peopleForeignLanguage.getType() != null) {
						// 英语 日语 法语 德语 韩语 俄语 西班牙语 葡萄牙语 阿拉伯语 意大利语
						foreignLanguage
								.setType(peopleForeignLanguage.getType());
					}
					if (peopleForeignLanguage.getLevelType() != null) {// 所选语种的等级的中文
						foreignLanguage.setLevelType(peopleForeignLanguage
								.getLevelType());
					}
					foreignLanguageListTmp.add(foreignLanguage);
				}
				education.setForeignLanguageList(foreignLanguageListTmp);
			}
			// 自定义项
			education.setCustom(new ArrayList<Basic>());
			List<PeoplePersonalLine> personalLineList = peopleEducation
					.getPersonalLineList();
			if (null != personalLineList) {
				for (PeoplePersonalLine peoplePersonalLine : personalLineList) {
					Basic basic = new Basic();
					basic.setName(peoplePersonalLine.getName());
					basic.setContent(peoplePersonalLine.getContent());
					basic.setType(TYPE_CUSTOM);
					basic.setSubtype(TYPE_CUSTOM);
					education.getCustom().add(basic);
				}
			}
			educations.add(education);
		}
		return educations;
	}

	private List<Intention> peopleDemandCommonsToIntentions(
			List<PeopleDemandCommon> investmentdemandListTmp) {
		if (investmentdemandListTmp == null
				|| investmentdemandListTmp.size() == 0) {
			return null;
		}
		List<Intention> investmentdemandList = new ArrayList<Intention>();
		for (PeopleDemandCommon peopleDemandCommon : investmentdemandListTmp) {
			Intention intention = new Intention();
			// 1-投资需求；2-融资需求；3-专家需求；4-专家身份
			if (peopleDemandCommon.getParentType() != null) {
				intention.setParentType(peopleDemandCommon.getParentType()
						.byteValue());
			}
			// 地区
			PeopleAddress peopleAddress = peopleDemandCommon.getAddress();
			if (peopleAddress != null) {
				Address address = new Address();
				address.setStateName(peopleAddress.getStateName());
				address.setCountyName(peopleAddress.getCountyName());
				address.setCityName(peopleAddress.getCityName());

				if (peopleAddress.getParentType() != null) {// 无用
					address.setParentType(peopleAddress.getParentType()
							.byteValue());
				}
				if (null != peopleAddress.getAreaType())// 0-国内；1-国外
					address.setAreaType(peopleAddress.getAreaType().byteValue());
				intention.setAddress(address);
			}
			intention.setIndustryIds(peopleDemandCommon.getIndustryIds());// 行业
			intention.setIndustryNames(peopleDemandCommon.getIndustryNames());
			intention.setTypeIds(peopleDemandCommon.getTypeIds());// 类型
			intention.setTypeNames(peopleDemandCommon.getTypeNames());
			// 自定义项
			intention.setCustom(new ArrayList<Basic>());
			Basic basic = new Basic();
			basic.setContent(peopleDemandCommon.getOtherInformation());
			basic.setName("附加信息");
			basic.setType(TYPE_CUSTOM);
			basic.setSubtype(TYPE_CUSTOM);
			intention.getCustom().add(basic);

			List<PeoplePersonalLine> personalLineList = peopleDemandCommon
					.getPersonalLineList();
			if (null != personalLineList) {
				for (PeoplePersonalLine re : personalLineList) {
					basic = new Basic();
					intention.getCustom().add(basic);
					basic.setContent(re.getContent());
					basic.setName(re.getName());
					basic.setType(TYPE_CUSTOM);
					basic.setSubtype(TYPE_CUSTOM);
					// if (null != re.getParentType())//无用
					// basic.setType(re.getParentType().toString());
				}
			}

			investmentdemandList.add(intention);
		}

		return investmentdemandList;
	}

	private List<PeopleTemp> findOldPeople(int skip, List<String> oldPelpleIds) {
		Query query = new Query();
		// query.skip(skip);
		// query.limit(500); //每次查询500条
		if (oldPelpleIds.size() <= skip) {
			return new ArrayList<PeopleTemp>();
		}
		int MAX = 500;
		if (oldPelpleIds.size() >= skip + MAX) {
			query.addCriteria(new Criteria().and("id").in(
					oldPelpleIds.subList(skip, skip + MAX)));
		} else {
			query.addCriteria(new Criteria().and("id").in(
					oldPelpleIds.subList(skip, oldPelpleIds.size())));
		}
		// Sort sort = query.sort();
		// sort.on("id", Order.ASCENDING); //按id升序排列
		List<PeopleTemp> list = oldPeopleTemplate.find(query, PeopleTemp.class,
				"peopleTemp");
		if (list == null)
			list = new ArrayList<PeopleTemp>();
		System.out.println("本次查询到 " + list.size() + " 个人脉，skip:" + skip);
		return list;
	}

	private List<String> findOldPelpleIds() {
		Query query = new Query();
		query.fields().include("id");
		// Sort sort = query.sort();
		// sort.on("id", Order.ASCENDING); //按id升序排列
		List<PeopleTemp> list = oldPeopleTemplate.find(query, PeopleTemp.class,
				"peopleTemp");
		List<String> re = new ArrayList<String>();
		if (null == list)
			return re;
		for (PeopleTemp pp : list) {
			re.add(pp.getId());
		}
		return re;
	}
	/*
	public void peopleSaveToPerson() {// 仅用于历史数据移植
		long start = System.currentTimeMillis();
		List<User> userList = userService.selectAllUser();// 查询所有个人用户，目前用户数很少，只有5000+
		List<Long> rPersonList = personCategoryRelationDAO.selectAll();
		Map<String, User> userMap = new HashMap<String, User>();// 有人脉对象的用户
		List<User> userListHasNoPeople = new ArrayList<User>();// 还没有创建人脉对象的用户
		for (User user : userList) {
			if (StringUtils.isNotBlank(user.getPeopleId())) {
				userMap.put(user.getPeopleId(), user);
			} else {
				userListHasNoPeople.add(user);
			}
		}
		System.out.println("共计有 " + userList.size() + " 个人用户"
				+ (System.currentTimeMillis() - start));

		// 对老的人脉数据进行移植
		int cnt = 0;
		// String id = "0";
		List<String> oldPeopleIds = findOldPelpleIds();
		// if ("1".equals("1")) {
		// System.out.println("oldPeopleIds:" + oldPeopleIds.size() + "," +
		// oldPeopleIds);
		// return;
		// }

		List<PeopleTemp> list = this.findOldPeople(cnt, oldPeopleIds);
		List<PersonCategoryRelation> relationList = new ArrayList<PersonCategoryRelation>();
		List<Person> personListToInsert = new ArrayList<Person>(2000);
		List<PersonSimple> personSimpleList = new ArrayList<PersonSimple>(2000);
		// Set<String> dealedPeopleId = new HashSet<String>();
		while (null != list && list.size() > 0) {
			cnt += list.size();
			System.out.println("本次查询到 " + list.size() + " 个人脉，累计个数为：" + cnt
					+ "," + (System.currentTimeMillis() - start));
			for (PeopleTemp peopleTemp : list) {
				try {
					// if (dealedPeopleId.contains(peopleTemp.getId()))
					// continue;
					// dealedPeopleId.add(peopleTemp.getId());

					Person person = this.peopleToPerson(peopleTemp);
					if (person.getPeopleNameList() == null) {
						person.setPeopleNameList(new ArrayList<PersonName>());
					}
					User user = userMap.get(peopleTemp.getId());
					if (user == null) {
						// 原来是人脉
						person.setFromUserId(null);
						person.setCreateUserId(peopleTemp.getCreateUserId());
						person.setVirtual(Person.TYPE_VIRTUAL_PERSON);
					} else {
						// 是用户对应的人脉
						person.setFromUserId(user.getId());
						person.setCreateUserId(user.getId());
						person.setVirtual(Person.TYPE_VIRTUAL_USER);
						if (null != user.getSex())
							person.setGender((byte) convertGender(user.getSex()));
						if (StringUtils.isNotBlank(user.getCompanyName()))
							person.setCompany(user.getCompanyName());
						if (StringUtils.isNotBlank(user.getCompanyJob()))
							person.setPosition(user.getCompanyJob());

						// 用户基本信息中的所在地，是指公司的所在地，原来是存储在tb_user表中
						person.setCountry(user.isCountry() ? "1" : "0");
						person.setLocationCountry(user.getProvince());// 国外时表示洲，国内时表示省
						person.setLocationCity(user.getCity());// 国外：国家，国内：市
						person.setLocationCounty(user.getCounty());// 国内：县
						person.setAddress(null);//线上地址中没有县以下的详细地址
						if (StringUtils.isNotBlank(user.getRemark())) // 个人简介
							person.setRemark(user.getRemark());
						if (StringUtils.isNotBlank(user.getMobile()))
							person.setTelephone(user.getMobile());
						if (StringUtils.isNotBlank(user.getEmail()))
							person.setEmail(user.getEmail());

						if (StringUtils.isNotBlank(user.getName())) {
							PersonName name = null;
							if (person.getPeopleNameList().size() > 0) {
								name = person.getPeopleNameList().get(0);
							}
							if (name == null
									|| !user.getName().equals(
											name.getFirstname())) {
								name = new PersonName();
								person.getPeopleNameList().add(0, name);
								name.setFirstname(user.getName());// 名
								name.setLastname("");// 姓
								name.setParentType(Byte.valueOf("0"));// 新人脉的姓名无类型，第一个姓名为中文名
								name.setTypeTag(new TypeTag());
								name.getTypeTag().setName("中文名");
							}
						}
						if (StringUtils.isNotBlank(user.getPicPath())) {// 用户头像
							person.setPortrait(user.getPicPath());
						}
					}
					// person = this.save(person);//存入mongo并在mysql的简表中插入记录
					person.setId(Long.valueOf(peopleTemp.getId()));// 保持原来的id
					person = this.genName(person);
					person.setCreateTime(new Date());
					person.setUpdateTime(new Date());
					person.setPeopleType("其他人物");
					person.setTypeId(108);
					try {
						// personMongodbDao.save(person);
						personListToInsert.add(person);
						if (user == null
								&& rPersonList.contains(person.getId()) == false) {
							// 原来是人脉，插入未分组
							PersonCategoryRelation relation = new PersonCategoryRelation();
							relation.setPersonId(person.getId());
							relation.setPersonType(2);
							relation.setCategoryId(-1L);
							relation.setCtime(new java.sql.Timestamp(System
									.currentTimeMillis()));
							relation.setCtype(1);
							relation.setUserId(person.getCreateUserId());
							relationList.add(relation);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					PersonSimple personSimple = this.personConver(person,
							new PersonSimple());
					if (personSimple.getPhone() != null
							&& personSimple.getPhone().length() > 20) {
						personSimple.setPhone(personSimple.getPhone()
								.substring(0, 19));
					}
					if (personSimple.getPinyin() != null
							&& personSimple.getPinyin().length() > 100) {
						personSimple.setPinyin(personSimple.getPinyin()
								.substring(0, 99));
					}
					// personSimpleService.savePerson(personSimple);
					personSimpleList.add(personSimple);

					userMap.remove(peopleTemp.getId());// 处理完一条记录
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (personListToInsert.size() > 1000) {
					System.out.println("开始插入" + personListToInsert.size()
							+ "个人脉对象" + (System.currentTimeMillis() - start));
					if (personListToInsert.size() > 0) {
						personMongoTemplate
								.insert(personListToInsert, "person");
					}
					System.out.println("完成插入" + personListToInsert.size()
							+ "个人脉对象" + (System.currentTimeMillis() - start));
					personListToInsert.clear();

					System.out.println("开始插入" + personSimpleList.size()
							+ "个人脉简表信息" + (System.currentTimeMillis() - start));
					if (personSimpleList.size() > 0) {
						personSimpleDao.saveBatch(personSimpleList);
					}
					if (relationList.size() > 0) {
						personCategoryRelationDAO
								.createPersonCategoryRelationBatch(relationList);
					}
					System.out.println("完成插入" + personSimpleList.size()
							+ "个人脉简表信息" + (System.currentTimeMillis() - start));
					personSimpleList.clear();
					relationList.clear();
				}
			}
			list = this.findOldPeople(cnt, oldPeopleIds);
		}

		System.out.println("共计有 " + cnt + " 个人脉（包括个人用户对应的人脉资料）"
				+ (System.currentTimeMillis() - start));
		if (personListToInsert.size() > 0) {
			personMongoTemplate.insert(personListToInsert, "person");
		}
		if (personSimpleList.size() > 0) {
			personSimpleDao.saveBatch(personSimpleList);
		}
		if (relationList.size() > 0) {
			personCategoryRelationDAO
					.createPersonCategoryRelationBatch(relationList);
		}

		for (User user : userMap.values()) {
			userListHasNoPeople.add(user);
		}

		// 给没有人脉对象的用户创建人脉对象
		List<Map<String, Object>> userListToUpdate = new ArrayList<Map<String, Object>>(
				1500);
		System.out.println("共计有 " + userListHasNoPeople.size()
				+ " 个没有人脉对象的个人用户" + (System.currentTimeMillis() - start));
		personSimpleList.clear();
		personListToInsert.clear();
		for (User user : userListHasNoPeople) {
			Person person = new Person();
			person.setFromUserId(user.getId());
			person.setCreateUserId(user.getId());
			// 用户基本信息中的所在地，是指公司的所在地，原来是存储在tb_user表中
			person.setCountry(user.isCountry() ? "1" : "0");
			person.setLocationCountry(user.getProvince());// 国外时表示洲，国内时表示省
			person.setLocationCity(user.getCity());// 国外：国家，国内：市
			person.setLocationCounty(user.getCounty());// 国内：县
			person.setEmail(user.getEmail());
			person.setGender((byte) convertGender(user.getSex()));
			person.setPortrait(user.getPicPath());
			person.setTelephone(user.getMobile());
			person.setTaskId(MakeTaskId.getTaskId());
			person.setCompany(user.getCompanyName());
			person.setPosition(user.getCompanyJob());
			person.setRemark(user.getRemark());
			person.setPeopleType("其他人物");
			person.setTypeId(108);
			PersonName name = new PersonName();
			name.setFirstname(user.getName());// 名
			name.setLastname("");// 姓
			name.setParentType(Byte.valueOf("0"));// 新人脉的姓名无类型，第一个姓名为中文名
			person.setPeopleNameList(new ArrayList<PersonName>());
			person.getPeopleNameList().add(name);
			try {
				person.setId(this.getPersonIncreasedId());
				person = this.genName(person);
				person.setVirtual(Person.TYPE_VIRTUAL_USER);
				person.setCreateTime(new Date());
				person.setUpdateTime(new Date());
				personListToInsert.add(person);
				// personMongodbDao.save(person);

				PersonSimple personSimple = this.personConver(person,
						new PersonSimple());
				personSimpleList.add(personSimple);
				// personSimpleService.savePerson(personSimple);

				// person = this.save(person);//保存人脉, 并在mysql的简表中插入记录
				Map<String, Object> map = new HashMap<String, Object>(2, 1);
				map.put("id", user.getId());
				map.put("peopleId", person.getId().toString());
				userListToUpdate.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("开始插入" + personListToInsert.size() + "个人脉对象"
				+ (System.currentTimeMillis() - start));
		personMongoTemplate.insert(personListToInsert, "person");
		System.out.println("完成插入" + personListToInsert.size() + "个人脉对象"
				+ (System.currentTimeMillis() - start));
		personListToInsert.clear();

		System.out.println("开始插入" + personSimpleList.size() + "个人脉简表信息"
				+ (System.currentTimeMillis() - start));
		personSimpleDao.saveBatch(personSimpleList);
		System.out.println("完成插入" + personSimpleList.size() + "个人脉简表信息"
				+ (System.currentTimeMillis() - start));
		personSimpleList.clear();

		personSimpleDao.updateUserPeopleId(userListToUpdate);// 更新人脉id到user表
		System.out.println("结束时间：" + (System.currentTimeMillis() - start));
	}
	*/

	public void peopleSaveToPerson() {
		long start = System.currentTimeMillis();
		List<User> userList = userService.selectAllUser();// 查询所有个人用户，目前用户数很少，只有5000+
		List<Person> personListToInsert = new ArrayList<Person>();
		List<PersonSimple> personSimpleList = new ArrayList<PersonSimple>();
		List<Map<String, Object>> userListToUpdate = new ArrayList<Map<String, Object>>();
		List<Long> ids = new ArrayList<Long>(1000);
		for (User user : userList) {
			if (user.isVirtual() || StringUtils.isBlank(user.getPeopleId()))
				continue;
			ids.add(Long.valueOf(user.getPeopleId()));
		}
		List<Person> list = personMongodbDao.getByIds(ids);
		if (null == list)
			list = new ArrayList<Person>();
		List<Long> personIdsOK = new ArrayList<Long>();
		List<Long> personIdsSaved = new ArrayList<Long>();
		for (Person person : list) {
			personIdsOK.add(person.getId());
		}
		Long currentId;
		{
			String key = "person";
			String collectionName = "MongoIds";
			Criteria c = Criteria.where("name").is(key);
			Query query = new Query(c);
			Update update = new Update();
			update.inc("cid", userList.size() - personIdsOK.size() + 100);
			MongoIds mongoIds = personMongoTemplate.findAndModify(query, update,
					MongoIds.class, collectionName);
			currentId = mongoIds.getCid();
			System.out.println("currentId:" + currentId + ", userList size " + userList.size() + ", personIdsOK size " + personIdsOK.size());
		}
		for (User user : userList) {
			if (user.isVirtual())
				continue;
			if (StringUtils.isNotBlank(user.getPeopleId()) && personIdsOK.contains(Long.valueOf(user.getPeopleId()))) {
				continue;
			}
			Person person = new Person();
			/*
			if (StringUtils.isNotBlank(user.getPeopleId())) {
				PeopleTemp peopleTemp = oldPeopleTemplate.findById(user.getPeopleId(), PeopleTemp.class, "peopleTemp");
				if (null != peopleTemp) {
					person =  peopleToPerson(peopleTemp);
				}
			}
			*/
			person.setFromUserId(user.getId());
			person.setCreateUserId(user.getId());
			// 用户基本信息中的所在地，是指公司的所在地，原来是存储在tb_user表中
			person.setCountry(user.isCountry() ? "1" : "0");
			person.setLocationCountry(user.getProvince());// 国外时表示洲，国内时表示省
			person.setLocationCity(user.getCity());// 国外：国家，国内：市
			person.setLocationCounty(user.getCounty());// 国内：县
			person.setEmail(user.getEmail());
			person.setGender((byte) convertGender(user.getSex()));
			person.setPortrait(user.getPicPath());
			person.setTelephone(user.getMobile());
			person.setTaskId(MakeTaskId.getTaskId());
			person.setCompany(user.getCompanyName());
			person.setPosition(user.getCompanyJob());
			person.setRemark(user.getRemark());
			person.setPeopleType("其他人物");
			person.setTypeId(108);
			PersonName name = new PersonName();
			name.setFirstname(user.getName());// 名
			name.setLastname("");// 姓
			name.setParentType(Byte.valueOf("0"));// 新人脉的姓名无类型，第一个姓名为中文名
			person.setPeopleNameList(new ArrayList<PersonName>());
			person.getPeopleNameList().add(name);
			try {
				if (StringUtils.isNotBlank(user.getPeopleId())) {
					person.setId(Long.valueOf(user.getPeopleId()));
				} else {
					person.setId(currentId++);
				}
				person = this.genName(person);
				person.setVirtual(Person.TYPE_VIRTUAL_USER);
				person.setCreateTime(new Date());
				person.setUpdateTime(new Date());
				personListToInsert.add(person);
				personIdsSaved.add(person.getId());

				PersonSimple personSimple = this.personConver(person,
						new PersonSimple());
				personSimpleList.add(personSimple);

				Map<String, Object> map = new HashMap<String, Object>(2, 1);
				map.put("id", user.getId());
				map.put("peopleId", person.getId().toString());
				userListToUpdate.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("currentId:" + currentId);
		System.out.println("开始插入" + personListToInsert.size() + "个人脉对象"
				+ (System.currentTimeMillis() - start));
		personMongoTemplate.insert(personListToInsert, "person");
		System.out.println("完成插入" + personListToInsert.size() + "个人脉对象"
				+ (System.currentTimeMillis() - start));
		personListToInsert.clear();

		System.out.println("开始插入" + personSimpleList.size() + "个人脉简表信息"
				+ (System.currentTimeMillis() - start));
		personSimpleDao.saveBatch(personSimpleList);
		System.out.println("完成插入" + personSimpleList.size() + "个人脉简表信息"
				+ (System.currentTimeMillis() - start));
		personSimpleList.clear();

//		personSimpleDao.updateUserPeopleId(userListToUpdate);// 更新人脉id到user表
		if (personIdsSaved.size() > 0) {
			String personIds = StringUtils.join(personIdsSaved, ',');
			String url = String.valueOf(propertyMap.get("bigDataQueryHost"))
					+ "/person/operation.json?ids="
					+ personIds;
			try {
				System.out.println(url);
				String result = HttpClientHelper.get(url);
				System.out.println("the big data response result:" + result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("结束时间：" + (System.currentTimeMillis() - start));
	}

	private int convertGender(Integer gender) {
		if (null == gender)
			return 3;// 未知
		if (1 == gender || 2 == gender)
			return gender;
		return 3;
	}

	//推送MQ
	public void pushIndexByMQ(String flagTypes,String keys,String json) {
		
		RocketSendResult result = defaultMessageService.sendMessage(TopicType.CONNECTIONS_TOPIC, flagTypes, json);
		
		int bscode = result.getBscode();
//		System.out.println("msgId="+result.getSendResult().getMsgId());
//		logger.info("msgId="+result.getSendResult().getMsgId());
		
		if(bscode != 0) {
			StringBuilder sb = new StringBuilder("MQ error : bscode=");
			sb.append(bscode);
			sb.append(",errorMessage=");
			sb.append(result.getErrorMessage());
			sb.append(" | ");
			sb.append("flagTypes=");
			sb.append(flagTypes);
			sb.append(",keys=");
			sb.append(keys);
			sb.append(",json=");
			sb.append(json);
			logger.error(sb.toString());
		}
		
	}

	public List<PersonSimple> indexList(Map<String, Object> values) {
		
		int size = (Integer) values.get("size");
		int index = (Integer) values.get("index");
		
		Long userId = getLongValue(values.get("userId"));
		Long typeId = getLongValue(values.get("typeId"));
		Long regionId = getLongValue(values.get("regionId"));
		
		//职能、
		List<Integer> industryDirections = getIndustryDirections(getLongValue(values.get("firstIndustryDirectionId")),getLongValue(values.get("secondIndustryDirectionId")));
		
		Page page = new Page();
		page.setSize((long) size);
		page.setPage((long) index);
		
		// 获取这个区域下面所有区域id
		Set<Long> regionIds = this.getRegionIds(regionId);
		
		List<PersonSimple> list;
		
		String[] arr = new String[0];

		if (userId != -1) { // 未登录判断设定
			User user = userService.selectByPrimaryKey(userId);
			if (null != user && StringUtils.isNotBlank(user.getCareIndustryIds())) {
				arr = user.getCareIndustryIds().split(",");
			}
		}
		
		List<Long> industrys = new ArrayList<Long>(arr.length);
		
		for (String str : arr) {
			if (StringUtils.isBlank(str)) continue;
			industrys.add(Long.valueOf(str));
		}
		
		// FiXME
		industrys = null;
		
		//条件查询包括：个人用户感兴趣的行业 
		list = personSimpleDao.findByIndustry(industrys, typeId,
				regionIds, industryDirections, page.getStartNumber(),
				page.getSize(),null);
		
		return list == null ? new ArrayList<PersonSimple>():list;
	}
	
	private Long getLongValue(Object longObject) {
		if (longObject == null) {
			return null;
		}
		Long longValue = (Long) longObject;
		if(longValue == 0l) {
			return null;
		}
		return longValue;
	}
	
	private List<Integer> getIndustryDirections(Long firstIndustryDirectionId,Long secondIndustryDirectionId) {
		
		List<Integer> returnResult = null;
		
		if(firstIndustryDirectionId != null) {
			
			Map<String,Object> result = codeSortService.getIndustryDirection(firstIndustryDirectionId.intValue(),0,0);
			@SuppressWarnings("unchecked")
			List<IndustryDirection> industryDirections = (List<IndustryDirection>) result.get("industryDirections");
			returnResult = new ArrayList<Integer>(industryDirections.size());
			for (IndustryDirection industryDirection : industryDirections) {
				returnResult.add(industryDirection.getId());
			}
			
			return returnResult;
		}
		
		if(secondIndustryDirectionId != null) {
			
			returnResult = new ArrayList<Integer>(1);
			returnResult.add(secondIndustryDirectionId.intValue());
			
			return returnResult;
		}
		
		return returnResult;
	}

	public Long indexListCount(Map<String, Object> values) {
		
		Long userId = getLongValue(values.get("userId"));
		Long typeId = getLongValue(values.get("typeId"));
		Long regionId = getLongValue(values.get("regionId"));
		
		//职能、
		List<Integer> industryDirections = getIndustryDirections(getLongValue(values.get("firstIndustryDirectionId")),getLongValue(values.get("secondIndustryDirectionId")));
		// 获取这个区域下面所有区域id
		Set<Long> regionIds = this.getRegionIds(regionId);
		
		List<Long> industrys = new ArrayList<Long>();
		
		String[] arr = new String[0];

		if(userId != -1) {
			User user = userService.selectByPrimaryKey(userId);
			if (null != user && StringUtils.isNotBlank(user.getCareIndustryIds())) {
				arr = user.getCareIndustryIds().split(",");
			}
		}
		for (String str : arr) {
			if (StringUtils.isBlank(str))
				continue;
			industrys.add(Long.valueOf(str));
		}
		return personSimpleDao.findByIndustryCount(industrys, typeId, regionIds, industryDirections, null);
	}

	/**
	 * Method: initSerach <br>
	 * Description:  初始化人脉原始数据到MQ<br>
	 * Creator: xutianlong@gingtong.com <br>
	 * Date: 2016/1/19 16:08
	 */
	public String initOldDataSearch(Long start, Long end) {
		int execNum = 0;
			logger.info("人脉原始数据初始化到MQ。。。");
			// 首先获取mongoDB中的数据条数的最大值
		try {
			Long mongoIdIndex = this.getPersonIncreasedId();
			String message = "";
			// 如果最大值大于0说明有数据，可以进行下步操作
			logger.info("人脉数据库中的数据量是:{}", mongoIdIndex);
			if (mongoIdIndex > 0) {
				// 根据传入参数确定发送次数
				for (long x = start; x < end; x++) {
					// 从start开始获取数据的实体对象
					Person p = null;
					try {
						p = this.get(x);

					} catch (Exception e) {
						logger.error("跳过异常数据");

					}
					if(null != p){
						// 根据mongo里的数据反推获取简表数据
						try {
							PersonSimple personSimple = this.personConver(p, new PersonSimple());
							// 将数据推送到MQ
							pushIndexByMQ(FlagTypeUtils.createConnectionsFlag(), p.getId() + "", makeIndexData(p, personSimple));
						} catch (Exception e) {
							logger.error("跳过异常数据");
						}

						execNum++;
					}
					if (execNum == 1395) {
						logger.error("");
					}
				}
				message = "循环结束";
				logger.info("循环结束");
			}
			return "SUCCESS num:" + execNum;
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("人脉原始数据初始化到MQ异常。。。" + e.getMessage());
			return "ERROR num:" + execNum;
		}

	}

	public List<Person> discoverPersonList(String region,String industry,List<Long> permissonId,int index,int size) {
		// TODO Auto-generated method stub
		return personMongodbDao.discoverPersonList(region,industry,permissonId,index,size);
	}

	
	public Long discoverPersonCount(String region,String industry,List<Long> permissonId) {
		// TODO Auto-generated method stub
		return personMongodbDao.discoverPersonCount(region,industry,permissonId);
	}

	@Override
	public Person settingAndUpdate(Person person) throws Exception {
		// TODO Auto-generated method stub
	    logger.info("设置里面调用修改人脉方法publicFlag=="+person.getPermissions().get("publicFlag"));
		this.updateBasic(person);
		return person;
	}

	private String formatCSTDate(final String cstDate)
	{
		//java.util.Date对象
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		try {
			Date date = (Date) sdf.parse(cstDate); //2009-09-16
			String formatDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			return formatDate;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			logger.error("convert date failed. original date: " + cstDate);
		}
		return null;
	}

	private boolean needConvert(final String dateStr)
    {
        return (dateStr != null && (dateStr.indexOf("CST") > 0 || dateStr.indexOf("CDT") > 0));
    }

	@Override
	public List<Person> listAddressBook(long userId, int mailType) {
		// TODO Auto-generated method stub
		return personMongodbDao.listAddressBook(userId, mailType);
	}

	@Override
	public boolean isExist(long userId, int mailType, String mobile) {
		// TODO Auto-generated method stub
		return personMongodbDao.isExist(userId, mailType, mobile);
	}

	@Override
	public Person getPersonByCreateUserId(Long createUserId, String virtual) {
		
		return convert(personMongodbDao.getPersonByCreateUserId(createUserId, virtual));
	}
}
