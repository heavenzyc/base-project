package com.base.common;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;

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
    public T get(String clazzName, Serializable id) {
        return (T) getHibernateTemplate().get(clazzName,id);
    }

    @Override
    public T load(Serializable id) {
        return getHibernateTemplate().load(type,id);
    }

    @Override
    public T load(String clazzName, Serializable id) {
        return (T) getHibernateTemplate().load(clazzName,id);
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

    @Override
    public List<T> findAll() {
        String hql = "from "+type.getName();
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public List<T> findAll(String orderParam, OrderType orderType) {
        String hql = "from "+type.getName() + " order by "+orderParam + " " + orderType.name();
        return this.getHibernateTemplate().find(hql);
    }

    @Override
    public List<T> find(String queryString) {
        return this.getHibernateTemplate().find(queryString);
    }

    @Override
    public List<T> find(String queryString, Object... values) {
        return this.getHibernateTemplate().find(queryString,values);
    }

    @Override
    public List<T> find(String queryString, Object value) {
        return this.getHibernateTemplate().find(queryString,value);
    }

    @Override
    public List<T> find(String queryString,Map<String, Object> params) {
        List<String> paramNames = new ArrayList<String>();
        List<Object> values = new ArrayList<Object>();
        for (Map.Entry<String,Object> entry : params.entrySet()){
            paramNames.add(entry.getKey());
            values.add(entry.getValue());
        }
        return getHibernateTemplate().findByNamedParam(queryString, (String[]) paramNames.toArray(new String[paramNames.size()]),values.toArray());
    }

    @Override
    public T findByProperty(String propertyName, String propertyValue) {
        String hql = "from " + type.getName() +" where " + propertyName + "=?";
        List<T> list = find(hql,propertyValue);
        if (list.size() == 0) return null;
        return list.get(0);
    }
}
