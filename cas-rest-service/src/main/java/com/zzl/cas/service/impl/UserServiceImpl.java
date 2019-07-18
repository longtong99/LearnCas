package com.zzl.cas.service.impl;/**
 * Created by admin on 2019/7/18.
 */

import com.zzl.cas.dao.UserMapper;
import com.zzl.cas.entity.SysUser;
import com.zzl.cas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzl
 * @version 1.0
 * @desception
 * @date 2019/7/18 11:20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser selectUser(String username) {
        return userMapper.selectUser(username);
    }

}
