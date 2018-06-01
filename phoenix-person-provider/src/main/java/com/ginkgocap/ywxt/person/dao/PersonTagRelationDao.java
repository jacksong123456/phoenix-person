package com.ginkgocap.ywxt.person.dao;

import java.util.List;

import com.ginkgocap.ywxt.person.model.PersonTagRelation;


public interface PersonTagRelationDao {

    public int save(PersonTagRelation personTag);

    public int update(PersonTagRelation personTag);

    public int deleteByPersonId(Long personid);

    public List<PersonTagRelation> findByPerAndPt(Long personid, Byte personType);

    public int deleteByPerIdAndperType(Long personId, Byte personType);

    public List<PersonTagRelation> findPersonIdByTag(Long userId, Long tagId, Long startRow, int pageSize);

    public Integer selectPersonCountByTag(Long userId, Long tagId);

    /**
     * 根据标签ID删除关联到此标签上的人脉关联数据
     * @param tagId
     * @return
     */
    public int deleteByTagId(Long tagId);

}
