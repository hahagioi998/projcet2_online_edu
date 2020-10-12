package com.guli.edu_Service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.common.Result;
import com.guli.edu_Service.MyExceptions.MyException;
import com.guli.edu_Service.Vo.ChaptetViedoVo.ChapterVo;
import com.guli.edu_Service.Vo.ChaptetViedoVo.VideoVo;
import com.guli.edu_Service.bean.EduChapter;
import com.guli.edu_Service.bean.EduVideo;
import com.guli.edu_Service.mapper.EduChapterMapper;
import com.guli.edu_Service.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu_Service.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xx
 * @since 2020-10-05
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService eduVideoService;

    @Override
    public void deleteChapterByCourseId(String id) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();

        wrapper.eq("course_id" , id);

        baseMapper.delete(wrapper);

    }

    @Override
    public List<ChapterVo> selectChapterVideoListByCourseId(String id) {

        List<ChapterVo> chapterList = new ArrayList<>();

        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id" , id);
        List<EduChapter> chapters = baseMapper.selectList(wrapper);
        for (EduChapter c : chapters) {
            ChapterVo vo = new ChapterVo();
            BeanUtils.copyProperties(c , vo);
            String chapterId = vo.getId();
            //通过chapterid 将各自小结塞到章节下
            vo.setChildren( eduVideoService.selectVideoByChapterId(chapterId));

            chapterList.add(vo);
        }




        return chapterList;
    }

    @Override
    public Boolean deleteChapterByid(String id) {


        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id" , id);

        int count = eduVideoService.count(wrapper);

        if (count>0){
            throw  new MyException(20001 , "本章节还存在小结，请先删除小结再重试");
        }

        return baseMapper.deleteById(id) > 0;

    }

    @Override
    public List<ChapterVo> selectChapterAndVideoInfo(String id) {

        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id" , id);


        ArrayList<ChapterVo> list = new ArrayList<>();

        List<EduChapter> chapters = baseMapper.selectList(wrapper);
        for (EduChapter c : chapters) {

            System.err.println("C ===: " +c);

            ChapterVo vo = new ChapterVo();

            BeanUtils.copyProperties(c , vo);
            QueryWrapper<EduVideo> videowrapper = new QueryWrapper<>();
            videowrapper.eq("chapter_id" , c.getId());

            List<EduVideo> videos = eduVideoService.list(videowrapper);

            for (EduVideo v : videos) {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(v,videoVo);
                vo.getChildren().add(videoVo);
            }

            list.add(vo);


        }


        return list;
    }


}
