package com.ac.product.service;

/**
 * @author Alan Chen
 * @description 产品
 * @date 2021/4/13
 */
public interface IProductService {

    /**
     * 下单扣减库存
     * @param productId
     * @param subCount
     */
    void subStock(int productId,int subCount);
}
