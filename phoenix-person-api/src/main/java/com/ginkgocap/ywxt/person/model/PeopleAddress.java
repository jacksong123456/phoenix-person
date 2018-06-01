package com.ginkgocap.ywxt.person.model;

/**
 * 地址
 * @author wenbin
 *
 */
public class PeopleAddress extends BaseObject {

	private static final long serialVersionUID = -3376130394695782352L;
	
	/**主键*/
	private Long id;
	/**通讯类型： 1-联系方式，2-投资需求，3-融资需求，4-专家需求，5-专家身份*/
	private Integer parentType;
	/**联系方式类型：1-住宅地址，2-商务地址，N-自定义， 非联系方式时为空*/
	private PeopleSelectTag typeTag;
	/**地区类型：0-国内，1-国外*/
	private Integer areaType;
//	/**国家*/
//	private Integer country;
//	/**省*/
//	private Integer state;
//	/**城市*/
//	private Integer city;
//	/**县区*/
//	private Integer county;	
	///**国家*/
//	private String countryName;
	/**省/洲*/
	private String stateName;
	/**城市/国*/
	private String cityName;
	/**县区*/
	private String countyName;
	/**地址*/
	private String address;
	/**邮编*/
	private String postalCode;
	
	/**************** 人脉详情需要显示字段start*******************/
	/**地区类型：1-国内，2-国外*/
	private String areaTypeName;
	/**************** 人脉详情需要显示字段end*******************/
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getParentType() {
		return parentType;
	}
	public void setParentType(Integer parentType) {
		this.parentType = parentType;
	}
	public PeopleSelectTag getTypeTag() {
		return typeTag;
	}
	public void setTypeTag(PeopleSelectTag typeTag) {
		this.typeTag = typeTag;
	}
	public Integer getAreaType() {
		return areaType;
	}
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getAreaTypeName() {
		return areaTypeName;
	}
	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
	
}
