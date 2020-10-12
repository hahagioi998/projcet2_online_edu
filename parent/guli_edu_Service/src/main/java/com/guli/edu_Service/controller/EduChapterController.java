package com.guli.edu_Service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.common.Result;
import com.guli.edu_Service.MyExceptions.MyException;
import com.guli.edu_Service.Vo.ChaptetViedoVo.ChapterVo;
import com.guli.edu_Service.Vo.ChaptetViedoVo.UpdateChapterVo;
import com.guli.edu_Service.bean.EduChapter;
import com.guli.edu_Service.service.EduChapterService;
import com.guli.edu_Service.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xx
 * @since 2020-10-05
 */
@RestController
@RequestMapping("/edu_Service/educhapter")
@CrossOrigin
@Transactional
@Api(tags = "查询章节")
public class EduChapterController {

    @Autowired
    EduChapterService eduChapterService;

    @Autowired
    EduVideoService eduVideoService;


    @GetMapping("/getChapterVideoByCourseId/{id}")
    @ApiOperation("查询章节以及小节")
    public Result getChapterVideoByCourseId(
            @PathVariable("id") String id
    ) {


        List<ChapterVo> chapterList = eduChapterService.selectChapterVideoListByCourseId(id);

        for (ChapterVo vo : chapterList) {
            System.err.println(vo);
        }
        return Result.ok().data("items", chapterList);
    }


    @ApiOperation("添加章节")
    @PostMapping("/addChaper")
    public Result addChaper(
            @RequestBody EduChapter chapter) {

        boolean flage = eduChapterService.save(chapter);

        if (flage) {
            return Result.ok();
        }
        return Result.fail();


    }

    @ApiOperation("删除章节")
    @DeleteMapping("/deleteChapter/{id}")
    public Result deleteChapter(@PathVariable("id") String id) {

        Boolean flage = eduChapterService.deleteChapterByid(id);

        if (flage) {
            return Result.ok();
        }

        return Result.fail();


    }


    @ApiOperation("修改章节")
    @PostMapping("/updateChapter")
    public Result updateChapter(
            @RequestBody UpdateChapterVo vo) {

        EduChapter chapter = new EduChapter();
        BeanUtils.copyProperties(vo , chapter);

        boolean b = eduChapterService.updateById(chapter);
        if (b) {
            return Result.ok();
        }
        return Result.fail();

    }


    @ApiOperation("根据id查询chapter")
    @GetMapping("/selectChapterById/{id}")
    public Result selectChapterById ( @PathVariable("id") String id){


        EduChapter chapter = eduChapterService.getById(id);

        UpdateChapterVo vo = new UpdateChapterVo();

        BeanUtils.copyProperties(chapter , vo);

        return Result.ok().data("items" , vo);
    }


}

