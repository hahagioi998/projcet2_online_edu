package com.guli.edu_Service.controller.FrontController;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.Result;
import com.guli.edu_Service.Vo.AllCourseInfo.CourseWebVo;
import com.guli.edu_Service.Vo.ChaptetViedoVo.ChapterVo;
import com.guli.edu_Service.bean.EduChapter;
import com.guli.edu_Service.bean.EduCourse;
import com.guli.edu_Service.mapper.EduChapterMapper;
import com.guli.edu_Service.service.EduChapterService;
import com.guli.edu_Service.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/edu_Service/FrontCourse")
@Transactional
@Api(tags="前端的课程管理")
@CrossOrigin
public class FrontEduCourseController {



    @Autowired
    EduCourseService eduCourseService;

    @Autowired
    EduChapterService eduChapterService;

    @GetMapping("/{page}/{limit}")
    public Result getPageCourseListFront(
            @PathVariable("page") Long page,
            @PathVariable("limit") Long limit){

        Page<EduCourse> condition = new Page<>(page, limit);

        HashMap<String ,Object> map  = eduCourseService.getPageCourseListFront(condition);




        return Result.ok().data(map);
    }



    @GetMapping("/selectCourseById/{id}")
    public Result selectCourseById(
            @PathVariable("id") String id ){


        CourseWebVo CourseWebVo = eduCourseService.selectInfoWebById(id);



        List<ChapterVo> chapter = eduChapterService.selectChapterAndVideoInfo(id);

        System.err.println("CourseWebVo  :   "+CourseWebVo);
        System.err.println("chapter  :   "+chapter);


        return Result.ok().data("Course",CourseWebVo).data("chapters",chapter);

    }


}
