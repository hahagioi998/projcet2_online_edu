package com.guli.edu_static.controller;


import com.guli.common.Result;


import com.guli.edu_static.service.StatisticsDailyService;
import com.guli.edu_static.service.UserCenterFeign;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author xx
 * @since 2020-10-08
 */
@RestController
@RequestMapping("/edu_static/statistics-daily")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    StatisticsDailyService statisticsDailyService;




    @GetMapping("/countRegistNum/{day}")
    public Result countRegistNum(@PathVariable("day") String day ){

        System.err.println("controller 接收到 day " + day);

        Result result = statisticsDailyService.countRegistNum(day );


        return result;
    }


    @GetMapping("/getQueryCondition/{type}/{begin}/{end}")
    public Result getQueryCondition(
            @PathVariable("type") String type,
            @PathVariable("begin") String begin,
            @PathVariable("end") String end
    ){


        Map<String,Object> list = statisticsDailyService.getQueryCondition(type , begin , end);





        return Result.ok().data(list);
    }












}

