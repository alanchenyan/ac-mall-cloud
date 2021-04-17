//package com.ac.order.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import io.seata.rm.datasource.DataSourceProxy;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
///**
// * @author Alan Chen
// * @description
// * @date 2021/4/17
// */
//@Configuration
//@ConditionalOnClass(HikariDataSource.class)
//public class DataSourceProxyConfig {
//
//    /**
//     * 原生datasource前缀取"spring.datasource"
//     *
//     * @return
//     */
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource hikariDataSource() {
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        return hikariDataSource;
//    }
//
//    /**
//     * 构造datasource代理对象，替换原来的datasource
//     *
//     * @param hikariDataSource
//     * @return
//     */
//    @Primary
//    @Bean("dataSource")
//    public DataSourceProxy dataSourceProxy(DataSource hikariDataSource) {
//        return new DataSourceProxy(hikariDataSource);
//    }
//}
