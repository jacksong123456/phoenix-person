package com.ginkgocap.ywxt.person.service;

import com.ginkgocap.ywxt.person.model.CustomerMeet;

/**
 * 会面记录业务层
 * @author tanghuihuang
 *
 * 2015-1-6
 */
public interface PersonMeetService {

	/**
	 * 保存或者修改行业动态
	 * @return
	 */
	public CustomerMeet saveOrUpdate(CustomerMeet customerMeet);
	/**
	 * 按id删除会面记录
	 * @return
	 */
	public boolean deleteById(long id);
	
	/**
	 * 根据主键id得到对象
	 * @param id
	 * @return
	 */
	public CustomerMeet findOne(long id);
	
}
