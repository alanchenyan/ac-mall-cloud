package com.ac.product.api;

import com.ac.product.constant.ModulePrePath;
import com.ac.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alan Chen
 * @description 产品API
 * @date 2021/4/10
 */
@RestController
@RequestMapping(ModulePrePath.API+"/products")
public class ProductApi {

    @Autowired
    IProductService productService;

    /**
     * 更新销量
     * @param productId
     */
    @PutMapping("/{productId}")
    public void updateSales(@PathVariable String productId){
        System.out.println("商品："+productId+"更新销量数");
    }

    /**
     * 下单扣减库存
     * @param productId
     * @param subCount
     */
    @PutMapping("/sub_stock/{productId}/{subCount}")
    public void subStock(@PathVariable int productId,@PathVariable int subCount){
        productService.subStock(productId,subCount);
    }
}
