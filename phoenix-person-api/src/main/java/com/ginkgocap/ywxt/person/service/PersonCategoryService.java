package com.ginkgocap.ywxt.person.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ginkgocap.ywxt.person.model.PersonCategory;

public interface PersonCategoryService {

	/**
	 * 5．新增或者修改目录 case update: 修改时必须传入id以及目录名称categoryname csse insert:
	 * 新增时-新增非顶级目录时pid有值，必须传入cateRgoryname目录名称
	 * 
	 * @param personCategory
	 * @return "success":true/false
	 */
	public Map<String, Object> saveOrUpdateCategory(
			PersonCategory personCategory);

	/**
	 * 6．删除目录 删除目录 先将目录关系表中的该目录及其子目录下的人脉、用户更新到该目录的上级目录下； 然后删除目录表中该目录的记录。
	 * 
	 * @param personCategory
	 *            对象
	 * @return "success":true/false
	 */
	public Map<String, Object> removeCategory(PersonCategory personCategory);

	/**
	 * 7．查询目录 ：userId为必传项， id以及pid为选传项,查询当前用户的目录记录,返回子集
	 * 
	 * @param id
	 *            目录ID
	 * @param pid
	 *            上级目录ID
	 * @param userId
	 *            用户ID
	 * @return "categoryname": "目录名称", "id": "","list":[下级列表]
	 */
	public Map<String, Object> findCategory(Long id, Long pid, Long userId,String keyword);

	List<Map<String, Object>> selectCategories(Collection<Long> categoryIds);
	
	//--------------------------web---------------------------------
	public List<PersonCategory> findTierCategory(Long id, Long pid, Long userId,String keyword,int pageno, int pagesize);
	
	public Long countTierCategory(Long id, Long pid, Long userId,String keyword);
	
	/**
	 * 返回当前目录id的目录
	 * @param id
	 * @return
	 */
	public PersonCategory findCategoryNameById(Long id);
	
	/**
	 * 返回当前用户的所有资源数量
	 * @param userId
	 * @return
	 */
	public long countPerson(long userId);

	/**
	 * 返回目录的等级
	 * @param id
	 * @param pid
	 * @return
	 */
	public int countLevels(Long id, Long pid);
}
