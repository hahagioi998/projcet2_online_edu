package com.mybatisplus.stu.bean;



import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
public class User {
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createdata;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedata;

    @TableField(fill = FieldFill.INSERT)
    @Version
    private int version;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer status ;

    public User(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User() {
    }
}
