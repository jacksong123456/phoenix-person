package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;


/**
 * 代码表
 *
 * @author yangjie
 */
public class CodeSort implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -6800065622225396060L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 父级id
     */
    private Long pid;

    /**
     * 目录名称
     */
    private String name;

    /**
     * 路径Id，如:01/0101
     */
    private String sortId;

    /**
     * 1-职业列表；2-分类列表
     */
    private String codeType;

    /**
     * 排序
     */
    private String orderNo;

    public Long getId() {
        if (id==null){
            id=0l ;
        }
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        if (pid==null){
            pid=0l ;
        }
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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

    public String getSortId() {
        if (sortId==null){
            sortId="" ;
        }
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getCodeType() {
        if (codeType==null){
            codeType="" ;
        }
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getOrderNo() {
        if (orderNo==null){
            return "" ;
        }
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


}
