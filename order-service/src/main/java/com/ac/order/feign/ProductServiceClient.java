package com.ac.order.feign;

import com.ac.order.constant.ModulePrePath;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author Alan Chen
 * @description 产品接口
 * @date 2021/4/10
 */
@FeignClient("product-service")
public interface ProductServiceClient {

    /**
     * 更新产品销量
     * @param productId
     */
    @PutMapping(ModulePrePath.API+"/products/{productId}")
    void updateSales(@PathVariable("productId") String productId);
}
