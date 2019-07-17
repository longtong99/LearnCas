package com.zzl.cas.service.impl;

import com.zzl.cas.dao.UserMapper;
import com.zzl.cas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: zzl
 * @description:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(String username) {
        userMapper.insert(username,"123456");
    }
}
