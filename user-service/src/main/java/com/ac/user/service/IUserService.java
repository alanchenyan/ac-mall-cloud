package com.ac.user.service;

import com.ac.user.entity.User;

/**
 * @author Alan Chen
 * @description
 * @date 2020/10/15
 */
public interface IUserService {

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    User getUser(String id);
}
