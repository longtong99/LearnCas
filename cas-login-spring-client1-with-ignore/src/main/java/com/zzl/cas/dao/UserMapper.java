package com.zzl.cas.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author: zzl
 * @description: 用户操作 dao层
 */
public interface UserMapper {

    /**
     * 创建用户
     * @param username
     * @param password
     * @return
     */
    void insert(@Param("username") String username, @Param("password") String password);
}
