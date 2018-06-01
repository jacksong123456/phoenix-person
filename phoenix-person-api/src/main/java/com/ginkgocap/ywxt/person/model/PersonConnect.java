package com.ginkgocap.ywxt.person.model;

/**
 * Created by Administrator on 2015/4/3.
 */
public class PersonConnect {
    public static enum ConnectType {
        // 关联格式（p:人脉,r:事件,o:组织,k:知识）
        event(1, "r"), people(2, "p"), organization(5, "o"), knowledge(6, "k");
        private int v;

        private String c;
   
        private ConnectType(int v, String c) {
            this.v = v;
            this.c = c;  
        }

        public int v() {
            return v;
        }

        public String c() {
            return c;
        }
    }
    private Long id;

    private Long personid;

    private Integer persontype ;

    private String tag;

    private Integer conntype;

    private Long connid;

    private String connname;

    private Long ownerid;

    private String owner;

    private String requirementtype;

    private String career;

    private String company;

    private String address;

    private String hy;

    private String columnpath;

    private Integer columntype;

    private String url;

    private String picpath;

    private Integer allasso;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public Integer getPersontype() {
        if(null == persontype){
            persontype = 0;
        }
        return persontype;
    }

    public void setPersontype(Integer persontype) {
        this.persontype = persontype;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonid() {
        if(null == personid){
            personid = 0L;
        }
        return personid;
    }


    public void setPersonid(Long personid) {
        this.personid = personid;
    }

    public String getTag() {
        if(null == tag){
            tag = "";
        }
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }




    public Integer getConntype() {
        if(null == conntype){
            conntype = 0;
        }
        return conntype;
    }

    public void setConntype(Integer conntype) {
        this.conntype = conntype;
    }

    public Long getConnid() {
        if(null == connid){
            connid = 0L;
        }
        return connid;
    }

    public void setConnid(Long connid) {
        this.connid = connid;
    }

    public String getConnname() {
        if(null == connname){
            connname = "";
        }
        return connname;
    }

    public void setConnname(String connname) {
        this.connname = connname;
    }

    public Long getOwnerid() {
        if(null == ownerid){
            ownerid = 0L;
        }
        return ownerid;
    }

    public void setOwnerid(Long ownerid) {
        this.ownerid = ownerid;
    }

    public String getOwner() {
        if(null == owner){
            owner = "";
        }
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRequirementtype() {
        if(null == requirementtype){
            requirementtype = "";
        }
        return requirementtype;
    }

    public void setRequirementtype(String requirementtype) {
        this.requirementtype = requirementtype;
    }

    public String getCareer() {
        if(null == career){
            career = "";
        }
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getCompany() {
        if(null == company){
            company = "";
        }
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        if(null == address){
            address = "";
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHy() {
        if(null == hy){
            hy = "";
        }
        return hy;
    }

    public void setHy(String hy) {
        this.hy = hy;
    }

    public String getColumnpath() {
        if(null == columnpath){
            columnpath = "";
        }
        return columnpath;
    }

    public void setColumnpath(String columnpath) {
        this.columnpath = columnpath;
    }

    public Integer getColumntype() {
        if(null == columntype){
            columntype = 0;
        }
        return columntype;
    }

    public void setColumntype(Integer columntype) {
        this.columntype = columntype;
    }

    public String getUrl() {
        if(null == url){
            url = "";
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicpath() {
        if(null == picpath){
            picpath = "";
        }
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public Integer getAllasso() {
        if(null == allasso){
            allasso = 0;
        }
        return allasso;
    }

    public void setAllasso(Integer allasso) {
        this.allasso = allasso;
    }
}
