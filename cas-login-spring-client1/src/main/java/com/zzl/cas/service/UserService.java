package com.zzl.cas.service;

/**
 * @author: zzl
 * @description: 用户操作service层
 */
public interface UserService {

    /**
     * 创建用户
     * @param username
     * @return
     */
    void insert(String username);
}
