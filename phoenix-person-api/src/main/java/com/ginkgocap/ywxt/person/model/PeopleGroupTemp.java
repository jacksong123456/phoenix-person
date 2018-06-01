package com.ginkgocap.ywxt.person.model;


/**
 * 人脉分组
 * @author wenbin
 *
 */
public class PeopleGroupTemp extends BaseObject {

	private static final long serialVersionUID = -1702145300615502322L;
	
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
