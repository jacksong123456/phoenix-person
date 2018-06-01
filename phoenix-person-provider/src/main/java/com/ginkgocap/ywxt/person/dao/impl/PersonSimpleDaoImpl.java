package com.ginkgocap.ywxt.person.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.person.dao.PersonSimpleDao;
import com.ginkgocap.ywxt.person.model.PersonSimple;

@Repository
public class PersonSimpleDaoImpl extends SqlSessionDaoSupport implements
        ApplicationContextAware, PersonSimpleDao {
	
	private static Map<String, Long> cacheMap = new ConcurrentHashMap<String, Long>();
    private ApplicationContext applicationContext;
    private static final String NAMESPACE = "person_simple";

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    public List<PersonSimple> findByCategory(Long userId, Long categoryId,String udate) {
        Map map = new HashMap();
        map.put("categoryId", categoryId);
        map.put("userId", userId);
        map.put("udate" , udate);
        return getSqlSession().selectList(NAMESPACE + ".findByCategory", map);
    }
    public List<PersonSimple> findByTid(Long userId, Long tagId,String udate) {
        Map map = new HashMap();
        map.put("tagId", tagId);
        map.put("userId", userId);
        map.put("udate" , udate) ;
        return getSqlSession().selectList(NAMESPACE + ".findByTid", map);
    }

    public List<PersonSimple> findByCategoryIdAndTid(Long userId, Long categoryId, Long tagId,String udate) {
        Map map = new HashMap();
        map.put("categoryId", categoryId);
        map.put("tagId", tagId);
        map.put("userId", userId);
        map.put("udate" , udate) ;
        return getSqlSession().selectList(NAMESPACE + ".findByCategoryIdAndTid", map);
    }

    public List<PersonSimple> findByUserId(Long userId,String udate ) {
        Map map = new HashMap();

        map.put("userId", userId);
        map.put("udate" , udate);
        return getSqlSession().selectList(NAMESPACE + ".findByUserId", map);
    }
    private String emojiReplace(String str) {
        if (StringUtils.isBlank(str))
            return str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int code = str.codePointAt(i);
//            if (code < 0x1F6C0 && code > 0x1F004) {
//                sb.append((char) 0x1b);// ESC键
//                sb.append(Integer.toHexString(code));
//                i++;
//                continue;
//            }
            if (Character.charCount(code) != 1)
                continue;
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public int save(PersonSimple personSimple) {
        personSimple.setName1(emojiReplace(personSimple.getName1()));
        personSimple.setName2(emojiReplace(personSimple.getName2()));
        return getSqlSession().insert(NAMESPACE + ".insert", personSimple);
    }

    public int saveBatch(List<PersonSimple> personSimples) {
        if (null == personSimples || personSimples.isEmpty())
            return 0;
        for (PersonSimple personSimple : personSimples) {
            personSimple.setName1(emojiReplace(personSimple.getName1()));
            personSimple.setName2(emojiReplace(personSimple.getName2()));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", personSimples);
        return getSqlSession().insert(NAMESPACE + ".insertBatch", map);
    }

    public int deleteByPerIdAndPerType(Long personId, Short personType) {
        Map map = new HashMap();
        map.put("personId", personId);
        map.put("personType", personType);
        return getSqlSession().delete(NAMESPACE + ".deleteByPerIdAndPerType", map);
    }

    public int updateByPerIdAndperType(PersonSimple personSimple) {
        
    	//为什么不执行更新操作呢？而是删除以前的，不理解 by zhangzhen FIXME
    	this.deleteByPerIdAndPerType(personSimple.getPersonid(), personSimple.getPersontype());
    	
    	return this.save(personSimple);
    }

    public List<PersonSimple> findByUIdAndN1AndN2(Long userId, String name1, String name2) {
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("name1", StringUtils.trimToEmpty(name1));
        map.put("name2", StringUtils.trimToEmpty(name2));
        return getSqlSession().selectList(NAMESPACE + ".findByUIdAndN1AndN2", map);
    }



    public List<PersonSimple> findByPeIdsAndPetypeAndCaIdAndtagId(List<Long> personids ,Short persontype    ,  Long categoryId , Long tagId ){
        Map map = new HashMap();
        map.put("personIds", StringUtils.join(personids, " , "));
        map.put("personType", persontype);
        map.put("categoryId" , categoryId) ;
        map.put("tagId" , tagId) ;
        return getSqlSession().selectList(NAMESPACE + ".findByPeIdsAndPetypeAndCaIdAndtagId", map);
    }

    /**
     * 接口 21．人脉首页列表
     */
    @Deprecated
    public List<PersonSimple> findByRecAndSendAndPush(Long receiveId, Long sendId, Short isPush, Long typeId, Set<Long> regionIds, Set<Long> careerIds, Long limi1, Long limi2) {
        Map map = new HashMap();
        map.put("receiveId", receiveId);
        map.put("sendId", sendId);
        map.put("isPush", isPush);
        map.put("typeId", typeId);
        if (regionIds!=null && regionIds.size()>0) {
            map.put("regionIds", StringUtils.join(regionIds, " , "));
        }
        if (careerIds!=null && careerIds.size()>0) {
            map.put("careerIds", StringUtils.join(careerIds, " , "));
        }
        map.put("limi1", limi1);
        map.put("limi2", limi2);
        return getSqlSession().selectList(NAMESPACE + ".findByRecAndSendAndPush", map);
    }

    /**
     * 接口 21．人脉首页列表（查大数据推送给行业的）
     */
    public List<PersonSimple> findByIndustry(List<Long> industryId, Long typeId, Set<Long> regionIds, List<Integer> industryDirections, Long limi1, Long limi2,String keyword) {
        
    	Map<String,Object> map = new HashMap<String,Object>(8);
        
		if (!CollectionUtils.isEmpty(industryId)) {
			map.put("industryId", StringUtils.join(industryId, ","));
		}
		if (!CollectionUtils.isEmpty(regionIds)) {
			map.put("regionIds", StringUtils.join(regionIds, " , "));
		}
		if (!CollectionUtils.isEmpty(industryDirections)) {
			map.put("industryDirections",StringUtils.join(industryDirections, " , "));
		}
		map.put("limi1", limi1);
		map.put("limi2", limi2);
		map.put("typeId", typeId);
		map.put("name", keyword);
        return getSqlSession().selectList("person_push.findByIndustry", map);
    }
    
    public Long findByIndustryCount(List<Long> industryId, Long typeId, Set<Long> regionIds, Set<Long> careerIds, String keyowrd) {
    	 
        if (typeId == null && CollectionUtils.isEmpty(regionIds) && CollectionUtils.isEmpty(careerIds) && StringUtils.isEmpty(keyowrd)) {
                return findByIndustryCount(industryId);
        } else {

                 Map map = new HashMap();
                 if (industryId == null || industryId.isEmpty()) {
                          // map.put("industryId",
                          // "372,394,444,503,515,523,544,557,565,583");//10个顶级行业
                 } else {
                          map.put("industryId", StringUtils.join(industryId, ","));
                 }
                 map.put("typeId", typeId);
                 if (regionIds != null && regionIds.size() > 0) {
                          map.put("regionIds", StringUtils.join(regionIds, ","));
                 }
                 if (careerIds != null && careerIds.size() > 0) {
                          map.put("careerIds", StringUtils.join(careerIds, ","));
                 }
                 map.put("keyowrd", keyowrd);
                 Map<String, Long> countMap = getSqlSession().selectOne("person_push.findByIndustryCount", map);
                 return countMap.get("cnt");
        }
}
    
    /**
     * 查找大数据推送过来的人脉或用户的最大权限
     * @param personId
     * @param personType
     * @return
     */
    public Integer findPersonPType(Long personId, Integer personType) {
        Map map = new HashMap();
        map.put("personId", personId);
        map.put("personType", personType);
        map =  getSqlSession().selectOne("person_push.findPersonPType", map);
        if (null == map)
            return null;
        Object ptype = map.get("ptype");
        if (null == ptype || ptype.toString().length() == 0)
            return null;
        return Integer.valueOf(ptype.toString());
    }



    /**
     * 接口 21．人脉首页列表条数
     */
    public Long findCountByRecAndSendAndPush(Long receiveId, Long sendId, Short isPush, Long typeId, Set<Long> regionIds, Set<Long> careerIds) {
        Map map = new HashMap();

        map.put("receiveId", receiveId);
        map.put("sendId", sendId);
        map.put("isPush", isPush);
        map.put("typeId", typeId);
        if (regionIds!=null && regionIds.size()>0) {
            map.put("regionIds", StringUtils.join(regionIds, " , "));
        }
        if (careerIds!=null && careerIds.size()>0) {
            map.put("careerIds", StringUtils.join(careerIds, " , "));
        }
        Map<String  ,Long > countMap =  getSqlSession().selectOne(NAMESPACE + ".findCountByRecAndSendAndPush", map);
        return countMap.get("count");
    }



    public List<PersonSimple> findByPeIdsAndPeTypeAndCtime(List<Long> personIds , Integer personType , String updateDate){
        Map map = new HashMap() ;
        map.put("personIds" , StringUtils.join(personIds, " , ")) ;
        map.put("personType" , personType) ;
        map.put("updateDate" , updateDate) ;
        return getSqlSession().selectList(NAMESPACE + ".findByPeIdsAndPeTypeAndCtime", map);

    }

    //只用于数据移植
    public int updateUserPeopleId(List<Map<String, Object>> list) {
        for (Map map : list) {
            getSqlSession().update(NAMESPACE + ".updateUserPeopleId", map);
        }
        return list.size();
    }

	public Set<Long> selectPerson(int personType, Long userId, int ctype,
			String keyword, int order, String poepleRecordWord,
			long poepleRecordId, int size) {
			Map map = new HashMap();
	        map.put("personType", personType);
	        map.put("userId", userId);
	        map.put("ctype", ctype);
	        map.put("keyword", keyword);
	        map.put("order", order);
	        map.put("size", size);
	        map.put("poepleRecordWord",StringUtils.isBlank(poepleRecordWord) ? null :poepleRecordWord);
	        map.put("poepleRecordId", poepleRecordId);
	        List<Map<String  ,Long >> userMaps =  getSqlSession().selectList(NAMESPACE+".selectPerson", map);
	        Set<Long> personIds = new HashSet<Long>() ;
	        for (Map<String  ,Long > map1:userMaps){
	            personIds.add(map1.get("personId"));
	        }
	        return personIds;
	}

	public long selectPersonCount(int personType, Long userId, int ctype,
			String keyword) {
		
		Map map = new HashMap();
		map.put("ctype", ctype);
		map.put("userId", userId);
		map.put("keyword", keyword);
		map.put("personType", personType);
		
		Map<String  ,Long > count = getSqlSession().selectOne(NAMESPACE+".selectPersonCount",map);
		
		return count.get("count");
	}


	public List<Long> selectPerson(int orderRule,int personType, Long userId,
			int ctype, String keyword, int order, int page, int size) {
		Map map = new HashMap();
        
		map.put("personType", personType);
        map.put("userId", userId);
        map.put("ctype", ctype);
        map.put("keyword", keyword);
        map.put("order", order);
        map.put("size", size);
        map.put("page", page);
        map.put("orderRule", orderRule);
        
        List<Map<String  ,Long >> userMaps =  getSqlSession().selectList(NAMESPACE+".selectPerson", map);
        List<Long> result = new ArrayList<Long>(userMaps.size());
        Set<Long> personIds = new HashSet<Long>(userMaps.size());
        for (Map<String  ,Long > map1:userMaps) {
        	
        	Long temp = map1.get("personId");
            
        	if( personIds.add(temp) ) {
        		result.add(temp);
            }
            
        }
        return result;
	}
	
	/**
	 * 通过行业id列表获取相关行业对应的人脉总数
	 * @param industryIds,将行业id排序后拼成字符串结构如"indu_123_345_456"，作为Key，存储在map中
	 * @return map中key对应的值
	 */
    private Long findByIndustryCount(List<Long> industryIds) {
    	String key = "";
    	if(industryIds == null || industryIds.size()==0) {
    		key = "indu_all";
    	} else {
	    	Collections.sort(industryIds);
	    	String industries = StringUtils.join(industryIds, "_");
	    	key = "indu_"+industries;
    	}
    	Long count = cacheMap.get(key);
    	// cacheMap中不存在时，查库，设置值
    	if ( count == null ) {
    		Map map = new HashMap();
    		if(!key.equals("indu_all"))	map.put("industryId", StringUtils.join(industryIds, ","));
    		Map<String, Long> countMap = getSqlSession().selectOne("person_push.findByIndustryCount", map);
    		count = countMap.get("cnt");
    		cacheMap.put(key, count);
    	}
    	return count;
    }
    
    public int findPersonExist(long personId){
        Map map = new HashMap() ;
        map.put("personId" , personId) ;
        return (Integer) getSqlSession().selectOne(NAMESPACE+".selectPersonExist",map);

    }

	public Long findByIndustryCount(List<Long> industryId, Long typeId,Set<Long> regionIds, List<Integer> industryDirections,String keyword) {
		if (typeId == null && CollectionUtils.isEmpty(regionIds) && CollectionUtils.isEmpty(industryDirections)) {
			return findByIndustryCount(industryId);
		} else {
			Map map = new HashMap(4);
			map.put("typeId", typeId);
			
			if (!CollectionUtils.isEmpty(industryId)) {
				map.put("industryId", StringUtils.join(industryId, ","));
			}
			
			if (!CollectionUtils.isEmpty(regionIds)) {
				map.put("regionIds", StringUtils.join(regionIds, ","));
			}
			
			if (!CollectionUtils.isEmpty(industryDirections)) {
				map.put("industryDirections",StringUtils.join(industryDirections, ","));
			}
			
			Map<String, Long> countMap = getSqlSession().selectOne("person_push.findByIndustryCount", map);
			
			return countMap.get("cnt");
		}
	}

}
