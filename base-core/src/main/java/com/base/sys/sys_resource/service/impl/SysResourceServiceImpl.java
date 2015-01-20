package com.base.sys.sys_resource.service.impl;

import com.base.sys.sys_resource.dao.SysResourceDao;
import com.base.sys.sys_resource.model.SysResource;
import com.base.sys.sys_resource.service.SysResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Override
    public List<SysResource> getMenus() {
        List<SysResource> first = sysResourceDao.find(" from SysResource where type=1 ");
        this.test(first,1);
        return first;
    }

    private void test(List<SysResource> resources, Integer type) {
        type++;
        List<SysResource> result = new ArrayList<SysResource>();
        if (type > 3 || resources.size() == 0) return;
        String hql = " from SysResource where id like ? and type=? ";
        for (SysResource parent : resources) {
            List<SysResource> list = sysResourceDao.find(hql,parent.getId()+"%",parent.getType()+1);
            parent.setSubRes(list);
            for (SysResource child : list) {
                result.add(child);
            }
        }
        test(result,type);
    }
}
