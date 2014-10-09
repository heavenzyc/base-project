package com.base.sys.role.domain;

import com.base.common.BaseEntity;

import javax.persistence.*;

/**
 * Created by heaven.zyc on 14-8-19.
 */
@Entity
@Table(name = "sys_role")
public class SysRole extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
