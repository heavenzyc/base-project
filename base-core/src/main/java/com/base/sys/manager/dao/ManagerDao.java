package com.base.sys.manager.dao;

import com.base.common.BaseDaoImpl;
import com.base.sys.manager.domain.Manager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
@Repository
public class ManagerDao extends BaseDaoImpl<Manager,Integer> {
}
