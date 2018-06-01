package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/4/14.
 */
public class RDelete implements Serializable {
	private Long userid;
	private Long targetid;
	private Integer targettype;
	private Date dtime;

	public Long getUserid() {
		if (userid == null)
			return 0L;
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getTargetid() {
		if (targetid == null)
			return 0L;
		return targetid;
	}

	public void setTargetid(Long targetid) {
		this.targetid = targetid;
	}

	public Integer getTargettype() {
		if (targettype == null)
			return 0;
		return targettype;
	}

	public void setTargettype(Integer targettype) {
		this.targettype = targettype;
	}

	public Date getDtime() {
		return dtime;
	}

	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}

}
