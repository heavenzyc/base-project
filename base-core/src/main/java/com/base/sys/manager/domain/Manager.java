package com.base.sys.manager.domain;

import com.base.common.BaseEntity;
import com.base.sys.role.domain.SysRole;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Created by heaven.zyc on 14-8-14.
 */
@Entity
@Table(name = "sys_manager")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Manager extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ManagerStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private SysRole role;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public ManagerStatus getStatus() {
        return status;
    }

    public void setStatus(ManagerStatus status) {
        this.status = status;
    }
}
