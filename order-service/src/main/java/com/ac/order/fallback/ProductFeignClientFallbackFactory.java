package com.ac.order.fallback;

import com.ac.order.feign.ProductServiceClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Alan Chen
 * @description
 * @date 2021/4/10
 */
//@Component
//public class ProductFeignClientFallbackFactory implements FallbackFactory<ProductServiceClient> {
//
//    public ProductServiceClient create(Throwable throwable) {
//
//        return new ProductServiceClient() {
//            public void updateSales(int productId) {
//                System.out.println("调用产品更新销量接口失败，记录日志(记录到数据库或消息中间件)，产品："+productId+"需要更新销量");
//            }
//
//            public void subStock(int productId, int subCount) {
//                System.out.println("调用产品更新库存接口失败，记录日志(记录到数据库或消息中间件)，产品："+productId+"需要减库存"+subCount);
//            }
//        };
//    }
//}
