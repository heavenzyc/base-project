package com.base.sys.manager.dao;

import com.base.common.BaseDaoImpl;
import com.base.sys.manager.domain.Manager;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
@Repository
public class ManagerDao extends BaseDaoImpl<Manager,Integer> {

    public Manager login(String account, String password) {
        String hql = " from Manager where account=? and password=? ";
        List<Manager> managers = this.find(hql,account,password);
        if (managers.size()>0) {
            return managers.get(0);
        }
        return null;
    }
}
