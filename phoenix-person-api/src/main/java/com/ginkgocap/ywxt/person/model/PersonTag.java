package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

/**
 * 用户的标签
 * @author hdy
 * @date 2014-10-21
 */
public class PersonTag implements Serializable {
    /**
     * 标签ID
     */
	private Long tagId;
    /**
     * 用户ID
     */
	private Long userId;
    /**
     * 标签名称
     */
	private String tagName;

    private  Long num ;

    public Long getNum() {
        if (num==null){
            num = 0l ;
        }
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getTagId() {
        if(null == tagId){
            tagId = 0L;
        }
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
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

    public String getTagName() {
        if(null == tagName){
            tagName = "";
        }
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
