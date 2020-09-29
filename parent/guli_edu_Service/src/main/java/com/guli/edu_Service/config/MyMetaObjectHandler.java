package com.guli.edu_Service.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


//自动插入值
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("isDeleted" , 0 , metaObject);

        this.setFieldValByName(  "gmtCreate" ,new Date(), metaObject);
        this.setFieldValByName( "gmtModified" ,new Date(), metaObject);


    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName( "gmtModified" ,new Date(), metaObject);

    }
}
