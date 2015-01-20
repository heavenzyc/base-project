package com.base.sys.sys_resource.model;

import com.base.common.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by heaven.zyc on 14-9-28.
 */
@Entity
@Table(name = "sys_resource")
public class SysResource extends BaseEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "pid")
    private String pid;

    @Column(name = "name")
    private String name;

    @Column(name = "pattern")
    private String pattern;

    @Column(name = "entry_url")
    private String entryUrl;

    @Column(name = "icon_class")
    private String iconClass;

    @Column(name = "type")
    private Integer type;

    @Column(name = "sort")
    private Integer sort;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private ResourceStatus status;

    @Column(name = "create_time")
    private Date createTime;

    @Transient
    private List<SysResource> subRes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getEntryUrl() {
        return entryUrl;
    }

    public void setEntryUrl(String entryUrl) {
        this.entryUrl = entryUrl;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<SysResource> getSubRes() {
        return subRes;
    }

    public void setSubRes(List<SysResource> subRes) {
        this.subRes = subRes;
    }
}
