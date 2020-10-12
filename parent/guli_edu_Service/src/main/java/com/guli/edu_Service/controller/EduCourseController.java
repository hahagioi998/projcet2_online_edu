package com.guli.edu_Service.controller;


import com.guli.common.Result;
import com.guli.edu_Service.Vo.AllCourseInfo.allCourseInfo;
import com.guli.edu_Service.Vo.form.CourseInfoForm;
import com.guli.edu_Service.bean.EduCourse;
import com.guli.edu_Service.bean.EduCourseDescription;
import com.guli.edu_Service.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @since 2020-10-04
 */
@RestController
@RequestMapping("/edu_Service/educourse")
@Transactional
@Api(tags="课程管理")
@CrossOrigin
public class EduCourseController {

    @Autowired
    EduCourseService eduCourseService;



    //添加课程信息
    @ApiOperation(value = "新增课程")
    @PostMapping("/save-course-info")
    public Result addCourseInfo(@RequestBody CourseInfoForm infoForm){

        String  courseId = eduCourseService.insertCourseInfo(infoForm);

        return Result.ok().data("courseId" , courseId);


    }

    @ApiOperation(value = "查询课程")
    @GetMapping("/getCourseById/{id}")
    public Result getCourseById(@PathVariable("id") String id){

//        EduCourse course = eduCourseService.getById(id);
        CourseInfoForm courseInfo = eduCourseService.getCourseInfo(id);

        return Result.ok().data("courseInfo" , courseInfo);
    }

    @ApiOperation(value = "修改课程")
    @PostMapping("/updateCourse")
    public Result updateCourseById(
            @RequestBody CourseInfoForm infoForm){

//        EduCourse course = eduCourseService.getById(id);
//        CourseInfoForm courseInfo = eduCourseService.getCourseInfo(id);

//        return Result.ok().data("courseInfo" , courseInfo);



        boolean flage = eduCourseService.updateCourse(infoForm);

        if (!flage){
            return  Result.fail().message("修改失败,请检查后重试");
        }


        return Result.ok().message("修改成功");

    }

    @ApiOperation(value = "查询全部课程列表")
    @GetMapping("/getAllCourse")
    public Result getAllCourse(){


        List<EduCourse> list = eduCourseService.getAllCourse();

        System.err.println("getAllCourse ++++ : " + list);


        return Result.ok().data("list", list);
    }


    @ApiOperation(value = "删除课程包括其下面的所有章节")
    @DeleteMapping("/deleteCourseById/{id}")
    public Result deleteCourseById(
            @PathVariable("id") String id    ) {

            Boolean flage = eduCourseService.deleteCourseById(id);

//        eduCourseService.listByMap()

            if (flage){
                return Result.ok().message("删除成功！");

            }else {
                return Result.fail().message("删除失败！");
            }

    }

    @ApiOperation(value = "发布的最后一步，所有数据的回显")
    @GetMapping("/publishCourseInfo/{id}")

    public Result publishCourseInfo(@PathVariable("id") String id){


        allCourseInfo info =   eduCourseService.publishCourseInfo(id);


        return Result.ok().data("items" , info);
    }

    @ApiOperation(value = "发布的最后一步，所有数据的回显")
    @GetMapping("/publishCourseFinish/{id}")
    public Result publishCourseFinish(@PathVariable("id") String id){

//        eduCourseService.
        EduCourse course = new EduCourse();

        course.setId(id);
        course.setStatus("normal");
        boolean flage = eduCourseService.updateById(course);

        if (!flage){ return  Result.fail();}


        return Result.ok();
    }







}

