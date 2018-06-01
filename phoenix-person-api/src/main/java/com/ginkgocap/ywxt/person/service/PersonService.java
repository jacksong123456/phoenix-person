package com.ginkgocap.ywxt.person.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonCategoryRelation;
import com.ginkgocap.ywxt.person.model.PersonSimple;

public interface PersonService extends Serializable {

    String TYPE_CUSTOM = "N";//自定义输入项代码
    String TYPE_DEFAULT = "0";//默认分类代码

    /**
     * @return 人脉下一个ID
     */
    public Long getPersonIncreasedId();

    /**
     * 保存
     *
     * @param person
     * @return
     */
    public Person save(Person person) throws Exception;

    /**
     * 1．创建人脉接口
     *
     * @param person                  人脉对象
     * @param personTagIds               人脉标签对象
     * @param personCategoryRelations 人脉、好友目录List
     * @param personType              类型 1 用户 2人脉 ，如果为null则是2
     * 添加到
     * 创建人脉
     *
     * FIXME 根本没用上
     */
    public Person create(Person person, List<Integer> personTagIds, List<PersonCategoryRelation> personCategoryRelations,
                         Byte personType, Long createUserId) throws Exception;

    /**
     * 1．创建人脉接口
     *
     * @param person       人脉对象
     * @param personTagIds    人脉标签对象
     * @param categoryIds  人脉、好友目录id数组
     * @param personType   类型 1 用户 2人脉 ，如果为null则是2
     * @param createUserId 创建人脉id
     * @throws Exception
     *
     * FIXME 根本没用上
     */
    public Person create(Person person, List<Integer> personTagIds, Set<Long> categoryIds, Byte personType, Long createUserId) throws Exception;

    /**
     * 2．更新人脉
     *
     * @param person                  人脉对象  persontype类型  1、用户2人脉
     * @param personTagIds               标签
     * @param personCategoryRelations 目录人脉关联
     * @return
     */
    public Person update(Person person, List<Integer> personTagIds, List<PersonCategoryRelation> personCategoryRelations,
                         Short persontype, Long createUserId) throws Exception;

    /**
     * 2．更新人脉
     *
     * @param person                    人脉对象  persontype类型  1、用户2人脉
     * @param personTagIds                 标签
     * @param personCategoryRelationIds 目录ids
     * @param createUserId              创建人脉id
     * @return
     */
    public Person update(Person person, List<Integer> personTagIds, Set<Long> personCategoryRelationIds, Short persontype, Long createUserId) throws Exception;

    void updateBasic(Person person) throws Exception;

    /**
     * @param person
     * @param name 注册完成第一次登录时，填写昵称时调用
     * @throws Exception
     */
    void updateName(Person person, String name) throws Exception;

    /**
     * 3．删除人脉
     *
     * @param personid     人脉对象id
     * @param createUserId 创建人 personType 类型1用户2人脉
     * @throws Exception
     */
    public void delete(Long personid, Long createUserId, Byte personType) throws Exception;

    /**
     * 4．人脉详情
     *
     * @param id 人脉id
     * @return
     * @throws Exception
     */
    public Person get(Long id) throws Exception;

    List<Person> getByIds(Collection<Long> ids) throws Exception;

    /**
     * 20．转为人脉
     *
     * @param id           人脉id
     * @param createUserId 创建人id
     * @param fromUserId   转换人id
     * @throws Exception
     */
    public Person copy(Long id, Long createUserId, Long fromUserId) throws Exception;

    /**
     * 21．人脉首页列表
     * @param userId 创建人id
     *发送者为当前用户接收者不为当前用户并且不是大数据推送的
     */
    public List<PersonSimple> indexList(Long userId, Long typeId, Long regionId, Long careerId, Integer index, Integer size, String keyword, String careIndustryIds) throws Exception;

    /**
     * 发现首页列表接口
     * */
    public List<PersonSimple> indexList(Map<String,Object> values);

    public Long indexListCount(Map<String,Object> values);

    /**
     * 24．可合并资料的人脉列表
     *
     * @param basePeopleId
     * @param createUserId 用户id
     * @param personType   类型 1用户 2 人脉
     * @return 获取选中的同名的人脉
     */
    public List<Person> showMerges(Long basePeopleId, Long createUserId, Byte personType) throws Exception;

    /**
     * 25．合并人脉资料
     *
     * @param otherPeopleId 被合并的人脉对象的ID
     * @param people        合并后的人脉对象
     * @param createUserId  创建人的id
     * @throws Exception
     */
    public void merge(Long otherPeopleId, Person people, Long createUserId) throws Exception;

    /**
     * 创建或者修改
     * @param person  人脉
     * @param personTagIds 标签
     * @param personType 类型
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    public Person createOrUpdate(Person person,  List<Integer> personTagIds,  Set<Long> categoryids, Byte personType , Long userId) throws Exception ;

    public Person getByIdAndCreateUserId(Long id, Long createUserId) ;

    List<Person> getUsersByCreateUserId(Collection<Long> userIds, Long createUserId);

    public Person getByUserId(Long userId) ;

    public List<Person> selectByCreatorId(long creatorId, int pageIndex, int pageSize);

//    public Map<String, Object> searchPeople(String query, String coreName, int currentPage, int pageSize, Map params) throws Exception  ;

    /**
     * 获取我创建和我收藏的人脉列表
     * @param userid 当前用户id
     * @return
     */
    public List<Person> getUsers(Long userid) ;

    public List<Person> selectByUserId(long userId,  int pageIndex, int pageSize) ;

    public Long indexListCount(Long userId, Long typeId, Long regionId, Long careerId , String keyword,String careIndustryIds) throws Exception ;

    /**
     * 导入通讯录判断
     *
     * @param telephone（电话号码）
     * @param createrId（创建者ID）
     * @return
     */
    public Boolean hasMail(String telephone, Long createrId);

    /**
     * 保存人脉和目录关系
     * @param person
     * @param personCategoryRelationIds
     */
    public void savePersonAndCategory(Person person ,Set<Long> personCategoryRelationIds, long userId);

//    public Person peopleToPerson(PeopleTemp peopleTemp) ;
    public void peopleSaveToPerson() ;

    /**
     * Method: initSearch<br>
     * Description: 初始化人脉数据到MQ <br>
     * Creator: xutianlong@gingtong.com <br>
     * Date: 2016/1/19 16:10
     */
    public String initOldDataSearch(Long start, Long end);

    /**
     * 类型
     */
    public static enum Persontype {
        //人脉               //用户
        person((byte) 2), user((byte) 1);
        public Byte code;

        Persontype(Byte code) {
            this.code = code;
        }
    }
    
    
    /**
     * 发现人脉列表
     * */
    public List<Person> discoverPersonList(String region,String industry,List<Long> permissonId,int index,int size);
    
    
    
    /**
     * 发现人脉总数
     * */
    public Long discoverPersonCount(String region,String industry,List<Long> permissonId);
    
    
    public Person settingAndUpdate(Person person) throws Exception ;
    
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
    public Person getPersonByCreateUserId(Long createUserId,String virtual);
    
    
    
}
