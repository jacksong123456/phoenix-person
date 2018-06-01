package com.ginkgocap.ywxt.person.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ginkgocap.ywxt.person.dao.PersonCategoryDAO;
import com.ginkgocap.ywxt.person.dao.PersonCategoryRelationDAO;
import com.ginkgocap.ywxt.person.model.PersonCategory;
import com.ginkgocap.ywxt.person.service.PersonCategoryService;

@Service("personCategoryService")
public class PersonCategoryServiceImpl implements PersonCategoryService {

	@Autowired
	private PersonCategoryDAO personCategoryDAO;
	@Autowired
	private PersonCategoryRelationDAO personCategoryRelationDAO;

	private boolean checkCategoryName(long pid, String categoryName, long userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("keyword", categoryName);
		result.put("userId", userId);
		result.put("pid", pid);
		return personCategoryDAO.countCategoryByCategoryName(result) > 0 ? true : false;
	}

	private Map<String, Object> updateCategory(Map<String, Object> result, PersonCategory personCategory) {

		// 如果同级下面有相同的名称的目录的话，则不予更新
		if (checkCategoryName(personCategory.getPid(), personCategory.getName(), personCategory.getUserId())) {
			result.put("flag", false);
			result.put("msg", "修改失败，目录名称重复");
			return result;
		}

		if (personCategory.getName().equals("未分组")) {
			result.put("flag", false);
			result.put("msg", "修改失败，不能修改未分组目录");
			return result;
		}

		// 更新/新增时-新增非顶级目录时pid有值，必须传入categoryname目录名称
		String resultSortId = "";
		String parentSortId = "";// 上级目录的sortId
		if (personCategory.getPid() != null && personCategory.getPid() != 0) {
			// 有上级目录
			PersonCategory parentPersonCategory = personCategoryDAO.findById(personCategory.getPid());
			if (parentPersonCategory != null) {
				parentSortId = StringUtils.trimToEmpty(parentPersonCategory.getSortId());
			} else {
				result.put("flag", false);
				result.put("msg", "上级目录不存在");
				return result;
			}
		} else {
			personCategory.setPid(0L);// 是顶级目录，所以上级目录id设置为0
		}

		String sortId = "";// 同级目录的最大的sortId
		{
			// 查询同级目录的最大的sortId
			PersonCategory sort = personCategoryDAO.findCategory(personCategory);// 通过pid查询用户的上级目录是否存在
			if (sort != null) {
				sortId = StringUtils.trimToEmpty(sort.getSortId());
			}
		}
		if (StringUtils.isBlank(sortId)) {// 没有同级目录
			resultSortId = parentSortId + "01";
		} else {// 有同级目录
			sortId = sortId.substring(sortId.length() - 2);
			sortId = String.valueOf(Integer.valueOf(sortId) + 1);
			sortId = StringUtils.leftPad(sortId, 2, '0');

			resultSortId = parentSortId + sortId;
		}
		personCategory.setSortId(resultSortId);
		
		// 修改时必须传入id以及目录名称categoryname
		PersonCategory checkCategory = personCategoryDAO.findById(personCategory.getId());
		if (checkCategory != null) {
			personCategoryDAO.updatePersonCategory(personCategory);
			
			//迭代子目录 修改sortid
			List<PersonCategory>  t_list = personCategoryDAO.selectCategoryByPid(personCategory.getId());
			downLevel2(t_list,StringUtils.trimToEmpty(personCategory.getSortId()));
			
			result.put("flag", true);
			result.put("msg", "修改成功");
		} else {
			result.put("flag", false);
			result.put("msg", "修改失败，目录不存在");
		}
		return result;
	}
	
	private void downLevel2(List<PersonCategory>  list,String pSortId){
		for (PersonCategory personCategory : list) {
			String parentSortId = StringUtils.trimToEmpty(pSortId);
			String subSortid = StringUtils.trimToEmpty(personCategory.getSortId());
			personCategory.setSortId(parentSortId+subSortid.substring(subSortid.length() - 2));
			personCategoryDAO.updatePersonCategory(personCategory);
			List<PersonCategory>  t_list = personCategoryDAO.selectCategoryByPid(personCategory.getId());
			if(t_list != null){
				downLevel2(t_list,StringUtils.trimToEmpty(personCategory.getSortId()));
			}
		}
		
	}

