package com.guli.edu_Service.controller;


import com.aliyuncs.exceptions.ClientException;
import com.guli.common.Result;
import com.guli.edu_Service.service.EduAliyunVideoFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/edu_Service/eduvideo")
@CrossOrigin
public class EduAliyunVideoController {

    @Autowired
    EduAliyunVideoFeignService eduAliyunVideoFeignService;



    @DeleteMapping("/delMovieById/{id}")
    public Result delMovieById(@PathVariable("id") String id) throws ClientException {



        System.err.println("Feign delMovieById 开始");


        eduAliyunVideoFeignService.deleteVideo(id);

        System.err.println("Feign delMovieById 结束");



        return Result.ok().message("视频删除成功");
    }



    @PostMapping("/video_Service/video/upload")
    public Result AliVideoUpLoad(@RequestParam("file") MultipartFile file ){
        System.err.println("Feign AliVideoUpLoad 开始");


        Result result = eduAliyunVideoFeignService.AliVideoUpLoad(file);

        System.err.println("Feign AliVideoUpLoad 结束");


        return result.message("视频上传成功");
    }







}
