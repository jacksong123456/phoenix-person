package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

/**
 * 人脉、好友目录实体
 *
 * @author xingtianlun
 */
public class PersonCategoryRelation implements Serializable {

    /**
     * 用户或者人脉ID
     */
    private Long personId;

    /**
     * 1-用户;2-人脉
     */
    private Integer personType;

    /**
     * 目录ID(当目录ID为 -1 时，说明目录为顶级目录)
     */
    private Long categoryId;

    /**
     * 1-我创建的人脉;2-我收藏的人脉;3-我保存的人脉;5-我的好友
     */
    private Integer ctype;

    /**
     * 创建时间
     */
    private java.sql.Timestamp ctime;

    /**
     * 用户ID
     */
    private Long userId;

    public static enum Ctype {
        create(1), saved(3), ref(2), friend(5);
        public Integer code;
        Ctype(Integer code) {
            this.code = code;
        }
    }

    public Long getPersonId() {
        if(null == personId){
            personId = 0L;
        }
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Integer getPersonType() {
        if(null == personType){
            personType = 0;
        }
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public Long getCategoryId() {
        if(null == categoryId){
            categoryId = 0L;
        }
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCtype() {
        if(null == ctype){
            ctype = 0;
        }
        return ctype;
    }

    public void setCtype(Integer ctype) {
        this.ctype = ctype;
    }

    public java.sql.Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(java.sql.Timestamp ctime) {
        this.ctime = ctime;
    }

    public Long getUserId() {
        if(null == userId){
            userId = 0L;
        }
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
