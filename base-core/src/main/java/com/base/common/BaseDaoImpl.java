package com.base.common;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

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
}
