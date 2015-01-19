package com.base.sys.sys_resource.service.impl;

import com.base.sys.sys_resource.dao.SysResourceDao;
import com.base.sys.sys_resource.model.SysResource;
import com.base.sys.sys_resource.service.SysResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zkdu on 2015/1/9.
 */
@Service("sysResourceService")
public class SysResourceServiceImpl implements SysResourceService {
    @Resource
    private SysResourceDao sysResourceDao;

    @Override
    public List<SysResource> getAll() {
        List<SysResource> list = sysResourceDao.findAll();
        return list;
    }
}
