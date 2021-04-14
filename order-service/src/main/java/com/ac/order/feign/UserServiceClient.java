package com.ac.order.feign;

import com.ac.order.constant.ModulePrePath;
import com.ac.order.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author Alan Chen
 * @description 用户接口
 * @date 2021/3/31
 */
@FeignClient("user-service")
public interface UserServiceClient {

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @GetMapping(ModulePrePath.API+"/users/{userId}")
    UserDto getUser(@PathVariable("userId") int userId);

    /**
     * 下单扣减余额
     * @param userId
     * @param amount
     */
    @PutMapping(ModulePrePath.API+"/users/deduction_balance/{userId}/{amount}")
    void deductionBalance(@PathVariable("userId") int userId,@PathVariable("amount") double amount);
}
