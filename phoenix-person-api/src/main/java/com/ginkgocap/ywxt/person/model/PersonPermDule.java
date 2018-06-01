package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

/**
 * @Title: DemandPermDule.java
 * @Package com.ginkgocap.ywxt.demand.form.property
 * @Description:
 * @author haiyan
 * @date 2015-3-6 下午3:28:21
 */
public class PersonPermDule implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2648141978024791787L;
	private Boolean value;

	/**
	 * @return the value
	 */
	public Boolean getValue() {
        if(null == value){
            value = false;
        }
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Boolean value) {
		this.value = value;
	}



}
