package com.guli.edu_Service.config;


import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootConfiguration
public class MybatisPlusConfig {


    @Bean //逻辑删除
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }



    @Bean  //分页插件
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
