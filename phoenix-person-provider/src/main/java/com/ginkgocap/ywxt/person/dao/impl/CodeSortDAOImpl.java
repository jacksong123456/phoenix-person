package com.ginkgocap.ywxt.person.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.ginkgocap.ywxt.person.dao.CodeSortDAO;
import com.ginkgocap.ywxt.person.model.CodeSort;
import com.ginkgocap.ywxt.person.model.IndustryDirection;

@Repository("codeSortDAO")
public class CodeSortDAOImpl extends SqlSessionDaoSupport implements ApplicationContextAware, CodeSortDAO {
	
	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public List<CodeSort> findCodeSortByType(String codeType) {
		return getSqlSession().selectList("tb_code_sort.selectCodeSort",codeType);
	}

	/**
	 * 根据id查出对象
	 * @param id
	 * @return
	 */
	public CodeSort get(Long id) {
		return getSqlSession().selectOne("tb_code_sort.get",id);
	}

	/**
	 * 根据sortid 查询子项
	 * @param sortId
	 * @return
	 */
	public List<CodeSort> selectBySortId(String sortId) {
		return getSqlSession().selectList("tb_code_sort.selectBySortId",sortId);
	}
	
	public List<CodeSort> selectCodeSortName(String sortId,String name) {
		Map<String,String> map = new HashMap<String,String>(2);
		map.put("type", sortId);
		map.put("keyowrd", name);
		return getSqlSession().selectList("tb_code_sort.selectCodeSortName",map);
	}

	public List<IndustryDirection> getIndustryDirection(int pid, int index,
			int size) {
		
		Map<String,Integer> map = new HashMap<String,Integer>(3);
		map.put("pid", pid);
		map.put("index", index);
		map.put("size", size);
		
		return getSqlSession().selectList("tb_industry_direction.selectIndustryDirectionByPage",map);
	}

	public long getIndustryDirectionCount(int pid) {
		Map<String,Integer> map = new HashMap<String,Integer>(1);
		map.put("pid", pid);
		return (Long)getSqlSession().selectOne("tb_industry_direction.selectIndustryDirectionCount", map);
	}

	public List<IndustryDirection> getIndustryDirectionBykeyword(String keyword, int size) {
		Map<String,Object> map = new HashMap<String,Object>(2);
		map.put("size", size);
		map.put("keyword", keyword);
		return getSqlSession().selectList("tb_industry_direction.selectIndustryDirectionByKeyword",map);
	}
}
