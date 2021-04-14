package com.ac.order.controller;

import com.ac.order.entity.ProductOrder;
import com.ac.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alan Chen
 * @description
 * @date 2020/10/15
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @GetMapping("/{productId}/{userId}")
    public ProductOrder saveOrder(@PathVariable int productId, @PathVariable int userId){
        return orderService.makeOrder(productId,userId);
    }
}
