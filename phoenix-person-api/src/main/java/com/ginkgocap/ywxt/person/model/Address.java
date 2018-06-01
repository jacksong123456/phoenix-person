package com.ginkgocap.ywxt.person.model;


import java.io.Serializable;

public class Address  implements Serializable {
	/**
	 * 父类型
	 */
    private Byte parentType ;
    /**
     * 地域类型
     */
    private Byte areaType ;
    /**
     * 状态名称
     */
    private String stateName ;
    /**
     * 城市名称
     */
    private  String cityName  ;
    /**
     * 国家名称
     */
    private String countyName  ;

    public Byte getParentType() {
        if (parentType==null){
            parentType=0 ;
        }
        return parentType;
    }

    public void setParentType(Byte parentType) {
        this.parentType = parentType;
    }

    public Byte getAreaType() {
        if (areaType==null){
            areaType = 0 ;
        }
        return areaType;
    }

    public void setAreaType(Byte areaType) {
        this.areaType = areaType;
    }

    public String getStateName() {
        if (stateName==null){
            stateName="" ;
        }
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        if (cityName==null){
            cityName="" ;
        }
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        if (countyName==null){
            countyName="" ;
        }
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}
