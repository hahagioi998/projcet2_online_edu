package com.guli.edu_Service.controller;


import com.aliyun.oss.OSS;
import com.guli.common.Result;
import com.guli.edu_Service.config.OSSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/edu_Service/oss")
///edu_Service/
@CrossOrigin
public class FileUpdateController {
    @Autowired
    private OSSConfig ossConfig;






    @PostMapping("/fileUpload")
    public Result fileUpload(@RequestParam("file")MultipartFile file) throws Exception{


        String originalFilename = file.getOriginalFilename();
        String name = UUID.randomUUID().toString().replace("-" ,"").substring(0,5)+"headImg" + "_"+ originalFilename;
        InputStream inputStream = file.getInputStream();


//        System.err.println("getEndpoint   "+ossConfig.getEndpoint());
//        System.err.println("getAccessKeyId   "+ossConfig.getAccessKeyId());
//        System.err.println("getAccessKeySecret   "+ossConfig.getAccessKeySecret());
//        System.err.println("getBucketName   "+ossConfig.getBucketName());
//        System.err.println("getBaseUrl   "+ossConfig.getBaseUrl());


//        String endpoint = "oss-cn-beijing.aliyuncs.com";
//        String accessKeyId = "LTAI4G6AToU9k3sP37CcnfjN";
//        String accessKeySecret = "Ak9g2UaGgGigHreJ2kKgKhhxQB7Uf6";
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        String BucketName  = "lingyan";
//        String BaseUrl = "https://lingyan.oss-cn-beijing.aliyuncs.com/";


        OSS o  = ossConfig.getOss();

        o.putObject(ossConfig.getBucketName(), name, inputStream);
        String PicUrl = ossConfig.getBaseUrl()+name;
//        o.shutdown(); //如果关闭 则需要重新进行bean 的初始化

        System.err.println(PicUrl);

        return Result.ok().data("url" , PicUrl).message("图片上传成功");
    }
    @PostMapping("/coverUpload")
    public Result coverUpload(@RequestParam("file")MultipartFile file) throws Exception{


        String originalFilename = file.getOriginalFilename();
        String name =  UUID.randomUUID().toString().replace("-" ,"").substring(0,5) + "_"+originalFilename;
        InputStream inputStream = file.getInputStream();


        OSS o  = ossConfig.getOss();

        o.putObject(ossConfig.getBucketName(), "coverImg/"+name, inputStream);
        String PicUrl = ossConfig.getBaseUrl()+"coverImg/"+name;
        System.err.println(PicUrl);
//        o.shutdown(); //如果关闭 则需要重新进行bean 的初始化

        System.err.println(PicUrl);

        return Result.ok().data("url" , PicUrl).message("图片上传成功");
    }







}
