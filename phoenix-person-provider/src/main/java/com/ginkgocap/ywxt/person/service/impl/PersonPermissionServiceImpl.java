package com.ginkgocap.ywxt.person.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.person.dao.PersonPermissionDao;
import com.ginkgocap.ywxt.person.dao.PersonSimpleDao;
import com.ginkgocap.ywxt.person.model.Constants;
import com.ginkgocap.ywxt.person.model.PermIds;
import com.ginkgocap.ywxt.person.model.PermissPeople;
import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonName;
import com.ginkgocap.ywxt.person.model.PersonPermDales;
import com.ginkgocap.ywxt.person.model.PersonPermXiaoles;
import com.ginkgocap.ywxt.person.model.PersonPermZhongles;
import com.ginkgocap.ywxt.person.model.PersonPermission;
import com.ginkgocap.ywxt.person.mongodb.dao.PersonMongodbDao;
import com.ginkgocap.ywxt.person.service.PersonPermissionService;
import com.ginkgocap.ywxt.person.service.PersonService;
import com.ginkgocap.ywxt.person.thread.NoticeThreadPool;
import com.ginkgocap.ywxt.user.model.User;
import com.ginkgocap.ywxt.user.service.FriendsRelationService;

@Service("personPermissionService")
public class PersonPermissionServiceImpl implements PersonPermissionService {

    private Logger logger = LoggerFactory.getLogger(PersonPermissionServiceImpl.class);

    @Autowired
    PersonPermissionDao personPermissionDao;

    @Autowired
    private PersonMongodbDao personMongodbDao;
    @Autowired
    private PersonSimpleDao personSimpleDao;
    @Autowired
    FriendsRelationService friendsRelationService ;
    public static final  byte DYNAMIC_TYPE_PERSON_CREATE =  30;//创建人脉的动态

    /**
     * 4．人脉详情
     *
     * @param personId   人脉id
     * @param personType 类型 1用户 2人脉
     * @return
     */
    public List<PersonPermission> findByPerAndPeTy(Long personId,
                                                   Byte personType) throws Exception {
        if (personId == null) {
            throw new Exception("数据错误");
        }
        if (personType == null) {
            //如果persontype为null 默认为人脉
            personType = PersonService.Persontype.person.code;
        }
        return personPermissionDao.findByPerAndPeTy(personId, personType);
    }

    public PersonPermission findByPerAndPeTyAndRec(Long personId, Short personType, Long receiveId) {
        PersonPermission per = personPermissionDao.findByPerAndPeTyAndRec(personId, personType, receiveId);
        if (null != per)
            return per;
        Integer ptype = personSimpleDao.findPersonPType(personId, Integer.valueOf(personType));
        if (null != ptype) {
            per = new PersonPermission();
            per.setPtype(ptype.shortValue());
        }
        return per;
    }

    @Transactional
    public void permissControl(PermIds permIds, Person person,  Long userId, Object noticeThreadPool) throws Exception {
        if (userId==null || person==null){
            throw new Exception("人脉权限数据错误") ;
        }
        User user = new User() ;
        user.setId(userId);
        logger.info("用户[{}]开始创建权限", user.getId());
        // 处理权限
        if (null == permIds)
            permIds = new PermIds();
        Boolean dule = permIds.getDule();
        if (dule==null){
            throw new Exception("权限未设置") ;
        }
        //删除非大数据推送的权限
        personPermissionDao.deleteByPerson(person.getId(), (short) (person.isUser() ? 1 : 2), userId);//1-用户；2-人脉
        // 判断处理非独乐情况，独乐不加权限
        if (dule) {//独乐时，只需要发送动态、或更新动态的时间即可
            addDynamic(person, user, null , DYNAMIC_TYPE_PERSON_CREATE, (NoticeThreadPool)noticeThreadPool);
        }else{
//            Boolean bool =
                    insertPermission(permIds, user, person, DYNAMIC_TYPE_PERSON_CREATE, (NoticeThreadPool)noticeThreadPool);
            /*if (!bool) {
                logger.info("权限存储失败!");
                throw new Exception("权限存储失败!");
            }*/
        }
        //syncDemandData(person.getId());
        logger.info("用户[{}]ID:[{}]", user.getId());
    }
    /*
    * 删除权限表相关记录，重新插入权限表权限，然后发送动态
    * */
    private Boolean insertPermission( PermIds permIds, User user, Person person , Byte dynamicType, NoticeThreadPool noticeThreadPool) throws Exception {
        Map<Integer, List<String>> permMap = getPermMap(permIds, user);
        insertPermission(person.getId(), user.getId(), permMap);
        addDynamic(person, user, permMap, dynamicType, noticeThreadPool);
        return true;
    }

