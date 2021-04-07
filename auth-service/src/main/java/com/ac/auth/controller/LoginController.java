package com.ac.auth.controller;

import com.ac.auth.request.LoginRequest;
import com.ac.auth.request.RefreshRequest;
import com.ac.auth.response.LoginResponse;
import com.ac.auth.response.ResponseCodeEnum;
import com.ac.auth.response.ResponseResult;
import com.ac.auth.util.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Alan Chen
 * @description
 * @date 2020/11/10
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Value("${secretKey:123456}")
    private String secretKey;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    final static String TOKEN = "token";

    final static String REFRESH_TOKEN = "refreshToken";

    @PostMapping("/login")
    public ResponseResult login(@RequestBody @Validated LoginRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseResult.error(ResponseCodeEnum.PARAMETER_ILLEGAL.getCode(), ResponseCodeEnum.PARAMETER_ILLEGAL.getMessage());
        }

        String username = request.getUsername();
        String password = request.getPassword();
        //  假设查询到用户ID是1001
        String userId = "1001";
        if ("alanchen".equals(username) && "admin".equals(password)) {
            //  生成Token
            String token = JWTUtil.generateToken(userId, secretKey);

            //  生成刷新Token
            String refreshToken = UUID.randomUUID().toString().replace("-", "");

            //  放入缓存
            HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();

            String key = userId;
            hashOperations.put(key, TOKEN, token);
            hashOperations.put(key, REFRESH_TOKEN, refreshToken);
            stringRedisTemplate.expire(key, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setRefreshToken(refreshToken);
            loginResponse.setUsername(userId);

            return ResponseResult.success(loginResponse);
        }

        return ResponseResult.error(ResponseCodeEnum.LOGIN_ERROR.getCode(), ResponseCodeEnum.LOGIN_ERROR.getMessage());
    }

    @GetMapping("/logout")
    public ResponseResult logout(@RequestParam("userId") String userId) {
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String key = userId;
        hashOperations.delete(key, TOKEN);
        return ResponseResult.success();
    }

    @PostMapping("/refreshToken")
    public ResponseResult refreshToken(@RequestBody @Validated RefreshRequest request, BindingResult bindingResult) {
        String userId = request.getUserId();
        String refreshToken = request.getRefreshToken();
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String key = userId;
        String originalRefreshToken = hashOperations.get(key, REFRESH_TOKEN);
        if (StringUtils.isBlank(originalRefreshToken) || !originalRefreshToken.equals(refreshToken)) {
            return ResponseResult.error(ResponseCodeEnum.REFRESH_TOKEN_INVALID.getCode(), ResponseCodeEnum.REFRESH_TOKEN_INVALID.getMessage());
        }

        //  生成新token
        String newToken = JWTUtil.generateToken(userId, secretKey);
        hashOperations.put(key, TOKEN, newToken);
        stringRedisTemplate.expire(userId, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

        return ResponseResult.success(newToken);
    }
}
