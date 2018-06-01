package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerMeetingDetail implements Serializable {

    private static final long serialVersionUID = 6777190171450787734L;

    //主键id
    private Long id;

    //客户ID
    private Long customerId;

    //客户id
    private Long personId;

    //会面时间
    private String meetDate;

    //会面日期
    private String time;

    //小时
    private String minTime;

    //颜色  0无颜色   1绿色   2蓝色  3橙色  4红色
    private String color;

    //提醒类型 0:不提醒 1:每天提醒 2:每周提醒 3:每月提醒  4:每年提醒
    private String repeadType;

    //:提醒时间"
    private String remindTime;

    //,"提醒类型  0不提醒  1分钟  2小时  3天"
    private String remindType;

    //标题
    private String title;

    //内容
    private String content;

    //会面地点
    private String address;

    //标签
    private List<CustomerTag> tags;

    //创建时间
    private String ctime;

    //附件组id
    private String taskId;

    //图片视频id
    private String picId;

    //备注
    private String remark;

    //关联信息
    private String relevance;

    //类型（1用户， 2人脉）
    private Integer personType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        if(null == customerId){
            customerId = 0L;
        }
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getMeetDate() {
        if(null == meetDate){
            meetDate = "";
        }
        return meetDate;
    }

    public void setMeetDate(String meetDate) {
        this.meetDate = meetDate;
    }

    public String getTime() {
        if(null == time){
            time = "";
        }
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMinTime() {
        if(null == minTime){
            minTime = "";
        }
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getColor() {
        if(null == color){
            color = "";
        }
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRepeadType() {
        if(null == repeadType){
            repeadType = "";
        }
        return repeadType;
    }

    public void setRepeadType(String repeadType) {
        this.repeadType = repeadType;
    }

    public String getRemindTime() {
        if(null == remindTime){
            remindTime = "";
        }
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public String getRemindType() {
        if(null == remindType){
            remindType = "";
        }
        return remindType;
    }

    public void setRemindType(String remindType) {
        this.remindType = remindType;
    }

    public String getTitle() {
        if(null == title){
            title = "";
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        if(null == content){
            content = "";
        }
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public List<CustomerTag> getTags() {
        if(null == tags){
            tags = new ArrayList<CustomerTag>();
        }
        return tags;
    }

    public void setTags(List<CustomerTag> tags) {
        this.tags = tags;
    }

    public String getCtime() {
        if(null == ctime){
            ctime = "";
        }
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTaskId() {
        if(null == taskId){
            taskId = "";
        }
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPicId() {
        if(null == picId){
            picId = "";
        }
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getRemark() {
        if(null == remark){
            remark = "";
        }
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRelevance() {
        if(null == relevance){
            relevance = "";
        }
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
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
}
