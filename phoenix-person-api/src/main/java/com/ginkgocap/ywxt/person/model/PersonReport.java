package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.Date;

public class PersonReport implements Serializable {
    private String id; //主键
    private Long personId;//人脉对象ID
    private Long userId;//人脉所属用户的ID
    private String content; //举报类型(违法、欺诈等汉字，逗号分隔)
    private String reason;//举报理由
    private Long reportUserId;//举报人
    private Date createTime;//举报时间

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(Long reportUserId) {
        this.reportUserId = reportUserId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
