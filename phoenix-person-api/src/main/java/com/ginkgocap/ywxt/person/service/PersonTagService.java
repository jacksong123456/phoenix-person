package com.ginkgocap.ywxt.person.service;

import java.util.List;
import java.util.Set;

import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonTag;

public interface PersonTagService {
	/**
	 * 通过主键获得标签
	 * 
	 * @param id
	 * @return
	 */
	public PersonTag selectById(Long id);


    /**
     * 插入标签
     * @param personTag
     * @return
     */
	public PersonTag insert(PersonTag personTag);

    /**
     * 插入多个标签
     * @param personTagList
     * @return
     */
	public List<PersonTag> insert(List<PersonTag> personTagList);

    /**
     * 修改标签
     * @param personTag
     */
	public void update(PersonTag personTag);

	/**
	 * 删除标签
	 * 
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 根据标签名和用户Id删除
	 * 
	 * @param userId
	 * @param tagName
	 */
	public void deleteByTagNameAndUserId(Long userId, String tagName);

	/**
	 * 根据名称和用户id查询
	 * 
	 * @param userId
	 *            用户id
	 * @param tagName
	 *            标签名称
	 * @return
	 */
	public List<PersonTag> selectByNameAndUserId(Long userId, String tagName);

    /**
     * 根据用户id查询分页
     *
     * @param userId 用户id
     * @param startRow
     * @param pageSize
     * @return
     */
	public List<PersonTag> selectPageByUserId(Long userId, Long startRow, int pageSize);
	
	/**
	 * 根据用户id查询总数
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	public Long countByUserId(Long userId);

	/**
	 * 根据用户id获得系统和用户的标签列表分页
	 * 
	 * @param userId
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<PersonTag> selectPageByUserIdAndSys(Long userId, Long startRow, int pageSize);

    /**
     * 根据用户id获得系统标签总数
     * @return
     */
	public Long countBySys();

    /**
     * 获得系统标签列表分页
     * @param startRow
     * @param pageSize
     * @return
     */
	public List<PersonTag> selectPageBySys(Long startRow, int pageSize);
	
	/**
	 * 根据用户id获得系统和用户的标签总数
	 * @param userId
	 */
	public Long countByUserIdAndSys(Long userId);

    /**
     * 在用户标签和系统标签中，根据标签名查询
     * @param userId
     * @param tagName
     * @return
     */
	public List<PersonTag> selectTagNameInUserIdAndSys(Long userId, String tagName);
	/**
	 * 搜索前缀
	 * @param userId
	 * @param keyword
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<PersonTag> searchPrefixPageByUserIdAndSys(Long userId, String[] keyword, Long startRow, int pageSize);
	/**
	 * 搜索前后包含
	 * @param userId
	 * @param keyword
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<PersonTag> searchContainPageByUserIdAndSys(Long userId, String[] keyword, Long startRow, int pageSize);

	public List<PersonTag> selectByUserId(Long userId  ,  Long startRow, int pageSize) ;

	public Integer selectCountPageByUserId(Long userId) ;

    /**
     * 根据标签ID查询当前用户的人脉列表
     *
     * @param userId
     * @param tagId
     * @param startRow
     * @param pageSize
     * @return
     */
    public List<Person> findPersonByTag(Long userId, Long tagId, Long startRow, int pageSize);

    /**
     * 根据标签ID查询当前用户的人脉总数
     *
     * @param userId
     * @param tagId
     * @return
     */
    public Integer selectPersonCountByTag(Long userId, Long tagId);

	List<PersonTag> selectByIds(Set<Long> ids) ;
	public List<PersonTag> selectCountByIds(Set<Long> ids);

}
