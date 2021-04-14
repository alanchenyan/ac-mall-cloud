package com.ac.order.feign;

import com.ac.order.constant.ModulePrePath;
import com.ac.order.fallback.ProductFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author Alan Chen
 * @description 产品接口
 * @date 2021/4/10
 */
@FeignClient(name="product-service",fallbackFactory = ProductFeignClientFallbackFactory.class)
public interface ProductServiceClient {

    /**
     * 更新产品销量
     * @param productId
     */
    @PutMapping(ModulePrePath.API+"/products/{productId}")
    void updateSales(@PathVariable("productId") int productId);

    /**
     * 下单扣减库存
     * @param productId
     * @param subCount
     */
    @PutMapping(ModulePrePath.API+"/products/sub_stock/{productId}/{subCount}")
    void subStock(@PathVariable("productId") int productId,@PathVariable("subCount") int subCount);
}
