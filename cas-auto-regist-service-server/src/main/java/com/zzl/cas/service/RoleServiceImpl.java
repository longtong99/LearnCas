package com.zzl.cas.service;


import com.zzl.cas.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author: zzl
 * @date:
 * @description:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public String findRolesByUserId(String uid) {
        return roleDao.findRolesByUserId(uid);
    }

    @Override
    public Set<String> findAllRoles() {
        return roleDao.findAllRoles();
    }
}
