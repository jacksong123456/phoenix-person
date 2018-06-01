package com.ginkgocap.ywxt.person.mongodb.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonSimple;

public interface PersonMongodbDao {

	public void save(Person Person);

	public Person get(Long id);

	public Person getByIdAndCreateUserId(Long id, Long createUserId);

	List<Person> getUsersByCreateUserId(Collection<Long> userIds,
			Long createUserId);

	public Person getByUserId(Long userId);

	public long getCountByCreateUserId(Long createUserId);

	public void delete(Long id);

	public List<Person> getByNameAndCreateUserId(String name, Long createUserId);

	public List<Person> getByIds(Collection<Long> ids);

	public void update(Person person);

	public Person getByFromUserIdAndCreateUserId(Long userId);

	public List<Person> selectByCreatorId(long creatorId, int pageIndex,
			int pageSize);

	public List<Person> selectByUserId(long userId, int pageIndex, int pageSize);

	public List<Person> selectMail(String telephone, Long createrId);
	
    /**
     * 发现人脉列表
     * */
    public List<Person> discoverPersonList(String region,String industry,List<Long> permissonId,int index,int size);
    
    
    
    /**
     * 发现人脉总数
     * */
    public Long discoverPersonCount(String region,String industry,List<Long> permissonId);
    
    /**
     * 通讯录列表
     * */
    public List<Person> listAddressBook(long userId,int mailType);
    
    /**
     * 导入通讯录时判断是否已经存在
     * */
    public boolean isExist(long userId,int mailType,String mobile);
    
    /**
     * 获取人脉好友
     * @param createUserId
     * @param virtual
     * @return
     */
    public Person getPersonByCreateUserId(long createUserId,String virtual);
}
