package com.ginkgocap.ywxt.person.model;


/**
 * 人脉 select自定义标签
 * @author wenbin
 *
 */
public class PeopleSelectTag extends BaseObject {

	private static final long serialVersionUID = 3507293970531998126L;
	
	/**主键*/
	private Long id;
	/**类型编码*/
	private String code;
	/**内容*/
	private String name;
	/**类型，0-默认，1-自定义*/
	private Integer type;
	
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
