package com.ginkgocap.ywxt.person.model;


/**
 * 人脉关联客户
 * @author wenbin
 *
 */
public class PeopleCustomer extends BaseObject {

	private static final long serialVersionUID = 626789426972267039L;
	
	/**主键*/
	private Long id;
	/**名称*/
	private String name;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
