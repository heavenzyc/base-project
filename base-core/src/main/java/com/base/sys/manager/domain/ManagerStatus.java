package com.base.sys.manager.domain;

/**
 * Created by zkdu on 2015/1/22.
 */
public enum ManagerStatus {

    ACTIVE(1,"激活");
    private Integer value;
    private String desc;
    private ManagerStatus(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }
}
