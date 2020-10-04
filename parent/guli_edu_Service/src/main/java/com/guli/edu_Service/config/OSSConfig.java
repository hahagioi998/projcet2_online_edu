package com.guli.edu_Service.config;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.Data;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootConfiguration
//@Component
@MapperScan("com.guli.edu_Service.mapper")
@EnableTransactionManagement
@ConfigurationProperties(prefix = "oss" )
@Data
public class OSSConfig {
    String endpoint;
    String accessKeyId;
    String accessKeySecret;
//    @Value("${oss.BucketName}")
    String BucketName;
//    @Value("${oss.BaseUrl}")
    String BaseUrl;


//    @Bean(name = "oss")
    @Bean (name = "getOss")
    public OSS getOss(){


        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }









}
