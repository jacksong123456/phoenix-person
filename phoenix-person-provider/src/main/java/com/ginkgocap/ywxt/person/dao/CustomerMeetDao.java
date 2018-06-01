/**
 * 
 */
package com.ginkgocap.ywxt.person.dao;

import java.util.List;

import com.ginkgocap.ywxt.person.model.CustomerMeet;


/**
 * 会面记录dao层接口
 * @author tanghuihuang
 *
 * 2015-1-6
 */
public interface CustomerMeetDao {
	/**
	 * 保存或者修改会面记录
	 * @return
	 */
	public CustomerMeet saveOrUpdate(CustomerMeet customerMeet);
	/**
	 * 按客户id删除会面记录
	 * @param id 客户id 主键id
	 * @return
	 */
	public boolean deleteById(long id);
	
	/**
	 * 根据主键id得到对象
	 * @param id
	 * @return
	 */
	public  CustomerMeet findOne(long id);
	/**
	 * 按客户id查询会面记录
	 * @param customerId
	 * @return
	 */
	public List< CustomerMeet> findByCustomerId(String customerId);
	public boolean deleteByPersonId(long id) ;
}
