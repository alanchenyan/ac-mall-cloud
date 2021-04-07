package com.ac.auth.response;

import lombok.Data;

/**
 * @author Alan Chen
 * @description
 * @date 2020/11/10
 */
@Data
public class LoginResponse {

    private String token;

    private String refreshToken;

    private String userId;

    private String username;
}
