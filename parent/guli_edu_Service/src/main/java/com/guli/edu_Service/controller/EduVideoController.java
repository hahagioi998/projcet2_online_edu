package com.guli.edu_Service.controller;


import com.guli.common.Result;
import com.guli.edu_Service.Vo.ChaptetViedoVo.UpdateVideoVo;
import com.guli.edu_Service.bean.EduVideo;
import com.guli.edu_Service.service.EduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author xx
 * @since 2020-10-05
 */
@RestController
@RequestMapping("/edu_Service/eduvideo")
@CrossOrigin
@Transactional
@Api(tags = "更改Video 小结的controller")
public class EduVideoController {


    @Autowired
    EduVideoService eduVideoService;


    //添加
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody UpdateVideoVo vo) {

        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(vo, eduVideo);

        boolean save = eduVideoService.save(eduVideo);

        if (!save) {
            return Result.fail();
        }
        return Result.ok();
    }


    //查询回显

    @GetMapping("/getVideoById/{id}")
    public Result getVideoById(@PathVariable("id") String id) {

        EduVideo video = eduVideoService.getById(id);

        UpdateVideoVo vo = new UpdateVideoVo();

        BeanUtils.copyProperties(video, vo);

        if (StringUtils.isEmpty(vo)) {
            return Result.fail();
        }
        return Result.ok().data("items", vo);


    }


    //删除

    @DeleteMapping("deleteVideoById/{id}")
    public Result deleteVideoById(@PathVariable("id") String id) {

        boolean flage = eduVideoService.removeById(id);

        if (!flage) {
            return Result.fail();
        }


        return Result.ok();
    }

    @PostMapping("/updateVideo")
    public Result updateVideo(@RequestBody EduVideo vo){
        boolean flage = eduVideoService.updateById(vo);

        if (flage){
            return  Result.ok();
        }
        return Result.fail();
    }


}

