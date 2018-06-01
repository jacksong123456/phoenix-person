package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:     ASSOData.java
 * @author         fxtx
 * @param <K>
 * @Date           2015年3月25日 上午11:21:09 
 * @Description:   权限控制中 具体人的信息
 */
public class ASSOData implements Serializable{
	/**
	 * 
	 */
	public static final long serialVersionUID = -8604637568909567613L;
	public String tag;
	public List<DemandASSOData> conn;

	public String getTag() {
		if(tag==null){
			tag="" ;
		}
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<DemandASSOData> getConn() {
		if (conn==null){
			conn = new ArrayList<DemandASSOData>() ;
		}
		return conn;
	}

	public void setConn(List<DemandASSOData> conn) {
		this.conn = conn;
	}

	public ASSOData(String tag, List<DemandASSOData> conn) {
		super();
		this.tag = tag;
		this.conn = conn;

	}
	public ASSOData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
 
