package com.ginkgocap.ywxt.person.service;

import java.util.List;

import com.ginkgocap.ywxt.person.model.PersonTagRelation;


public interface PersonTagRelationService {
    /**
     * 保存标签关联
     * @param personTag
     * @return
     * @throws Exception
     */
    public int save(PersonTagRelation personTag) throws Exception;

    /**
     * 根据人脉id和人脉类型获得标签关联
     * @param personid 人脉id
     * @param personType 人脉类型
     * @return
     * @throws Exception
     */
    public List<PersonTagRelation> findByPerAndPt(Long personid , Byte personType) throws Exception;

}