	private Map<String, Object> createCategory(Map<String, Object> result, PersonCategory personCategory) {

		// 如果同级下面有相同的名称的目录的话，则不予更新
		if (checkCategoryName(personCategory.getPid(), personCategory.getName(), personCategory.getUserId())) {
			result.put("flag", false);
			result.put("msg", "创建失败，目录名称重复");
			return result;
		}

		if (personCategory.getName().equals("未分组")) {
			result.put("flag", false);
			result.put("msg", "创建失败，不能创建未分组目录");
			return result;
		}

		// 新增时-新增非顶级目录时pid有值，必须传入categoryname目录名称
		String resultSortId = "";
		String parentSortId = "";// 上级目录的sortId
		if (personCategory.getPid() != null && personCategory.getPid() != 0) {
			// 有上级目录
			PersonCategory parentPersonCategory = personCategoryDAO.findById(personCategory.getPid());
			if (parentPersonCategory != null) {
				parentSortId = StringUtils.trimToEmpty(parentPersonCategory.getSortId());
			} else {
				result.put("flag", false);
				result.put("msg", "上级目录不存在");
				return result;
			}
		} else {
			personCategory.setPid(0L);// 是顶级目录，所以上级目录id设置为0
		}

		String sortId = "";// 同级目录的最大的sortId
		{
			// 查询同级目录的最大的sortId
			PersonCategory sort = personCategoryDAO.findCategory(personCategory);// 通过pid查询用户的上级目录是否存在
			if (sort != null) {
				sortId = StringUtils.trimToEmpty(sort.getSortId());
			}
		}
		if (StringUtils.isBlank(sortId)) {// 没有同级目录
			resultSortId = parentSortId + "01";
		} else {// 有同级目录
			sortId = sortId.substring(sortId.length() - 2);
			sortId = String.valueOf(Integer.valueOf(sortId) + 1);
			sortId = StringUtils.leftPad(sortId, 2, '0');

			resultSortId = parentSortId + sortId;
		}
		personCategory.setSortId(resultSortId);
		personCategoryDAO.createPersonCategory(personCategory);
		long id = personCategoryDAO.findLastId();
		result.put("id", id);
		result.put("flag", true);
		result.put("msg", " 新增成功 ");
		return result;
	}

	/**
	 * 5．新增或者修改目录
	 */
	public Map<String, Object> saveOrUpdateCategory(PersonCategory personCategory) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (personCategory.getId() != null) {
				return updateCategory(result, personCategory);
				// 如果是修改目录信息，则执行到此就结束了
			}

