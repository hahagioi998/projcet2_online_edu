//package com.guli.video;
//
//
//
//import java.util.HashMap;
//import java.util.Map;
///**
// * 视频点播OpenAPI调用示例
// * 以GetVideoPlayAuth接口为例，其他接口请替换相应接口名称及私有参数
// */
//
//
///**
// * aliyun.oss.file.endpoint=oss-cn-beijing.aliyuncs.com
// * aliyun.oss.file.keyid=LTAIq6nIPY09VROj
// * aliyun.oss.file.keysecret=FQ7UcixT9wEqMv9F35nORPqKr8XkTF
// * #bucket可以在控制台创建，也可以使用java代码创建
// * aliyun.oss.file.bucketname=edu-teacher1111
// */
//public class Main {
//
//    public static void main(String[] args) throws Exception {
//        //生成私有参数，不同API需要修改
//        Map<String, String> privateParams = generatePrivateParamters();
//        //生成公共参数，不需要修改
//        Map<String, String> publicParams = AliyunVodAPIUtils.generatePublicParamters();
//        //生成OpenAPI地址，不需要修改
//        String URL = AliyunVodAPIUtils.generateOpenAPIURL(publicParams, privateParams);
//        //发送HTTP GET 请求
//        AliyunVodAPIUtils.httpGet(URL);
//    }
//    /**
//     * 生成视频点播OpenAPI私有参数
//     * 不同API需要修改此方法中的参数
//     * @return
//     */
//    private static Map<String, String> generatePrivateParamters() {
//        // 接口私有参数列表, 不同API请替换相应参数
//        Map<String, String> privateParams = new HashMap<>();
//        // 视频ID
//        privateParams.put("VideoId","你上传至视频点播的视频id");
//        // API名称
//        privateParams.put("Action", "GetVideoPlayAuth");
//        return privateParams;
//    }
//}