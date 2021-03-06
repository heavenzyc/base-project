package com.base.sys.manager.service.impl;

import com.base.sys.manager.dao.ManagerDao;
import com.base.sys.manager.domain.Manager;
import com.base.sys.manager.service.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private ManagerDao managerDao;

    @Override
    public Manager get(Integer id) {
        Manager manager = managerDao.get(id);
        return manager;
    }

    @Override
    public List<Manager> findAll() {
        List<Manager> managers = managerDao.findAll();
        return managers;
    }

    @Override
    public Manager login(String account, String password) {
        return managerDao.login(account,password);
    }
}
