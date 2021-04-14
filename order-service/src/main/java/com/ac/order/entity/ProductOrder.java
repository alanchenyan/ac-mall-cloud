package com.ac.order.entity;

import lombok.Data;

/**
 * @author Alan Chen
 * @description
 * @date 2020/10/15
 */
@Data
public class ProductOrder {

    private int id;

    private double amount;

    private int userId;

    private String userName;

    private int productId;
}
