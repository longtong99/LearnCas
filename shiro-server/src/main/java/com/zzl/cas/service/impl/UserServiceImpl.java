package com.zzl.cas.service.impl;

import com.zzl.cas.dao.UserDao;
import com.zzl.cas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: zzl
 * @date
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}
