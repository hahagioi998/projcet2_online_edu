package com.guli.edu_Service.service;


import com.aliyuncs.exceptions.ClientException;
import com.guli.common.Result;
import com.guli.edu_Service.service.FeignServiceExcept.EduAliyunVideoFeignServiceExceptionHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "EDUVIDEO",fallback = EduAliyunVideoFeignServiceExceptionHandler.class)
public interface EduAliyunVideoFeignService {


    @DeleteMapping("/video_Service/video/deleteVideo/{id}")
    public Result deleteVideo(@PathVariable("id") String id) throws ClientException  ;


    @PostMapping("/video_Service/video/upload")
    public Result AliVideoUpLoad(@RequestParam("file") MultipartFile file );




}
