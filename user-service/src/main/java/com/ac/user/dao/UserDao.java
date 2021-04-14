package com.ac.user.dao;

import com.ac.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Alan Chen
 * @description
 * @date 2020/10/15
 */
@Repository
@Mapper
public interface UserDao extends BaseMapper<User> {

}
