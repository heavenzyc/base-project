package com.base.common;

import java.io.Serializable;

/**
 * Created by heaven.zyc on 14-8-14.
 */
public interface BaseDao<T,PK extends Serializable> {

    public T get(PK id);
}
