package com.ginkgocap.ywxt.person.model;


import java.io.Serializable;

/**
 * 人脉名称
 */
public class PersonName implements Serializable {
	private static final long serialVersionUID = -3153505096946533595L;
	/**
     * 姓
     */
    private String lastname;
    /**
     * 名
     */
    private String firstname;
    
    /**
     * 1-中文名，2-英文名，N-自定义
     */
    private Byte parentType;
    public static Byte PARENT_TYPE_ZH = 1;//中文名
    /**
     * 标签类型
     */
    private TypeTag typeTag;

    public String getLastname() {
        if(null == lastname){
            lastname = "";
        }
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        if(null == firstname){
            firstname = "";
        }
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Byte getParentType() {
        if(null == parentType){
            parentType = 0;
        }
        return parentType;
    }

    public void setParentType(Byte parentType) {
        this.parentType = parentType;
    }

    public TypeTag getTypeTag() {
        if(null == typeTag){
            typeTag = new TypeTag();
        }
        return typeTag;
    }

    public void setTypeTag(TypeTag typeTag) {
        this.typeTag = typeTag;
    }
}
