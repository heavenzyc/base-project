package com.base.user.service;

import com.base.user.domain.User;

import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
public interface UserService {

    User get(Integer id);

    List<User> findAll();
}
