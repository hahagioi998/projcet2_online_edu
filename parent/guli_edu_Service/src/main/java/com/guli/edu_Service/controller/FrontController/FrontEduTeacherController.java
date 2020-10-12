package com.guli.edu_Service.controller.FrontController;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.Result;
import com.guli.edu_Service.bean.EduCourse;
import com.guli.edu_Service.bean.EduTeacher;
import com.guli.edu_Service.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/edu_Service/FrontTeacher")
@CrossOrigin
@Transactional
public class FrontEduTeacherController {

    @Autowired
    EduTeacherService eduTeacherService;


    //index 的分页查询
    @GetMapping("/page/{page}/{limit}")
    public Result getPageTeacherListFront(
            @PathVariable("page") Long page,
            @PathVariable("limit") Long limit){

        Page<EduTeacher> condition = new Page<>(page, limit);

        Map<String , Object> map = eduTeacherService.getPageTeacherListFront(condition);






        return Result.ok().data(map);
    }

    //查询讲师的详情 id

    @GetMapping("/selectTeacherById/{id}")
    public Result selectTeacherById(
            @PathVariable String id ){



        EduTeacher teacher = eduTeacherService.getById(id);

        List<EduCourse> list = eduTeacherService.getCourseByTeacherId(id);


        return  Result.ok().data("teacher" , teacher).data("course" , list);

    }







}
