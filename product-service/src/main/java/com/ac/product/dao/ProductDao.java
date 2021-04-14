package com.ac.product.dao;

import com.ac.product.entiy.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Alan Chen
 * @description
 * @date 2021/4/13
 */
@Repository
@Mapper
public interface ProductDao extends BaseMapper<Product> {

}
