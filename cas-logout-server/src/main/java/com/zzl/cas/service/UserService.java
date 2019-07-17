package com.zzl.cas.service;

import java.util.Map;

/**
 * @author: zzl
 * @date:
 * @description:
 */
public interface UserService {

    Map<String,Object> findByUserName(String userName);
}
