package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

/**
 * @ClassName: DemandPeople.java
 * @author fxtx
 * @Date 2015年3月25日 上午11:40:14
 * @Description: 关联信息数据
 */
public class DemandASSOData implements Serializable {

	/**
	 *
	 */
	public static final long serialVersionUID = -790179278438715812L;
	public Integer type; // 2人脉，4组织，6知识，1事件
	public String id;
	public String picPath;//头像路径
	public String title;
	public String ownerid;// 拥有者id
	public String ownername;// 拥有者名称
	public String requirementtype; // 需求类型
	public String career;// 职业
	public String company;// 公司
	public OrganizationMini organizationMini;
	

	public Integer getType() {
		if (type==null){
			type = 0 ;
		}
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getId() {
		if (id==null){
			id="" ;
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicPath() {
		if (picPath==null){
			picPath = "" ;
		}
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getTitle() {
		if (title==null){
			title="" ;
		}
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOwnerid() {
		if (ownerid==null){
			ownerid="" ;
		}
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getOwnername() {
		if (ownername==null){
			ownername="" ;
		}
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public String getRequirementtype() {
		if (requirementtype==null){
			requirementtype = "" ;
		}
		return requirementtype;
	}

	public void setRequirementtype(String requirementtype) {
		this.requirementtype = requirementtype;
	}

	public String getCareer() {
		if (career==null){
			career="" ;
		}
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getCompany() {
		if (company==null){
			company = "" ;
		}
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		if (address==null){
			address="" ;
		}
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHy() {
		if (hy==null){
			hy="" ;
		}
		return hy;
	}

	public void setHy(String hy) {
		this.hy = hy;
	}

	public String getColumnpath() {
		if (columnpath==null){
			columnpath="" ;
		}
		return columnpath;
	}

	public void setColumnpath(String columnpath) {
		this.columnpath = columnpath;
	}

	public String getColumntype() {
		if (columntype==null){
			columntype="" ;
		}
		return columntype;
	}

	public void setColumntype(String columntype) {
		this.columntype = columntype;
	}

	public String getName() {
		if (name==null){
			name="" ;
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		if(tag==null){
			tag="" ;
		}
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String address;// 地址
	public String hy;// 行业
	public String columnpath;// 栏目路径
	public String columntype;// 栏目类型
	public String name;// 人名

	public String tag;// 标签关系

	public OrganizationMini getOrganizationMini() {
		return organizationMini;
	}

	public void setOrganizationMini(OrganizationMini organizationMini) {
		this.organizationMini = organizationMini;
	}

}
