package com.ginkgocap.ywxt.person.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.dao.PersonTagRelationDao;
import com.ginkgocap.ywxt.person.model.PersonTagRelation;
import com.ginkgocap.ywxt.person.service.PersonTagRelationService;

@Service("personTagRelationService")
public class PersonTagRelationServiceImpl implements PersonTagRelationService {
    @Autowired
    private PersonTagRelationDao personTagDao;

    public int save(PersonTagRelation personTag) throws Exception {
        if (personTag.getTagId() == null || personTag.getTagId().equals(0)) {
            throw new Exception("TagId值错误");
        }
        personTag.setCtime(new Date());
        return personTagDao.save(personTag);
    }

    /**
     * 4．人脉详情 获得标签
     *
     * @param personid   人脉id
     * @param personType 类型 1 用户 2人脉
     * @return
     */
    public List<PersonTagRelation> findByPerAndPt(Long personid, Byte personType) throws Exception {
        if (personid == null || personType == null) {
            throw new Exception("数据错误");
        }
        return personTagDao.findByPerAndPt(personid, personType);
    }
}
