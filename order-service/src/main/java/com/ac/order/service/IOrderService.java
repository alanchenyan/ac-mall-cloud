package com.ac.order.service;

import com.ac.order.entity.Order;

/**
 * @author Alan Chen
 * @description
 * @date 2020/10/15
 */
public interface IOrderService {

    /**
     * 下单
     * @param productId
     * @param userId
     */
    Order makeOrder(String productId, String userId);
}
