package com.base.sys.role.domain;

import com.base.common.BaseEntity;
import com.base.sys.sys_resource.model.SysResource;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(
            fetch = FetchType.LAZY,
            targetEntity=SysResource.class,
            cascade= {CascadeType.PERSIST}
    )
    @JoinTable(
            name="sys_role_resource",
            joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="resource_id")
    )
    private List<SysResource> resourceList;


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

    public List<SysResource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<SysResource> resourceList) {
        this.resourceList = resourceList;
    }
}
