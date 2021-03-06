package com.base.sys.sys_resource.service;

import com.base.sys.manager.domain.Manager;
import com.base.sys.sys_resource.model.SysResource;

import java.util.List;

/**
 * Created by zkdu on 2015/1/9.
 */

public interface SysResourceService {

    public List<SysResource> getAll();

    public List<SysResource> getMenus();

    public List<SysResource> getManagerMenus(Manager manager);
}
