package com.guli.video_Service.Controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.guli.common.Result;
import com.guli.video_Service.Service.VideoService;
import com.guli.video_Service.Utils.AliyunVodSDKUtils;
import com.guli.video_Service.Utils.ReadConfigValues;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RequestMapping("/video_Service/video")
@RestController
@CrossOrigin
@Api(tags = "视频服务")
public class videoController {


    @Autowired
    VideoService videoService;







    @PostMapping("/upload")
    public Result AliVideoUpLoad(@RequestParam("file") MultipartFile file ){

        System.err.println("已经进入了upload ： ======");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Cache-Control","no-cache");

        String videoid = videoService.aliVideoUpload(file);


        System.err.println("视频id ： " + videoid);



        return  Result.ok().data("items" , videoid);
    }


    @GetMapping("/hello")
    public Result hello(){
        System.err.println("say hello");

        return Result.ok().message("say hello");
    }

    @DeleteMapping("/deleteVideo/{id}")
    public Result deleteVideo(@PathVariable("id") String id) throws ClientException {

        System.err.println("要删除的id" + id);

        String result = videoService.deleteVideo(id);

        return  Result.ok().message(result);
    }


    //获取视频播放凭证
    @GetMapping("getVideoPlayAuth/{vid}")
    public Result getVideoPlayAuth(
            @PathVariable("vid") String vid) throws ClientException {

        System.err.println("vid +++:   " + vid);

        //获取阿里云存储相关常量
        String accessKeyId = ReadConfigValues.KEYID;
        String accessKeySecret = ReadConfigValues.KEYSECURIT;

        //初始化
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);

        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(vid);

        //响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        //得到播放凭证
        String playAuth = response.getPlayAuth();

        //返回结果

        System.err.println("playAuth:   " + playAuth);


        return Result.ok().data("playAuth",playAuth);
    }











}
//http://localhost:9001/video_Service/video/hello
//http://localhost:9001/video_Service/video/upload