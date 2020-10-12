package com.guli.edu_Service.service;

import com.guli.edu_Service.Vo.ChaptetViedoVo.ChapterVo;
import com.guli.edu_Service.bean.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xx
 * @since 2020-10-05
 */
public interface EduChapterService extends IService<EduChapter> {
    //根据课程id 删除章节
    void deleteChapterByCourseId(String id);

    List<ChapterVo> selectChapterVideoListByCourseId(String id);

    Boolean deleteChapterByid(String id);

    List<ChapterVo> selectChapterAndVideoInfo(String id);
}
