package com.base.sys.manager.service;

import com.base.sys.manager.domain.Manager;

import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
public interface ManagerService {

    Manager get(Integer id);

    List<Manager> findAll();
}
