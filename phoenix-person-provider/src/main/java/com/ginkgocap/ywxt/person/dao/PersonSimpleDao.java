package com.ginkgocap.ywxt.person.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ginkgocap.ywxt.person.model.PersonSimple;

public interface PersonSimpleDao {

    public static enum IsPush {
        YES((short) 1), NO((short) 0);
        public Short code;

        IsPush(Short code) {
            this.code = code;
        }
    }

    public List<PersonSimple> findByCategory(Long userId, Long categoryId,String udate);

    public List<PersonSimple> findByTid(Long userId, Long tagId ,String udate);

    public List<PersonSimple> findByCategoryIdAndTid(Long userId, Long categoryId, Long tagId, String udate );
    
    public List<PersonSimple> findByUserId(Long userId ,String udate );

    public int save(PersonSimple personSimple);

    int saveBatch(List<PersonSimple> personSimples);

    public int deleteByPerIdAndPerType(Long personId, Short personType);

    public int updateByPerIdAndperType(PersonSimple personSimple);

    public List<PersonSimple> findByUIdAndN1AndN2(Long userId, String name1, String name2);
    public List<PersonSimple> findByPeIdsAndPetypeAndCaIdAndtagId(List<Long> personids ,Short persontype    ,  Long categoryId , Long tagId );
        /**
        * 接口 21．人脉首页列表
        * @param receiveId 接收者ID
        * @param sendId    发送者ID
        * @param isPush    是否是大数据推荐
        * @return
        */
    public List<PersonSimple> findByRecAndSendAndPush(Long receiveId, Long sendId, Short isPush, Long typeId, Set<Long> regionId, Set<Long> careerIds, Long limi1, Long limi2);

    public List<PersonSimple> findByPeIdsAndPeTypeAndCtime(List<Long> personIds , Integer personType , String updateDate);

    List<PersonSimple> findByIndustry(List<Long> industryId, Long typeId, Set<Long> regionIds,List<Integer> industryDirections, Long limi1, Long limi2,String keyword);

    Long findByIndustryCount(List<Long> industryId, Long typeId, Set<Long> regionIds, List<Integer> industryDirections,String keyword);

    Integer findPersonPType(Long personId, Integer personType);

    public Long findCountByRecAndSendAndPush(Long receiveId, Long sendId, Short isPush, Long typeId, Set<Long> regionIds, Set<Long> careerIds) ;

    //只用于数据移植
    public int updateUserPeopleId(List<Map<String, Object>> list);

    
    //---web项目新加----
    public Set<Long> selectPerson(int personType, Long userId, int ctype, String keyowrd,int order,String poepleRecordWord,long poepleRecordId,int size);
    
    public List<Long> selectPerson(int orderRule,int personType, Long userId, int ctype, String keyowrd,int order,int page,int size);
    
    public long selectPersonCount(int personType, Long userId, int ctype, String keyowrd);
    
    int findPersonExist(long personId);
}
