package com.guli.edu_Service.service;

import com.guli.edu_Service.Vo.ChaptetViedoVo.VideoVo;
import com.guli.edu_Service.bean.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author xx
 * @since 2020-10-05
 */
public interface EduVideoService extends IService<EduVideo> {

    void deleteVideoByCourseId(String id);

    List<VideoVo> selectVideoByChapterId(String chapterId);
}
