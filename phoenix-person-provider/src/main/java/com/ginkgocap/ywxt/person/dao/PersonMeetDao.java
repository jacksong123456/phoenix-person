package com.ginkgocap.ywxt.person.dao;

import java.util.List;

import com.ginkgocap.ywxt.person.model.PersonMeet;

public interface PersonMeetDao {

    public List<PersonMeet> findByPersonid(Long personid);

    public PersonMeet findByCidAndMid(Long cid, Long meetId);

    public int deleteByCidAndMeetId(Long cid, Long meetId);

    public int updateByCidAndMeetId(PersonMeet personMeet);

    public int save(PersonMeet personMeet);

    public int deleteByPersonId(Long personId);

    public int deleteByPerIdAndperType(Long personId, Byte personType);

}
