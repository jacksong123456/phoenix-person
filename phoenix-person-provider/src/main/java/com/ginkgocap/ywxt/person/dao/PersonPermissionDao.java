package com.ginkgocap.ywxt.person.dao;

import java.util.List;

import com.ginkgocap.ywxt.person.model.PersonPermission;

public interface PersonPermissionDao {

    public int save(PersonPermission personPermission);

    public int deleteByPersonId(Long peronid);

    public int deleteByPerIdAndperType(Long personId, Byte personType);

    public List<PersonPermission> findByPerAndPeTy(Long personId, Byte personType);

    public int deleteByPerson(Long personId, Short personType ,Long sendId ) ;

    public PersonPermission findByPerAndPeTyAndRec(Long personId , Short personType , Long receiveId);

    public int  deleteBySendIdAndPersonId(Long sendId , Long  personId , Byte personType  , Short isPush) ;

    public void batchSave(List<PersonPermission> personPermissions)  ;
}
