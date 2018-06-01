package com.ginkgocap.ywxt.person.service;

import java.util.List;
import java.util.Set;

import com.ginkgocap.ywxt.person.model.PersonSimple;

public interface PersonSimpleService {

    /**
     * 类型
     */
    public static enum Persontype {
        //人脉               //用户
        person((short) 2), user((short) 1);
        public Short code;

        Persontype(Short code) {
            this.code = code;
        }
    }

    /**
     * 10．好友/人脉列表
     *
     * @param categoryId 目录id
     * @param tagId      标签id
     * @return
     */
    public List<PersonSimple> findByCategoryIdAndTid(Long userId,
                                                     Long categoryId, Long tagId, String udate ) ;
    /**
     * 保存人脉
     * @param personSimple
     * @return
     */
    public int savePerson(PersonSimple personSimple);

    /**
     * 根据personid 和类型获取数据
     * @param personids
     * @param persontype
     * @return
     */
    public List<PersonSimple> findByPeIdsAndPetypeAndCaIdAndtagId(List<Long> personids ,Short persontype    ,  Long categoryId , Long tagId );

    public List<PersonSimple> findByPeIdsAndPeTypeAndCtime(List<Long> personIds , Integer personType , String updateDate)  ;

    /**
     * 获得电话号码
     * @param personSimples
     * @return
     */
    public List<PersonSimple> getMobilePhonesAndFixedPhones(List<PersonSimple> personSimples);
    /**
     * 其实可以公用一个接口，但是为了保存但是混排翻页的思想，固保留本接口 
     * */
    public Set<Long> selectPerson(int personType, Long userId, int ctype, String keyowrd,int order,String poepleRecordWord,long poepleRecordId,int size);
    
    public List<Long> selectPerson(int orderRule,int personType, Long userId, int ctype, String keyowrd,int order,int page,int size);
    
    public long selectPersonCount(int personType, Long userId, int ctype, String keyowrd);
}
