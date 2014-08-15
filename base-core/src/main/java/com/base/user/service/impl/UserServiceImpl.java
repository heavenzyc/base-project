package com.base.user.service.impl;

import com.base.user.dao.UserDao;
import com.base.user.domain.User;
import com.base.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by heaven.zyc on 14-8-14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public User get(Integer id) {
        return userDao.get(id);
    }
}