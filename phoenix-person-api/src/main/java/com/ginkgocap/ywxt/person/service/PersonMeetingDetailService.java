package com.ginkgocap.ywxt.person.service;

import java.util.Map;

import com.ginkgocap.ywxt.person.model.CustomerMeetingDetail;

/**
 * 会面记录业务层
 * @author tanghuihuang
 *
 * 2015-1-6
 */
public interface PersonMeetingDetailService {

	/**
	 * 保存或者修改会面记录
	 * @return
	 */
	public CustomerMeetingDetail saveOrUpdate(CustomerMeetingDetail customerMeetingDetail);
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
	public CustomerMeetingDetail findOne(long id);
	/**
	 * 根据提醒类型来查找会议
	 * @param repeadType 0:不提醒 1:每天提醒 2:每周提醒 3:每月提醒  4:每年提醒 -1:全部 -2:全体提醒
	 * @return
	 * @author wfl
	 */
	public Map<String, Object> findByParams(String repeadType, int currentPage, int pageSize);
	
	/**
	 * 根据客户id查找会面情况列表
	 * @param customerId
	 * @return
	 * @author tanghh
	 */
	public Map<String, Object> findByPersonId(long customerId, int currentPage, int pageSize);
	
}
