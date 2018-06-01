package com.ginkgocap.ywxt.person.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ginkgocap.ywxt.person.model.PersonCategory;

public interface PersonCategoryDAO {

	public void createPersonCategory(PersonCategory personCategory);

	public void deletePersonCategory(String ids);

	public void updatePersonCategory(PersonCategory personCategory);

	public Long selectParentCategroy(Long id);

	public PersonCategory findById(Long id);

	public PersonCategory findCategory(PersonCategory personCategory);

	public String findCategoryIds(PersonCategory personCategory);

	public List<PersonCategory> findCategoryByEntity(Map<String,Object> map);

	/**
	 * 7查询目录
	 * 
	 * @param parameter
	 * @return
	 */
	public List<PersonCategory> selectCategory(Map<String, Object> parameter);

	public List<PersonCategory> selectCategoryBySortId(String sortId);

	public List<PersonCategory> selectCategoryByPid(Long pid);

	public Long findLastId();

	List<Map<String, Object>> selectCategories(Collection<Long> categoryIds);
	
	public List<PersonCategory> selectCategoryForPage(Map<String, Object> parameter);
	
	public Long countCategoryForPage(Map<String, Object> parameter);
	
	public Long countCategoryByCategoryName(Map<String, Object> parameter);
}
