package com.ginkgocap.ywxt.person.dao;

import java.util.List;
import java.util.Set;

import com.ginkgocap.ywxt.person.model.PersonTag;

/**
 * 用户标签Dao 接口
 * @author hdy
 * @date 2014-10-21
 */
public interface PersonTagDao {
    /**
     * 通过主键获得标签
     * @param id
     * @return
     */
	PersonTag selectByPrimarKey(Long id);
    /**
     *  根据用户id获得标签
     * @param userId
     * @return
     */
    List<PersonTag> selectByUserId(Long userId, Long startRow, int pageSize);
    /**
     * 根据用户id获得标签总数
     * @param userId
     * @return
     */
	 Long countByUserId(Long userId);
    /**
     * 根据用户id获得系统和用户的标签列表
     * @param userId
     * @param startRow
     * @param pageSize
     * @return
     */
    List<PersonTag> selectByUserIdAndSys(Long userId, Long startRow, int pageSize);
    /**
     * 根据用户id获得系统和用户的标签总数
     * @param userId
     * @return
     */
	Long countByUserIdAndSys(Long userId);
    /**
     * 插入标签
     * @param personTag
     * @return
     */
    PersonTag insert(PersonTag personTag);
    /**
     * 修改标签
     * @param personTag
     */
    void update(PersonTag personTag);
    /**
     * 删除标签
     * @param id
     */
    void delete(Long id);

    /**
     * 根据用户id和名称删除
     * @param userId
     * @param tagName
     */
    void deleteByTagNameAndUserId(Long userId, String tagName);
    /**
     * 根据用户id,标签名查找
     * @param userId
     * @param tagName
     * @return
     */
    List<PersonTag> selectByTagNameAndUserId(Long userId, String tagName);
    /**
     * 根据用户id和系统id,标签名查找
     * @param userId
     * @param tagName
     * @return
     */
    List<PersonTag> selectTagNameInUserIdAndSys(Long userId, String tagName);

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
    public List<PersonTag> selectPageByUserId(Long userId  ,  Long startRow, int pageSize) ;

    public Integer selectCountPageByUserId(Long userId) ;

    List<PersonTag> selectByIds(Set<Long> ids);

    public List<PersonTag> selectCountByIds(Set<Long> ids);
}
