package com.ac.order.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Alan Chen
 * @description
 * @date 2020/10/15
 */
@Data
public class Order {

    private String id;

    private String orderNo;

    private Long priceFen;

    private String img;

    private Date createTime;

    private String userId;

    private String userName;

    private String productId;
}
