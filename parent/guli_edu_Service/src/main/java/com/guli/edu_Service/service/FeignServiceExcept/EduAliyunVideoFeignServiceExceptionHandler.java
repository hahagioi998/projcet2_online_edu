package com.guli.edu_Service.service.FeignServiceExcept;

import com.aliyuncs.exceptions.ClientException;
import com.guli.common.Result;
import com.guli.edu_Service.service.EduAliyunVideoFeignService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

public class EduAliyunVideoFeignServiceExceptionHandler implements EduAliyunVideoFeignService {
    @Override
    public Result deleteVideo(@PathVariable("id") String id) throws ClientException {
        return Result.fail().message("删除失败");
    }

    @Override
    public Result AliVideoUpLoad(MultipartFile file) {
        return Result.fail().message("上传失败");
    }


}
