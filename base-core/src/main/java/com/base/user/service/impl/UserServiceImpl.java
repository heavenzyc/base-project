package com.base.user.service.impl;

import com.base.user.dao.UserDao;
import com.base.user.domain.User;
import com.base.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by heaven.zyc on 14-8-14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public User get(Integer id) {
        return userDao.get(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
