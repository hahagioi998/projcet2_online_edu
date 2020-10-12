package com.guli.edu_static.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootConfiguration
@MapperScan("com.guli.edu_static.mapper")
public class myconfig {
}
