package com.ginkgocap.ywxt.person.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ginkgocap.ywxt.person.dao.CodeSortDAO;
import com.ginkgocap.ywxt.person.model.CodeSort;
import com.ginkgocap.ywxt.person.service.CodeSortService;

@Service("codeSortService")
public class CodeSortServiceImpl implements CodeSortService {

	@Autowired
	private CodeSortDAO codeSortDAO;

	/**
	 * 22．职业列表查询、分类列表查询
	 * 
	 * @param codeType
	 * @return
	 */
	@Deprecated
	public Map<String, Object> peopleCodeList(String codeType) {
		
		Map<String, Object> result = new HashMap<String, Object>();

		if (StringUtils.isBlank(codeType)) {
			result.put("flag", false);
			result.put("msg", "参数codeType不能为空");
			return result;
		}

		try {
			List<CodeSort> list = codeSortDAO.findCodeSortByType(codeType);
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("flag", false);
			result.put("msg", "查询失败");
			return result;
		}
		result.put("flag", true);
		result.put("msg", "查询成功");
		return result;

	}

	/**
	 * 获取子项的id
	 * 
	 * @param id
	 *            父级id
	 * @return
	 */
	public Set<Long> getChildIdsById(Long id) {
		if (id == null) {
			return null;
		}
		Set<Long> ids = new HashSet<Long>();
		ids.add(id) ;
		CodeSort codeSort = codeSortDAO.get(id);
		if (codeSort == null) {
			return null;
		}
		List<CodeSort> codeSorts = codeSortDAO.selectBySortId(codeSort
				.getSortId());
		if (codeSorts == null || codeSorts.size() == 0) {
			return null;
		}

		for (CodeSort codeSort1 : codeSorts) {
			ids.add(codeSort1.getId());
		}
		return ids;
	}

	public Map<String, Object> peopleCodeListByName(String codeType,
			String keyword) {
		
		Map<String, Object> result = new HashMap<String, Object>();

		if (StringUtils.isBlank(codeType)) {
			result.put("flag", false);
			result.put("msg", "参数codeType不能为空");
			return result;
		}

		try {
			List<CodeSort> list = codeSortDAO.selectCodeSortName(codeType, keyword);
			result.put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("flag", false);
			result.put("msg", "查询失败");
			return result;
		}
		result.put("flag", true);
		result.put("msg", "查询成功");
		return result;
	}

	public Map<String, Object> peopleCodeList(Map<String, Object> map) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String type = map.get("type").toString();
		
		List<CodeSort> list = null;
		String msg = "success";
		boolean flag = true;
		
		try {
			
			list = codeSortDAO.findCodeSortByType(type);
			
			if(type.equals(CodeSortService.classify)) {
				
				/**
				 * 脏数据处理机制
				 * 在数据库中还执行不了脚本（会导致主键重复），只好手工修改了。
				 * */
				for (int i = 0,temp =list.size(); i < temp; i++) { //FIXME
						CodeSort  cs = list.get(i);
						cs.setId(cs.getId() - 756);
				}
			}
			
			
		} catch (Exception e) {
			msg = e.getMessage();
			flag = false;
		
		}
		
		result.put("msg", msg);
		result.put("flag", flag);
		result.put("list", list);
		
		return result;
	}

	public Map<String, Object> getIndustryDirection(int pid, int index, int size) {
		Map<String,Object> result = new HashMap<String,Object>(2);
		result.put("industryDirections", codeSortDAO.getIndustryDirection(pid, index, size));
		result.put("total", codeSortDAO.getIndustryDirectionCount(pid));
		return result;
	}

	public Map<String, Object> getIndustryDirectionByKeyword(String keyword,int size) {
		Map<String,Object> result = new HashMap<String,Object>(1);
		result.put("industryDirections", codeSortDAO.getIndustryDirectionBykeyword(keyword, size));
		return result;
	}
}
