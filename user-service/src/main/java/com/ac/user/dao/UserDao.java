package com.ac.user.dao;

import com.ac.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author Alan Chen
 * @description
 * @date 2020/10/15
 */
@Repository
public class UserDao {

    public User get(String id){
        if("1".equals(id)){
            User user = new User();
            user.setId("1");
            user.setUserName("AC");

            return user;
        }
       return null;
    }
}
