package com.base.common;

import java.io.Serializable;

/**
 * Created by heaven.zyc on 14-8-19.
 */
public abstract class BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3936744392532421052L;
    public abstract Serializable getId();

    /**
     * 由于hibernte的cglib proxy问题 所以 1、class名字的相等性要通过ClassUtils.equals()进行比较
     * 2、other对象的id只能通过getId()的方法来取得
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (this.getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!this.getId().equals(other.getId()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        if (null == getId()) {
            return super.hashCode();
        }
        return getId().hashCode();
    }
}
