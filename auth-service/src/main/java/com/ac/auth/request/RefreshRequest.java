package com.ac.auth.request;

import lombok.Data;

/**
 * @author Alan Chen
 * @description
 * @date 2020/11/10
 */
@Data
public class RefreshRequest {

    private String userId;

    private String refreshToken;
}
