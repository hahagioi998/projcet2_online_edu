package com.guli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
//@ComponentScan("com.guli.edu_static")
@MapperScan({"com.guli.edu_static.mapper"})
@EnableFeignClients
public class EduStaticApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduStaticApplication.class, args);
	}

}
