package com.mybatisplus.stu.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class mymeraobjecthandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("createdata" , new Date() ,metaObject );
        this.setFieldValByName("updatedata" , new Date() ,metaObject );
        this.setFieldValByName("version" , 1,metaObject );
        this.setFieldValByName("status" , 0 ,metaObject );
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedata" , new Date() ,metaObject );

    }
}
