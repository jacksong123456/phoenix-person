package com.ginkgocap.ywxt.person.service;

import java.util.List;

import com.ginkgocap.ywxt.person.model.PermIds;
import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonPermission;

public interface PersonPermissionService {

    /**
     * 4．人脉详情
     *
     * @param personId 人脉id
     * @param personType 类型  1用户 2人脉
     * @return
     */
    public List<PersonPermission> findByPerAndPeTy(Long personId , Byte personType) throws Exception;

    /**
     * 人脉详情权限查询
     * @param personId 人脉id
     * @param personType 人脉类型
     * @param receiveId 接收者id
     * @return
     */
    public PersonPermission findByPerAndPeTyAndRec(Long personId , Short personType , Long receiveId) ;

    public void permissControl(PermIds permIds, Person person,  Long userId, Object noticeThreadPool) throws Exception ;

    public void deleteUserDemandPermission(Long sendId , Long personId , Byte personType) ;
}
