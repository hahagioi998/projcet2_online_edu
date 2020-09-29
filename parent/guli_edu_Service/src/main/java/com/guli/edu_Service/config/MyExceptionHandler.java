package com.guli.edu_Service.config;


import com.guli.common.Result;
import com.guli.edu_Service.MyExceptions.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result ServiceError(Exception e){
        e.printStackTrace();


        return Result.fail().message("抱歉服务器开小差了呢");


    }

    @ResponseBody
    @ExceptionHandler(MyException.class)
    public Result myExceptionTest(MyException e){
        e.printStackTrace();

        return Result.fail().message(e.getMessage()).code(e.getCode());
    }



}
