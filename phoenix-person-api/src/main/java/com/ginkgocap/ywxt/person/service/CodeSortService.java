package com.ginkgocap.ywxt.person.service;

import java.util.Map;
import java.util.Set;

/**
 * 编码接口
 * 
 * @author yangjie
 */
public interface CodeSortService {
	
	public final String career = "career";

	public final String classify = "classify";

	/**
	 * 根据 codeType 进行列表查询 （codeType为必传参数，并且只有两种情况。career-职业列表；classify-分类列表）
	 * 
	 * @param codeType
	 * @return
	 */
	@Deprecated
	public Map<String, Object> peopleCodeList(String codeType);
	
	public Map<String, Object> peopleCodeList(Map<String,Object> map);
	
	public Map<String, Object> peopleCodeListByName(String codeType,String keyword);
	/**
	 * 获取子项的id
	 *
	 * @param id
	 *            父级id
	 * @return
	 */
	public Set<Long> getChildIdsById(Long id);
	
	public Map<String,Object> getIndustryDirection(int pid,int index,int size);
	public Map<String,Object> getIndustryDirectionByKeyword(String keyword,int size);
}
