package com.ac.product.api;

import com.ac.product.constant.ModulePrePath;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alan Chen
 * @description 产品API
 * @date 2021/4/10
 */
@RestController
@RequestMapping(ModulePrePath.API+"/products")
public class ProductApi {

    @PutMapping("/{productId}")
    public void updateSales(@PathVariable String productId){
        System.out.println("商品："+productId+"更新销量数");
    }
}
