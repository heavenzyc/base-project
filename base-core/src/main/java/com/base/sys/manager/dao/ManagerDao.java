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

    public List<Manager> getAll(){
        Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Criteria c = s.createCriteria(Manager.class);
        c.setCacheable(true);//这句必须要有
        System.out.println("第一次读取");
        s.beginTransaction();
        List<Manager> users = c.list();
        s.getTransaction().commit();
        System.out.println(users.size());

        s = getHibernateTemplate().getSessionFactory().getCurrentSession();
        c = s.createCriteria(Manager.class);
        c.setCacheable(true);//这句必须要有
        System.out.println("第二次读取");
        s.beginTransaction();
        users = c.list();
        s.getTransaction().commit();
        System.out.println(users.size());
        s.close();
        return null;
    }
}
