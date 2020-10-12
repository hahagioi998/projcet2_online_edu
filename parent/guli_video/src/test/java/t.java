//
//
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
//        import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
//
///**
// * 删除视频
// * @param client 发送请求客户端
// * @return DeleteVideoResponse 删除视频响应数据
// * @throws Exception
// */
//
//
//

///*请求示例*/
//public static void main(String[] argv) {
//        DefaultAcsClient client = initVodClient("<Your AccessKeyId>", "<Your AccessKeySecret>");
//        DeleteVideoResponse response = new DeleteVideoResponse();
//        try {
//        response = deleteVideo(client);
//        } catch (Exception e) {
//        System.out.print("ErrorMessage = " + e.getLocalizedMessage());
//        }
//        System.out.print("RequestId = " + response.getRequestId() + "\n");
//        }
//
//
//
//
//
//
//
//
//        }