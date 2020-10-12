package com.guli.edu_static.service;


import com.guli.common.Result;


import com.guli.edu_static.service.FeignExceptionHandler.UserCenterFeignExceptionHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "EDU-UC",fallback = UserCenterFeignExceptionHandler.class)
public interface UserCenterFeign {



    @GetMapping("/edu_Service/member/countRegistNum/{day}")
    public Result countRegistNum(@PathVariable("day") String day );




}
