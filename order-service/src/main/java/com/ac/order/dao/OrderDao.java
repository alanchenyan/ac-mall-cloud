package com.ac.order.dao;

import com.ac.order.entity.ProductOrder;
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
public interface OrderDao extends BaseMapper<ProductOrder> {

}
