package com.ac.user.api;

import com.ac.user.constant.ModulePrePath;
import com.ac.user.entity.User;
import com.ac.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        return userService.getUser(userId);
    }

    /**
     * 下单扣减余额
     * @param userId
     * @param amount
     */
    @PutMapping("/deduction_balance/{userId}/{amount}")
    void deductionBalance(@PathVariable int userId,@PathVariable double amount){
        userService.deductionBalance(userId,amount);
    }
}
