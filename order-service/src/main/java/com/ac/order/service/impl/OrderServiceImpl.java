package com.ac.order.service.impl;

import com.ac.order.dao.OrderDao;
import com.ac.order.dto.UserDto;
import com.ac.order.entity.Order;
import com.ac.order.feign.UserServiceClient;
import com.ac.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

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

    //final static String USER_SERVICE_URL="http://127.0.0.1:8010/users/{userId}";

    final static String USER_SERVICE_URL="http://user-service/users/{userId}"; //用服务名来替换IP

    public Order makeOrder(String productId, String userId) {

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

        // 2、生成订单
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCreateTime(new Date());
        order.setPriceFen(1600L);
        order.setUserId(userId);
        order.setUserName(userName);
        order.setProductId(productId);
        order.setOrderNo(UUID.randomUUID().toString());

        // 3、保存数据库
        orderDao.insert(order);

        return order;
    }
}
