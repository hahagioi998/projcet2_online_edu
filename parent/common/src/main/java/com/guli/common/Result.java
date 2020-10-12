package com.guli.common;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private  Integer code;

    private Boolean success;

    private String message;

    private Map<String , Object> data = new HashMap<>();

    public Result() {
    }



    public static Result ok(){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");

        return r;
    }

    public static Result fail(){
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");

        return r;
    }

    public Result success(Boolean success){

        this.setSuccess(success);
        return this;

    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }


    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }



}
