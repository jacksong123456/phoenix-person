/**
 * 
 */
package com.ginkgocap.ywxt.person.dao;

import java.util.List;

import com.ginkgocap.ywxt.person.model.CustomerMeetingDetail;


/**
 * 会面记录dao层接口
 * @author tanghuihuang
 *
 * 2015-1-6
 */
public interface CustomerMeetingDetailDao {
	/**
	 * 保存或者修改会面记录
	 * @return
	 */
	public CustomerMeetingDetail saveOrUpdate(CustomerMeetingDetail customerMeetingDetail);
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
	public  CustomerMeetingDetail findOne(long id);
	/**
	 * 按客户id查询会面记录
	 * @param customerId
	 * @return
	 */
	public List< CustomerMeetingDetail> findByCustomerId(String customerId);
	
	/**
	 * 根据提醒类型来查找会议
	 * @param repeadType 0:不提醒 1:每天提醒 2:每周提醒 3:每月提醒  4:每年提醒 -1:全部
	 * @return
	 * @author wfl
	 */
	public List<CustomerMeetingDetail> findByParams(String repeadType);
	
}
