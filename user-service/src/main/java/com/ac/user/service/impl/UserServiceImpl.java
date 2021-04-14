package com.ac.user.service.impl;

import com.ac.user.dao.UserDao;
import com.ac.user.entity.User;
import com.ac.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alan Chen
 * @description
 * @date 2020/10/15
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;

    public User getUser(String id) {
        System.out.println("获取用户信息");
        return userDao.selectById(id);
    }

    /**
     * 下单扣减余额
     * @param userId
     * @param amount
     */
    @Transactional(rollbackFor = Exception.class)
    public void deductionBalance(int userId, double amount) {
        User user = userDao.selectById(userId);
        double newBalance=user.getBalance()-amount;
        user.setBalance(newBalance);

        userDao.updateById(user);

        System.out.println("下单扣减余额成功!");
    }
}
