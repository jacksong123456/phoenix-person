package com.ginkgocap.ywxt.person.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ginkgocap.ywxt.person.model.PersonCategoryRelation;

public interface PersonCategoryRelationService {

	/**
	 * 未分组id
	 */
	public static final Long NO_GROUP_CATID = -1L;

	Set<Long> selectUser(Integer personType, Long userId);
	
	Set<Long> selectPersonUser(Long userId);
	
	Set<Long> selectCollectUser(Long userId);
	
	Set<Long> selectAllPerson(Long userId);

	List<Map<String, Object>> selectPersonCategories(Long userId, Long personId, Byte personType);

	/**
	 * 类型
	 */
	public static enum Ctype {
		// 创建
		create(1);
		public Integer code;

		Ctype(Integer code) {
			this.code = code;
		}
	}

	/**
	 * 8．收藏人脉(必须包含用户ID、人脉ID、类型此处默认是2（人脉）)
	 * 插入mysql中的目录关系表（创建类型字段为2，表示我收藏的人脉），插入目录id字段设置为-1中。 要先校验是否已收藏，避免重复收藏。
	 * 
	 * @param param
	 *            (personId、userId)
	 * @return "success":true/false
	 */
	public Map<String, Object> collectPeople(Map<String, Object> param);

	/**
	 * 9.取消收藏（必须包含用户ID\人脉ID） 删除mysql中的目录关系表中的对应记录（根据人脉ID、人的类型、创建类型、所有者）
	 * 
	 * @param param
	 *            (personId、userId)
	 * @return "success":true/false
	 */
	public Map<String, Object> cancelCollectPeople(Map<String, Object> param);

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
	public int deleteCollec(Long personId, Integer personType, Long userId);

	/**
	 * 4．人脉详情
	 * 
	 * @param userId
	 *            用户ID
	 * @param personId
	 *            人脉id
	 * @param personType
	 *            用户类型 1 用户 2人脉
	 * @return
	 */
	public List<PersonCategoryRelation> selectByUidAndPerAndPerTy(Long userId,
			Long personId, Byte personType) throws Exception;

	public Long selectUserCount(Long userId);
}
