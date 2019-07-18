package com.zzl.cas.service;/**
 * Created by admin on 2019/7/18.
 */

import com.zzl.cas.entity.SysUser;

/**
 * @author zzl
 * @version 1.0
 * @desception
 * @date 2019/7/18 11:20
 */
public interface UserService {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser selectUser(String username);

}
