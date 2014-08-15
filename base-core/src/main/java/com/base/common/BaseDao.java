package com.base.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
public interface BaseDao<T,PK extends Serializable> {

    public T get(PK id);

    public void save(T obj);

    public void delete(T obj);

    public void update(T obj);

    public void saveOrUpdate(T obj);

    public void deleteAll(Collection<T> objs);

//    String hql = " from BuildingNews b where b.enable=1 and b.merchant.id=:merchantid and b.shareEnvelope != null ORDER BY id desc ";
//    Finder finder = new SimpleParametersFinder(hql, "merchantid", merchantid);
//    return find(finder, pagination);
}
