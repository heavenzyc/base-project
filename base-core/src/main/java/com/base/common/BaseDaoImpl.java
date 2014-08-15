package com.base.common;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

/**
 * Created by heaven.zyc on 14-8-14.
 */
public class BaseDaoImpl<T,PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T,Serializable> {

    private Class<T> type;

    public BaseDaoImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.type = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public T get(Serializable id) {
        return getHibernateTemplate().get(type,id);
    }

    @Override
    public void save(T obj) {
        getHibernateTemplate().save(obj);
    }

    @Override
    public void delete(T obj) {
        getHibernateTemplate().delete(obj);
    }

    @Override
    public void update(T obj) {
        getHibernateTemplate().update(obj);
    }

    @Override
    public void saveOrUpdate(T obj) {
        getHibernateTemplate().saveOrUpdate(obj);
    }

    @Override
    public void deleteAll(Collection<T> objs) {
        getHibernateTemplate().deleteAll(objs);
    }
}