    /**
     * 添加动态
     * @param person
     * @param user
     * @param permMap
     */
    private void addDynamic(Person person, User user,
                           Map<Integer, List<String>> permMap ,  Byte type, NoticeThreadPool noticeThreadPool) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("type", type.toString());
        if (person.isUser()) {
            param.put("lowType", "1");//用户
        } else {
            param.put("lowType", "2");//人脉
        }
        param.put("createrId", String.valueOf(user.getId()));
        if (StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getPicPath())) {
            Person per = personMongodbDao.getByFromUserIdAndCreateUserId(user.getId());
            if (null != per && per.getPeopleNameList() != null
                    && per.getPeopleNameList().size() > 0 && StringUtils.isBlank(user.getName())) {
                PersonName name = per.getPeopleNameList().get(0);
                user.setName(StringUtils.trimToEmpty(name.getLastname()) + StringUtils.trimToEmpty(name.getFirstname()));
            }
            if (StringUtils.isBlank(user.getPicPath()) && per!=null) {
                user.setPicPath(per.getPortrait());
            }
        }
        param.put("createrName", user.getName());
        PersonName peopleName   = null ;
        if (person.getPeopleNameList()!=null && person.getPeopleNameList().size()>0){
            peopleName = person.getPeopleNameList().get(0) ;
        }
        if (peopleName!=null) {//title是第一行
            param.put("title", StringUtils.trimToEmpty(peopleName.getLastname())+StringUtils.trimToEmpty(peopleName.getFirstname()));
        }
        //content是第二行
        if (StringUtils.isBlank(person.getCompany()))
            param.put("content", StringUtils.trimToEmpty(person.getPosition()));
        else if (StringUtils.isBlank(person.getPosition()))
            param.put("content", StringUtils.trimToEmpty(person.getCompany()));
        else
            param.put("content", StringUtils.trimToEmpty(person.getCompany())+" "+StringUtils.trimToEmpty(person.getPosition()));
        param.put("targetId", String.valueOf(person.getId()));
        param.put("picPath", user.getPicPath());// 用户头像，指的是创建人的头像
        param.put("imgPath", person.getPortrait());// targetId对应的人脉的头像
        
        param.put("forwardingContent", "");
        Map<String, List<Long>> map = new HashMap<String, List<Long>>();
        List<Long> list;
        if (permMap != null) {
            Set<Integer> set = permMap.keySet();
            Iterator<Integer> iter = set.iterator();
            while (iter.hasNext()) {
                int v = iter.next();
                List<String> sList = permMap.get(v);
                list = new ArrayList<Long>();
                for (String s : sList) {
                    list.add(Long.parseLong(s));
                }
                if (v == Constants.PermissionType.dales.v()) {
                    map.put("dale", list);
                } else if (v == Constants.PermissionType.zhongles.v()) {
                    map.put("zhongle", list);
                } else if (v == Constants.PermissionType.xiaoles.v()) {
                    map.put("xiaole", list);
                }
            }
            param.put("receiverIds", map);
        } else {
            param.put("receiverIds", new HashMap());
        }
       
        //动态新增
        param.put("createType", "1");
        param.put("gender", person.getGender());
        
        noticeThreadPool.noticeDataCenter(param);
    
    }

    /**
     *  删除用户需求权限
     * @param sendId
     *  @param personId
     */
    public void deleteUserDemandPermission(Long sendId , Long personId , Byte personType) {
        personPermissionDao.deleteBySendIdAndPersonId(sendId, personId, personType, PersonSimpleDao.IsPush.YES.code);
    }
    private boolean isAll(List<? extends PermissPeople> list) {
        if (list == null || list.size() == 0)
            return false;
        PermissPeople ids = list.get(0);
        if (ids == null)
            return false;
        Long id = ids.getId();
        if (id != null && id.intValue() == -1)
            return true;

        return false;
    }
    /**
     * 解析json字符串
     *
     * @param
     * @author haiyan
     */
    private List<String> getPermList(List<? extends PermissPeople> permList) {
        List<String> perList = new ArrayList<String>();
        if (permList != null) {
            for (PermissPeople perm : permList) {
                perList.add(perm.getId() + "");
            }
        }
        return perList;

    }
    private List<String> getPermList(User user, List<? extends PermissPeople> permIds) {
        List<String> permList = new ArrayList<String>();
        List<User> userList = getUserFriends(user.getId());//获取所有好友：个人和组织好友
        if (userList != null) {
            for (User u : userList) {
                permList.add(u.getId() + "");
            }
        }
        return permList;
    }
    /**
     * 获取所有好友：个人和组织好友
     *
     * @param userId
     * @return
     * @author haiyan
     */
    private List<User> getUserFriends(long userId) {
        return   friendsRelationService.findAllFriendsByUserId(userId);
    }


    /**
     * 权限信息处理
     *
     * @param
     * @return List<String>
     * @author haiyan
     */
    private Map<Integer, List<String>> getPermMap(PermIds demandPermIds,
                                                  User user) {
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        List<String> permList ;
        // {"dule":false,"xiaoles":[],"zhongles":[{"id":13556,"name":"张斌"},{"id":12454,"name":"林美霞"},{"id":13835,"name":"张桂珍"}],"dales":[{"id":13414,"name":"股市水晶球"},{"id":10089,"name":"杨楠"},{"id":13247,"name":"曾添_dataplayer"}]}
        List<PersonPermDales> daleList =     demandPermIds.getDales();
        List<PersonPermZhongles> zhongleList = demandPermIds.getZhongles();
        List<PersonPermXiaoles> xiaoleList = demandPermIds.getXiaoles();
        // 大乐全选
        if (isAll(daleList)) {
            permList = getPermList(user, daleList);
            if (permList.contains("0") == false)
                permList.add("0");
            map.put(Constants.PermissionType.dales.v(), permList);
        } else {
            permList = getPermList(daleList);
            map.put(Constants.PermissionType.dales.v(), permList);
        }
        // 中乐全选
        if (isAll(zhongleList)) {
            permList = getPermList(user, zhongleList);
            if (permList.contains("0") == false)
                permList.add("0");
            map.put(Constants.PermissionType.zhongles.v(), permList);
        } else {
            permList = getPermList(zhongleList);
            map.put(Constants.PermissionType.zhongles.v(), permList);
        }
        // 小乐全选
        if (isAll(xiaoleList)) {
            permList = getPermList(user, xiaoleList);
            if (permList.contains("0") == false)
                permList.add("0");
            map.put(Constants.PermissionType.xiaoles.v(), permList);
        } else {
            permList = getPermList(xiaoleList);
            map.put(Constants.PermissionType.xiaoles.v(), permList);
        }
        return map;
    }


    public void insertPermission(Long personId, Long userId,
                                    Map<Integer, List<String>> permMap) {
        logger.info("开始添加权限信息到数据库");
        List<PersonPermission> list = new ArrayList<PersonPermission>();
        if (permMap != null) {
            Set<Integer> set = permMap.keySet();
            Iterator<Integer> iter = set.iterator();
            PersonPermission personPermission ;
            while (iter.hasNext()) {
                Integer key = iter.next();
                List<String> ids = permMap.get(key);
                for (String id : ids) {
                    personPermission = new PersonPermission();
                    personPermission.setSendid(userId);
                    personPermission.setReceiveid(Long.parseLong(id));
                    personPermission.setPtype(key.shortValue());
                    personPermission.setPersonid(personId);
                    personPermission.setIspush((short) 0);//不是大数据推送的
                    personPermission.setPersontype(PersonService.Persontype.person.code.shortValue());
                    personPermission.setCreatetime(new Date());
                    list.add(personPermission);
                }
            }
        }
        personPermissionDao.batchSave(list);
    }

}
