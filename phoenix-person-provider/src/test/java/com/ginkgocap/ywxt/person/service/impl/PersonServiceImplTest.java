package com.ginkgocap.ywxt.person.service.impl;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;

import com.ginkgocap.ywxt.TestBase;
import com.ginkgocap.ywxt.person.model.Person;
import com.ginkgocap.ywxt.person.model.PersonName;
import com.ginkgocap.ywxt.person.service.PersonService;

public class PersonServiceImplTest extends TestBase {

	@Resource
	private PersonService personService;

	/**
	 * 人脉保存时的MQ测试
	 * 
	 * @author 周仕奇
	 * @throws Exception
	 * @date 2015年12月10日 下午2:39:46
	 */
	@Test
	public void saveForMQTest() throws Exception {

		long personIdBegin = 38000L;

		for (int i = 0; i < 1000; i++) {

			Person person = personService.get(personIdBegin + i);

			// 为了标明该人脉是测试MQ的，则在personName.lastname中添加“MQ测试”
			if (person != null && person.getPeopleNameList() != null && person.getPeopleNameList().size() > 0) {
				PersonName personName = person.getPeopleNameList().get(0);
				personName.setLastname(personName.getFirstname() + "MQ测试");
				Person returnPerson = personService.save(person);
				
				assertThat(returnPerson, notNullValue());
				
				assertThat(returnPerson.getPeopleNameList().get(0).getLastname(), is(personName.getFirstname() + "MQ测试"));

			}
		
		}

	}

	@Test
	public void initOldDataSearchTest(){
		personService.initOldDataSearch(100000L, 360000L);
	}
	
}