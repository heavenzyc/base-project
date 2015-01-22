package com.base.sys.sys_resource.service.impl;

import com.base.sys.manager.domain.Manager;
import com.base.sys.role.domain.SysRole;
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
//        this.buildMenus(first, 1);
        return first;
    }

    @Override
    public List<SysResource> getManagerMenus(Manager manager) {
        SysRole role = manager.getRole();
        List<SysResource> list = role.getResourceList();
        List<SysResource> firstList = new ArrayList<SysResource>();
        for (SysResource first : list) {
            if (first.getType().equals(1)) {
                firstList.add(first);
            }
        }
        buildMenus(list,1,firstList);
        return firstList;
    }

    private void buildMenus(List<SysResource> resource, Integer type, List<SysResource> firstList) {
        type++;
        List<SysResource> result = new ArrayList<SysResource>();
        if (type > 3 || firstList.size() == 0) return;
        String hql = " from SysResource where id like ? and type=? ";
        for (SysResource parent : resource) {
            //
            List<SysResource> list = sysResourceDao.find(hql,parent.getId()+"%",parent.getType()+1);
            List<SysResource> check = new ArrayList<SysResource>();
            for (SysResource out : list) {
                for (SysResource in : resource) {
                    if (in.getId().equals(out.getId())) {
                        check.add(in);
                        break;
                    }
                }
            }
            parent.setSubRes(check);
            for (SysResource child : check) {
                result.add(child);
            }
        }
        buildMenus(result, type,firstList);
    }
}
