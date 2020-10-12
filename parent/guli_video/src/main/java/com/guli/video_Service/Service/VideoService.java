package com.guli.video_Service.Service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    String aliVideoUpload(MultipartFile file);

    String deleteVideo(String id) throws ClientException;
}
