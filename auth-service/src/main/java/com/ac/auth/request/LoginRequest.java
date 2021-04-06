package com.ac.auth.request;

import lombok.Data;

/**
 * @author Alan Chen
 * @description
 * @date 2020/11/10
 */
@Data
public class LoginRequest {

    private String username;

    private String password;
}
