package com.guli.edu_ucService.controller;


import com.guli.common.Result;
import com.guli.edu_ucService.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xx
 * @since 2020-10-08
 */
@RestController
@RequestMapping("/edu_Service/member")
@CrossOrigin

public class UcenterMemberControlleron {

    @Autowired
    UcenterMemberService memberService;


            //统计每一天的人数
    @GetMapping("/countRegistNum/{day}")
    public Result countRegistNum(@PathVariable("day") String day ){


        Integer count = memberService.countRegistNum(day);

        return Result.ok().data("items" , count);
    }



}

