package com.zzl.cas.dao;/**
 * Created by admin on 2019/7/18.
 */

import com.zzl.cas.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author zzl
 * @version 1.0
 * @desception
 * @date 2019/7/18 11:18
 */
public interface UserMapper {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser selectUser(@Param("username") String username);

}
