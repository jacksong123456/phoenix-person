package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 人脉、好友目录实体
 *
 * @author xingtianlun
 */
public class PersonCategory implements Serializable {

	private static final long serialVersionUID = 2165881577764822245L;

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
     * 用户ID
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Timestamp ctime;
    
    /**
     * 评定下级是否有子目录
     * */
    private boolean isParent;
    
    /**
     * 目录下的资源个数
     */
    private long count;
    

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        if(null == pid){
            pid = 0L;
        }
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        if(null == name){
            name = "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSortId() {
        if(null == sortId){
            sortId = "";
        }
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

}
