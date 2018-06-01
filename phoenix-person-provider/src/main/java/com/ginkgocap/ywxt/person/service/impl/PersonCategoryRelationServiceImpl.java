package com.ginkgocap.ywxt.person.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.dao.PersonCategoryRelationDAO;
import com.ginkgocap.ywxt.person.model.PersonCategoryRelation;
import com.ginkgocap.ywxt.person.service.PersonCategoryRelationService;
import com.ginkgocap.ywxt.person.service.PersonService;

@Service("personCategoryRelationService")
public class PersonCategoryRelationServiceImpl implements
		PersonCategoryRelationService {

	private static final int CTYPE_COLLEC = 2;
	
	private static final int PERSON_TYPE = 2;

	@Autowired
	private PersonCategoryRelationDAO personCategoryRelationDAO;

	/**
	 * 8．收藏人脉
	 * 
	 * @param param
	 *            param 必须包含用户ID\人脉ID\类型此处默认是2（人脉）
	 * @return
	 */
	public Map<String, Object> collectPeople(Map<String, Object> param) {
		
		long personId = (Long) param.get("personId");
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			
			if (personCategoryRelationDAO.selectCountByPersonIdForUserId(param) > 0) { //重构替换原有写法
				result.put("flag", false);
				result.put("msg", "您已收藏过 ID = " + personId+ " 的人脉");
				return result;
			}

			PersonCategoryRelation favorite = new PersonCategoryRelation();
			favorite.setPersonType(PERSON_TYPE);
			favorite.setCtype(CTYPE_COLLEC); // 说明：1-我创建的人脉;2-我收藏的人脉;3-我保存的人脉;5-我的好友
			favorite.setPersonId(personId);
			favorite.setCategoryId(-1L); //收藏的人脉会放在个人用户的未分组里面
			favorite.setUserId((Long) param.get("userId"));

			personCategoryRelationDAO.createPersonCategoryRelation(favorite);

			result.put("flag", true);
			result.put("msg", "收藏成功");
		} catch (Exception e) {
			result.put("flag", false);
			result.put("msg", "收藏失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 取消收藏
	 * 
	 * @param param
	 *            param 必须包含用户ID\人脉ID
	 * @return
	 */
	public Map<String, Object> cancelCollectPeople(Map<String, Object> param) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			
			if (personCategoryRelationDAO.selectCountByPersonIdForUserId(param) == 0) {
				result.put("flag", false);
				result.put("msg", "您尚未收藏过 ID = " + param.get("personId") + " 的人脉， 无需取消收藏");
				return result;
			}

			PersonCategoryRelation favorite = new PersonCategoryRelation();
			favorite.setPersonId((Long) param.get("personId"));
			favorite.setUserId((Long) param.get("userId"));

			personCategoryRelationDAO.cancelFavorite(favorite);

			result.put("flag", true);
			result.put("msg", "取消成功");
		} catch (Exception e) {
			result.put("flag", false);
			result.put("msg", "取消失败");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除收藏
	 * 
	 * @param personId
	 *            人脉id
	 * @param personType
	 *            用户类型
	 * @param userId
	 *            所属用户id
	 * @return
	 */
	public int deleteCollec(Long personId, Integer personType, Long userId) {
		return personCategoryRelationDAO.deleteByPerIdAndperTyAndCtyAndUid(
				personId, personType, CTYPE_COLLEC, userId);
	}

	/**
	 * 人脉详情
	 * 
	 * @param userId
	 * @param personId
	 *            人脉id
	 * @param personType
	 *            用户类型 1 用户 2人脉
	 * @return
	 */
	public List<PersonCategoryRelation> selectByUidAndPerAndPerTy(Long userId,
			Long personId, Byte personType) throws Exception {
		if (userId == null || personId == null || personType == null) {
			throw new Exception("数据错误");
		}
		return personCategoryRelationDAO.selectByUidAndPerAndPerTy(userId,
				personId, personType);
	}

	public Long selectUserCount( Long userId){
		return personCategoryRelationDAO.selectUserCount(PersonService.Persontype.person.code.intValue() , userId) ;
	}

	public Set<Long> selectUser(Integer personType, Long userId) {
		return  personCategoryRelationDAO.selectUser(personType, userId);
	}
	public Set<Long> selectCollectUser(Long userId) {
		return  personCategoryRelationDAO.selectCollectUser(userId);
	}
	public Set<Long> selectPersonUser(Long userId) {
		return  personCategoryRelationDAO.selectPersonUser(userId);
	}
	public List<Map<String, Object>> selectPersonCategories(Long userId, Long personId, Byte personType) {
		return personCategoryRelationDAO.selectPersonCategories(userId, personId, personType);
	}

	@Override
	public Set<Long> selectAllPerson(Long userId) {
		// TODO Auto-generated method stub
	    return  personCategoryRelationDAO.selectAllPerson(userId);
	}
}
