package com.ac.product.service.impl;

import com.ac.product.dao.ProductDao;
import com.ac.product.entiy.Product;
import com.ac.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alan Chen
 * @description 产品
 * @date 2021/4/13
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductDao productDao;

    /**
     * 下单扣减库存
     * @param productId
     * @param subCount
     */
    @Transactional(rollbackFor = Exception.class)
    public void subStock(int productId, int subCount) {
        Product product = productDao.selectById(productId);
        int newStock = product.getStock()-subCount;
        product.setStock(newStock);

        productDao.updateById(product);

        //制造异常测试回滚
        int a = 1/0;

        System.out.println("下单扣减库存成功!");
    }
}
