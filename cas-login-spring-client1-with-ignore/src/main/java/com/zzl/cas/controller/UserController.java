package com.zzl.cas.controller;


import com.zzl.cas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zzl
 * @description: 用户相关操作controller
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("insert")
    public String insert(String username){

        userService.insert(username);
        return "result";
    }

}
