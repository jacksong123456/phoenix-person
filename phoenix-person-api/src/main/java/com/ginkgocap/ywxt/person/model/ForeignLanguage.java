package com.ginkgocap.ywxt.person.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/4/21.
 */
public class ForeignLanguage implements Serializable {
    private String type ;
    private String levelType ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }
}
