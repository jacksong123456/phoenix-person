package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonalInformation implements Serializable {
	
	private static final long serialVersionUID = 8787417744938246732L;
	
	/**籍贯国  国内、国外*/
	private String birthPlaceCountryName;
	/**
	 * 籍贯所在国家
	 */
	private String birthCountry;
	/**
	 * 籍贯所在省
	 */
	private String birthCity;
	/**
	 * 籍贯所在城市
	 */
	private String birthCounty;
	/**籍贯地址*/
	private String birthPlaceAddress;
	/**
	 * 信仰
	 */
	private String faithName;
	/**
	 * 身体状态
	 */
	private String health;
	
	/**
	 * 擅长
	 * */
	private String goodAt;
	
	/**
	 * 民族
	 */
	private String raceName;
	/**
	 * 爱好
	 */
	private String hobby;
	/**
	 * 习惯
	 */
	private String habit;
	/**
	 * 重要日期
	 */
	private List<Basic> keyDate;
	/**
	 * 社会关系
	 */
	private List<Basic> socialRelations;
	/**
	 * 自定义
	 */
	private List<Basic> custom;
	
	private String birthday;
	
	/**
	 * 血型
	 */
	private String bloodType;
	
	/**
	 * 感情状况
	 */
	private String emotionalState;
	
	/**
	 * 宗教信仰
	 */
	private String religiousBelief;
	
	/**
	 * 家庭成员
	 */
	private List<FamilyMember> familyMembers;
	

	/**
	 * 前端用
	 */
	private String isClone;
	

	

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getGoodAt() {
		return goodAt;
	}

	public void setGoodAt(String goodAt) {
		this.goodAt = goodAt;
	}

	public String getBirthCountry() {
        if(null == birthCountry){
            birthCountry = "";
        }
        return birthCountry;
	}

	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public String getBirthCounty() {
        if(null == birthCounty){
            birthCounty = "";
        }
        return birthCounty;
	}

	public void setBirthCounty(String birthCounty) {
		this.birthCounty = birthCounty;
	}

	public String getBirthCity() {
        if(null == birthCity){
            birthCity = "";
        }
        return birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public String getFaithName() {
        if(null == faithName){
            faithName = "";
        }
        return faithName;
	}

	public void setFaithName(String faithName) {
		this.faithName = faithName;
	}

	public String getHealth() {
        if(null == health){
            health = "";
        }
        return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getRaceName() {
        if(null == raceName){
            raceName = "";
        }
        return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	public String getHobby() {
        if(null == hobby){
            hobby = "";
        }
        return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getHabit() {
        if(null == habit){
            habit = "";
        }
        return habit;
	}

	public void setHabit(String habit) {
		this.habit = habit;
	}

	public List<Basic> getKeyDate() {
        if(null == keyDate){
            keyDate = new ArrayList<Basic>();
        }
        return keyDate;
	}

	public void setKeyDate(List<Basic> keyDate) {
		this.keyDate = keyDate;
	}

	public List<Basic> getSocialRelations() {
        if(null == socialRelations){
            socialRelations = new ArrayList<Basic>();
        }
        return socialRelations;
	}

	public void setSocialRelations(List<Basic> socialRelations) {
		this.socialRelations = socialRelations;
	}

	public List<Basic> getCustom() {
        if(null == custom){
            custom = new ArrayList<Basic>();
        }
        return custom;
	}

	public void setCustom(List<Basic> custom) {
		this.custom = custom;
	}

	public String getBirthPlaceCountryName() {
		return birthPlaceCountryName;
	}

	public void setBirthPlaceCountryName(String birthPlaceCountryName) {
		this.birthPlaceCountryName = birthPlaceCountryName;
	}

	public String getBirthPlaceAddress() {
		return birthPlaceAddress;
	}

	public void setBirthPlaceAddress(String birthPlaceAddress) {
		this.birthPlaceAddress = birthPlaceAddress;
	}
	
	
	
	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getEmotionalState() {
		return emotionalState;
	}

	public void setEmotionalState(String emotionalState) {
		this.emotionalState = emotionalState;
	}

	public String getReligiousBelief() {
		return religiousBelief;
	}

	public void setReligiousBelief(String religiousBelief) {
		this.religiousBelief = religiousBelief;
	}

	public List<FamilyMember> getFamilyMembers() {
		return familyMembers==null?new ArrayList<FamilyMember>():familyMembers;
	}

	public void setFamilyMembers(List<FamilyMember> familyMembers) {
		this.familyMembers = (familyMembers==null?new ArrayList<FamilyMember>():familyMembers);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIsClone() {
		return isClone;
	}

	public void setIsClone(String isClone) {
		this.isClone = isClone;
	}

}
