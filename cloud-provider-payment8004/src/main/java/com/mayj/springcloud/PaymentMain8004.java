package com.mayj.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Mayj
 * @Date 2020/10/19 20:21
 **/
@SpringBootApplication
@EnableDiscoveryClient//如果服务使用consul或者zk使用这个注解 向注册中心注册服务
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }
}


