package com.guli.common;


import lombok.Data;

@Data
public class R<T> {

    private  Integer code;

    private Boolean success;

    private String message;

    private T data;

    public static R ok(){
        R<Object> r = new R<>();
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        r.setSuccess(true);

        return r;

    }

    public static<T> R<T> ok(T data){
        R<T> r = new R<>();
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        r.setSuccess(true);
        r.setData(data);
        return r;

    }

    public static<T> R fail(){
        R<Object> r = new R<>();
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");
        r.setSuccess(false);


        return r;
    }





}
