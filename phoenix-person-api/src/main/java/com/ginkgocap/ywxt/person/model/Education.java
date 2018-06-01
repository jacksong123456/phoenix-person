package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Education implements Serializable {
	
	private static final long serialVersionUID = -550686882764757310L;
	
	/**
	 * 学校
	 */
	private String school;

	/**学院*/
	private String college;
	/**
	 * 专业
	 */
	private String specialty;
	/**
	 * 学历
	 */
	private String educationalBackgroundType;
	/**学位*/
	private String degreeType;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 海外学习经历（0无1有）
	 */
	private Integer studyAbroadType;
	/**
	 * 同学关系
	 */
	private List<Basic> studentsRelationshipList;
	/**
	 * 外语能力
	 */
	private List<ForeignLanguage> foreignLanguageList;
	/**
	 * 教育自定义项
	 */
	private List<Basic> custom;
	
	//开始时间
	private String stime;
	
	//结束时间
	private String etime;
	
	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSchool() {
		if (school==null){
			school="" ;
		}
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getSpecialty() {
		if (specialty==null){
			specialty = "" ;
		}
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getEducationalBackgroundType() {
		return educationalBackgroundType;
	}

	public void setEducationalBackgroundType(String educationalBackgroundType) {
		this.educationalBackgroundType = educationalBackgroundType;
	}

	public String getDesc() {
		if (desc==null){
			desc="" ;
		}
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getStudyAbroadType() {
		if (studyAbroadType==null){
			studyAbroadType=0 ;
		}
		return studyAbroadType;
	}

	public void setStudyAbroadType(Integer studyAbroadType) {
		this.studyAbroadType = studyAbroadType;
	}

	public List<Basic> getStudentsRelationshipList() {
		if (studentsRelationshipList==null){
			studentsRelationshipList = new ArrayList<Basic>() ;
		}
		return studentsRelationshipList;
	}

	public void setStudentsRelationshipList(List<Basic> studentsRelationshipList) {
		this.studentsRelationshipList = studentsRelationshipList;
	}

	public List<ForeignLanguage> getForeignLanguageList() {
		if (foreignLanguageList==null){
			foreignLanguageList=new ArrayList<ForeignLanguage>() ;
		}
		return foreignLanguageList;
	}

	public void setForeignLanguageList(List<ForeignLanguage> foreignLanguageList) {
		if (foreignLanguageList==null){
			foreignLanguageList=new ArrayList<ForeignLanguage>() ;
		}
		this.foreignLanguageList = foreignLanguageList;
	}

	public List<Basic> getCustom() {
		if (custom==null){
			custom=new ArrayList<Basic>() ;
		}
		return custom;
	}

	public void setCustom(List<Basic> custom) {
		this.custom = custom;
	}

	public String getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
}
