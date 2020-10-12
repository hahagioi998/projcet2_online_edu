package com.guli.edu_static.service.FeignExceptionHandler;

import com.guli.common.Result;
import com.guli.edu_static.service.UserCenterFeign;


public class UserCenterFeignExceptionHandler implements UserCenterFeign {

    public Result countRegistNum(String day) {
        return Result.fail().message("调用Usercenter接口失败");
    }
}
