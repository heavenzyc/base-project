package com.base.common;

import com.base.pagination.Pagination;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        this.getHibernateTemplate().setCacheQueries(true);
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
        return getHibernateTemplate().findByNamedParam(queryString,paramNames.toArray(new String[paramNames.size()]),values.toArray());
    }

    @Override
    public T findByProperty(String propertyName, String propertyValue) {
        String hql = "from " + type.getName() +" where " + propertyName + "=?";
        List<T> list = find(hql,propertyValue);
        if (list.size() == 0) return null;
        return list.get(0);
    }

    @Override
    public Pagination<T> getPage(String queryString, Map<String, Object> params, Pagination pagination) {
        final String queryStr = queryString;
        final Map<String,Object> pa = params;
        final Pagination<T> pag = pagination;
        return (Pagination<T>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                long totalCount = 0;
                int fromIndex = queryStr.toLowerCase().indexOf("from");
                int orderIndex = queryStr.toLowerCase().indexOf("order");
                String countQueryStr = "";
                if(orderIndex!=-1){
                    countQueryStr = "select count(*) " + queryStr.substring(fromIndex,orderIndex);
                }else{
                    countQueryStr = "select count(*) " + queryStr.substring(fromIndex);
                }
                Query countQuery = session.createQuery(countQueryStr);
                for (Map.Entry<String,Object> entry : pa.entrySet()){
                    countQuery.setParameter(entry.getKey(),entry.getValue());
                }
                totalCount = (Long)countQuery.uniqueResult();
                Query query = session.createQuery(queryStr);
                for (Map.Entry<String,Object> entry : pa.entrySet()){
                    query.setParameter(entry.getKey(),entry.getValue());
                }
                List result = query.setFirstResult((pag.getCurrentPage()-1) * pag.getPageSize()).setMaxResults(pag.getPageSize()).list();
                pag.setCount((int) totalCount);
                pag.setRecord(result);
                return pag;
            }
        });
    }

    @Override
    public long count(String queryString, Map<String, Object> params) {
        final String queryStr = queryString;
        final Map<String,Object> map = params;
        return (Long)getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                int fromIndex = queryStr.toLowerCase().indexOf("from");
                int orderIndex = queryStr.toLowerCase().indexOf("order");
                String countQueryStr = "";
                if(orderIndex!=-1){
                    countQueryStr = "select count(*) " + queryStr.substring(fromIndex,orderIndex);
                }else{
                    countQueryStr = "select count(*) " + queryStr.substring(fromIndex);
                }
                Query countQuery = session.createQuery(countQueryStr);
                for (Map.Entry<String,Object> entry : map.entrySet()){
                    countQuery.setParameter(entry.getKey(),entry.getValue());
                }
                return countQuery.uniqueResult();
            }
        });
    }

    @Override
    public long sum(String queryString,String propertyName, Map<String, Object> params) {
        final String queryStr = queryString;
        final Map<String,Object> map = params;
        final String proName = propertyName;
        return (Long)getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                int fromIndex = queryStr.toLowerCase().indexOf("from");
                int orderIndex = queryStr.toLowerCase().indexOf("order");
                String countQueryStr = "";
                if(orderIndex!=-1){
                    countQueryStr = "select sum("+proName+") " + queryStr.substring(fromIndex,orderIndex);
                }else{
                    countQueryStr = "select sum("+proName+") " + queryStr.substring(fromIndex);
                }
                Query countQuery = session.createQuery(countQueryStr);
                for (Map.Entry<String,Object> entry : map.entrySet()){
                    countQuery.setParameter(entry.getKey(),entry.getValue());
                }
                return countQuery.uniqueResult();
            }
        });
    }
}
