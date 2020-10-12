package com.guli.edu_Service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu_Service.Vo.ChaptetViedoVo.VideoVo;
import com.guli.edu_Service.bean.EduVideo;
import com.guli.edu_Service.mapper.EduVideoMapper;
import com.guli.edu_Service.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author xx
 * @since 2020-10-05
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public void deleteVideoByCourseId(String id) {

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id" , id);
        baseMapper.delete(wrapper);

    }

    @Override
    public List<VideoVo> selectVideoByChapterId(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id" , chapterId);
        List<VideoVo> list = new ArrayList<>();

        List<EduVideo> videoList = baseMapper.selectList(wrapper);
        for (EduVideo t : videoList) {
            VideoVo vo = new VideoVo();
            BeanUtils.copyProperties(t , vo);
            list.add(vo);

        }
        return list;
    }
}
