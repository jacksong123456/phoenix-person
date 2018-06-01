package com.ginkgocap.ywxt;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.ginkgocap.ywxt.person.dao.CodeSortDAO;
import com.ginkgocap.ywxt.person.dao.PersonCategoryRelationDAO;
import com.ginkgocap.ywxt.person.dao.PersonCountDao;
import com.ginkgocap.ywxt.person.dao.PersonSimpleDao;
import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonSimple;
import com.ginkgocap.ywxt.person.service.CodeSortService;
import com.ginkgocap.ywxt.person.service.PersonCategoryRelationService;
import com.ginkgocap.ywxt.person.service.PersonCategoryService;
import com.ginkgocap.ywxt.person.service.PersonCountService;
import com.ginkgocap.ywxt.person.service.PersonPermissionService;
import com.ginkgocap.ywxt.person.service.PersonService;
import com.ginkgocap.ywxt.person.service.PersonSimpleService;

public class AppTest extends TestBase {

	
	@Resource
	private CodeSortService codeSortService;
	@Resource
	private CodeSortDAO codeSortDao;
	@Resource
	private PersonCategoryService personCategoryService;
	@Resource
	private PersonSimpleDao personSimpleDao;
	@Resource
	private PersonCountService personCountService;
	@Resource
	private PersonCountDao personCountDao;
	@Resource
	private PersonService personService;
	@Resource
	private PersonCategoryRelationService personCategoryRelationService;
	@Resource
	private PersonSimpleService personSimpleService;
	@Resource
 	private PersonPermissionService personPermissionService;
	@Resource
	private PersonCategoryRelationDAO personCategoryRelationDAO;
	
	@Test
	public void personTest() {
		try {
			
			Map<String, Object> values = new HashMap<String, Object>(8);
			values.put("userId", 13315);
			values.put("typeId", 13315);
			values.put("regionId", 13315);
			values.put("firstIndustryDirectionId", 13315);
			
			List<PersonSimple> ps =personService.indexList(values);
			
			System.out.println(ps.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetPerson() {
		final long personId = 10000000004000000L;
		try {
			Person person = personService.get(personId);
			String json = JSON.toJSONString(person);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public List<PersonSimple> indexList(Map<String,Object> values);
//    public Long indexListCount(Map<String,Object> values);
	
}
