package com.ac.order.feign;

import com.ac.order.constant.ModulePrePath;
import com.ac.order.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    UserDto getUser(@PathVariable("userId") String userId);
}
