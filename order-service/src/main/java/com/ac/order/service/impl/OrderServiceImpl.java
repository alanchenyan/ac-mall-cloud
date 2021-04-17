package com.ac.order.service.impl;

import com.ac.order.dao.OrderDao;
import com.ac.order.dto.UserDto;
import com.ac.order.entity.ProductOrder;
import com.ac.order.feign.ProductServiceClient;
import com.ac.order.feign.UserServiceClient;
import com.ac.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author Alan Chen
 * @description
 * @date 2020/10/15
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserServiceClient userServiceClient;

    @Autowired
    ProductServiceClient productServiceClient;

    //final static String USER_SERVICE_URL="http://127.0.0.1:8010/users/{userId}";

    final static String USER_SERVICE_URL="http://user-service/users/{userId}"; //用服务名来替换IP


    @GlobalTransactional(rollbackFor = Exception.class)
    // @Transactional(rollbackFor = Exception.class)
    public ProductOrder makeOrder(int productId, int userId) {

        /**
         * RestTemplate是java创造出来的，在java能够访问到网络资源的包是java.net.URLConnenction/Socket
         * RestTemplate是对URLConnenction的封装
         * apache--HttpClient 也是对URLConnenction/HttpURLConnenction的封装
         * oKHttp 也封装了URLConnenction
         * netty/rpc/grpc/thirt/tomcat
         */

        // 1、根据用户ID调用用户服务接口数据，查询用户的名字
        //UserDto userDto = restTemplate.getForObject(USER_SERVICE_URL,UserDto.class,userId);

        //换成OpenFeign
        UserDto userDto = userServiceClient.getUser(userId);

        String userName=userDto.getUserName();

        double amount = 100;

        // 2、生成订单
        ProductOrder productOrder = new ProductOrder();
        productOrder.setAmount(amount);
        productOrder.setUserId(userId);
        productOrder.setUserName(userName);
        productOrder.setProductId(productId);

        // 3、保存数据库
        orderDao.insert(productOrder);

        // 4、更新产品销量
        productServiceClient.updateSales(productId);

        // 5、下单减库存
        productServiceClient.subStock(productId,1);

        // 6、下单扣减用户余额
        userServiceClient.deductionBalance(userId,amount);

        return productOrder;
    }
}
