//package com.ac.user.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import io.seata.rm.datasource.DataSourceProxy;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
///**
// * @author Alan Chen
// * @description
// * @date 2021/4/19
// */
//@Configuration
//public class DataSoucreConfigure {
//
//    @Bean
//    @Primary
//    public DataSourceProxy dataSourceProxy(DataSourceProperties properties){
//        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//        return new DataSourceProxy(dataSource);
//    }
//}
