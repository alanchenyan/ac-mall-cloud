package com.ac.user.api;

import com.ac.user.constant.ModulePrePath;
import com.ac.user.entity.User;
import com.ac.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alan Chen
 * @description 用户API
 * @date 2021/3/31
 */
@RestController
@RequestMapping(ModulePrePath.API+"/users")
public class UserApi {

    @Autowired
    IUserService userService;

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        return userService.getUser(userId);
    }
}
