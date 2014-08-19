package com.base.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by heaven.zyc on 14-8-14.
 */
public interface BaseDao<T,PK extends Serializable> {

    public T get(PK id);

    public T get(String clazzName,PK id);

    public T load(PK id);

    public T load(String clazzName, PK id);

    public void save(T obj);

    public void delete(T obj);

    public void update(T obj);

    public void saveOrUpdate(T obj);

    public void deleteAll(Collection<T> objs);

    public List<T> findAll();

    /**
     * s查询全部数据，并按指定字段排序进行排序
     * @param orderParam 排序字段
     * @param orderType 排序规则
     * @see com.base.common.OrderType
     * @return
     */
    public List<T> findAll(String orderParam, OrderType orderType);

    public List<T> find(String queryString);

    /**
     * s根据hql语句和值查询
     * @param queryString 查询语句（from User where age=? and name=?）
     * @param values
     * @return
     */
    public List<T> find(String queryString,Object... values);

    /**
     * s根据hql语句和值查询
     * @param queryString 查询语句（from User where name=?）
     * @param value
     * @return
     */
    public List<T> find(String queryString,Object value);

    /**
     * 根据传入的Map查询，map的key为属性名，value为属性值
     * @param queryString 例如:from User where name=:name and age=:age
    * @param params  map.put("name","张三"); map.put("age",18);
     * @return
     */
    public List<T> find(String queryString,Map<String,Object> params);

    /**
     * s根据属性名和属性值查询一条数据
     * @param propertyName 属性名
     * @param propertyValue 属性值
     * @return
     */
    public T findByProperty(String propertyName,String propertyValue);

}
