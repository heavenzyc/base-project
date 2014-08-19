package com.base.user.dao;

import com.base.common.BaseDaoImpl;
import com.base.pagination.Pagination;
import com.base.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
@Repository
public class UserDao extends BaseDaoImpl<User,Integer> {
}
