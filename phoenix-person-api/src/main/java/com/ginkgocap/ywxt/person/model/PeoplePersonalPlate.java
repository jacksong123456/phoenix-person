package com.ginkgocap.ywxt.person.model;

import java.util.List;


/**
 * 人脉 自定义板块
 * @author wenbin
 *
 */
public class PeoplePersonalPlate extends BaseObject {

	private static final long serialVersionUID = -991356412866578107L;
	
	
	/**主键*/
	private Long id;
	/**自定义板块代码*/
	private String code;
	/**自定义板块名称*/
	private String name;
	/**内容*/
	private List<PeoplePersonalLine> personalLineList;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PeoplePersonalLine> getPersonalLineList() {
		return personalLineList;
	}
	public void setPersonalLineList(List<PeoplePersonalLine> personalLineList) {
		this.personalLineList = personalLineList;
	}
	
	
}