			return createCategory(result, personCategory);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("flag", false);
			result.put("msg", " 查询失败 ");
		}
		return result;
	}

	/**
	 * 删除目录 先将目录关系表中的该目录及其子目录下的人脉、用户更新到该目录的上级目录下； 然后删除目录表中该目录的记录。
	 */
	@Transactional(rollbackFor = { RuntimeException.class })
	public Map<String, Object> removeCategory(PersonCategory personCategory) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Long categoryId = personCategory.getId();
			if (categoryId == null || categoryId < 1) {
				result.put("flag", false);
				result.put("msg", " 删除失败");
				return result;
			}
			personCategory = personCategoryDAO.findById(personCategory.getId());
			if (personCategory == null) {
				result.put("flag", false);
				result.put("msg", " 删除失败，目录不存在");
			} else {
				// 根据用户ID,以及sortId，查询所有下级目录的id
				String ids = personCategoryDAO.findCategoryIds(personCategory);

				Map<String, Object> param = new HashMap<String, Object>();
				Long pid = personCategory.getPid();
				if (pid.equals(0L)) {// 上级目录id为0，说明被删除的目录是顶级目录
					param.put("categoryId", -1);// -1表示未分组
				} else {
					param.put("categoryId", personCategory.getPid());
				}
				param.put("categoryIds", ids);
				personCategoryRelationDAO.updatePersonCategoryRelation(param);
				personCategoryDAO.deletePersonCategory(ids);
				result.put("flag", true);
				result.put("msg", " 删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("flag", false);
			result.put("msg", " 删除失败");
		}
		return result;
	}

	/**
	 * 查询目录
	 * 
	 * @param id
	 * @param pid
	 * @param userId
	 * @return
	 */
	public Map<String, Object> findCategory(Long id, Long pid, Long userId, String keyword) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (id != null || pid != null) {
				// PersonCategory category = new PersonCategory();
				// category.setId(id);
				// category.setPid(pid);
				// category.setUserId(userId);
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("id", id);
				param.put("pid", pid);
				param.put("userId", userId);
				List<PersonCategory> list = personCategoryDAO.findCategoryByEntity(param);
				if (list == null || list.size() <= 0) {
					result.put("flag", false);
					result.put("msg", "该目录不存在");
					return result;
				}
				String sortId = list.get(0).getSortId();
				List<PersonCategory> personCategories = personCategoryDAO.selectCategoryBySortId(sortId);
				if(personCategories!=null){
					int personCategorySize = personCategories.size();
					if (personCategorySize > 0) {
						for (int i = 0; i < personCategorySize; i++) {
							PersonCategory pc = personCategories.get(i);
							pc.setCount(personCategoryRelationDAO.countPersonByCategoryId(pc.getId(), pc.getUserId()));
						}
					}
				}
				result.put("flag", true);
				result.put("msg", "查询成功");
				result.put("list", personCategories);
			} else {
				// PersonCategory category = new PersonCategory();
				// category.setUserId(userId);
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", userId);
				param.put("keyword", keyword);
				List<PersonCategory> list = personCategoryDAO.findCategoryByEntity(param);
				// 新增count字段
				if (list != null) {
					int personCategorySize = list.size();
					if (personCategorySize > 0) {
						for (int i = 0; i < personCategorySize; i++) {
							PersonCategory pc = list.get(i);
							pc.setCount(personCategoryRelationDAO.countPersonByCategoryId(pc.getId(), pc.getUserId()));
						}
					}

				}

				// if (list == null || list.size() <= 0) {
				// result.put("flag", false);
				// result.put("msg", "数据库中不存在 用户ID=" + userId + " 的数据");
				// return result;
				// }
				result.put("flag", true);
				result.put("msg", "查询成功");
				result.put("list", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("flag", false);
			result.put("msg", "查询失败");
			return result;
		}
		return result;
	}

	public List<Map<String, Object>> selectCategories(Collection<Long> categoryIds) {
		return personCategoryDAO.selectCategories(categoryIds);
	}

	public List<PersonCategory> findTierCategory(Long id, Long pid, Long userId, String keyword, int pageno,
			int pagesize) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("pid", pid);
		param.put("userId", userId);
		param.put("keyword", keyword);
		param.put("limitStart", pageno);
		param.put("limitEnd", pagesize);

		// 评定一下下级是否存在子目录
		List<PersonCategory> personCategorys = personCategoryDAO.selectCategoryForPage(param);

		if (personCategorys != null) {

			int personCategorySize = personCategorys.size();

			if (personCategorySize > 0) {

				for (int i = 0; i < personCategorySize; i++) {

					PersonCategory pc = personCategorys.get(i);

					pc.setCount(personCategoryRelationDAO.countPersonByCategoryId(pc.getId(), pc.getUserId()));

					pc.setIsParent(countTierCategory(null, pc.getId(), null, null) > 0 ? true : false);

				}

			}

		}

		return personCategorys;
	}

	public Long countTierCategory(Long id, Long pid, Long userId, String keyword) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("pid", pid);
		param.put("userId", userId);
		param.put("keyword", keyword);

		return personCategoryDAO.countCategoryForPage(param);
	}

	public PersonCategory findCategoryNameById(Long id) {
		return personCategoryDAO.findById(id);
	}

	//用户目录下的总资源数
	public long countPerson(long userId) {

		return personCategoryRelationDAO.countPersonByCategoryId(null, userId);
	}

	//获取目录等级
	@Override
	public int countLevels(Long id, Long pid) {
		int down,up;
		List<PersonCategory>  list = personCategoryDAO.selectCategoryByPid(id);
		
		Map<String,Integer> map = new HashMap();
		downLevel(list,1,map);
		down = map.get("count");
		up = uplevel(pid);
		return up+down;
	}
	
	
	//目录向下递归
	private void downLevel(List<PersonCategory>  list,int count,Map<String,Integer> map){
			map.put("count", count);
			for (PersonCategory personCategory : list) {
				List<PersonCategory>  t_list = personCategoryDAO.selectCategoryByPid(personCategory.getId());
				if(t_list != null){
					count=count+1;
					downLevel(t_list,count,map);
				}
			}
	}
		
	//目录向上
	private int uplevel(Long pid){
		if(pid != null && pid != 0){
			PersonCategory temp_person = personCategoryDAO.findById(pid);
			if(temp_person != null){
				int weishu = temp_person.getSortId().length();
				return weishu/2;
			}
		}
		return 0;
	}
	
}
