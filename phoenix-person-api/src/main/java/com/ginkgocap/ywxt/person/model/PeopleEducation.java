package com.ginkgocap.ywxt.person.model;

import java.util.List;

/**
 * 人脉：教育经历
 * @author wenbin
 *
 */
public class PeopleEducation extends BaseObject {

	private static final long serialVersionUID = 1018328642974424367L;
	
	/**主键*/
	private Long id;
	/**时间起*/
	private String startTime;
	/**时间止*/
	private String endTime;
	/**学校*/
	private String school;
	/**学院*/
	private String college;
	/**专业类别*/
	private String specialty;
	/**学历*/
	private String educationalBackgroundType;
	/**学位*/
	private String degreeType;
	/**描述*/
	private String description;
	
	
	/**海外学习：0-否，1-是*/
	private Integer studyAbroadType;
	/**同学关系*/
	private List<PeopleStudentsRelationship> studentsRelationshipList;
	/**外语语种*/
	private List<PeopleForeignLanguage> foreignLanguageList;
	/**自定义字段*/
	private List<PeoplePersonalLine> personalLineList;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getSpecialty() {
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
	public String getDegreeType() {
		return degreeType;
	}
	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStudyAbroadType() {
		return studyAbroadType;
	}
	public void setStudyAbroadType(Integer studyAbroadType) {
		this.studyAbroadType = studyAbroadType;
	}
	public List<PeopleStudentsRelationship> getStudentsRelationshipList() {
		return studentsRelationshipList;
	}
	public void setStudentsRelationshipList(
			List<PeopleStudentsRelationship> studentsRelationshipList) {
		this.studentsRelationshipList = studentsRelationshipList;
	}
	public List<PeopleForeignLanguage> getForeignLanguageList() {
		return foreignLanguageList;
	}
	public void setForeignLanguageList(
			List<PeopleForeignLanguage> foreignLanguageList) {
		this.foreignLanguageList = foreignLanguageList;
	}
	public List<PeoplePersonalLine> getPersonalLineList() {
		return personalLineList;
	}
	public void setPersonalLineList(List<PeoplePersonalLine> personalLineList) {
		this.personalLineList = personalLineList;
	}
	
	
}
