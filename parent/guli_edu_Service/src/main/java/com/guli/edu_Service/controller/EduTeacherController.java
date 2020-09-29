package com.guli.edu_Service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.Result;
import com.guli.edu_Service.MyExceptions.MyException;
import com.guli.edu_Service.Vo.addTeacher.addTeacherVo;
import com.guli.edu_Service.Vo.queryVo.queryTeacherVo.teacherForThreeConditionVo;
import com.guli.edu_Service.bean.EduTeacher;
import com.guli.edu_Service.service.EduTeacherService;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xx
 * @since 2020-09-29
 */
@RestController
//@RequestMapping("/teacher")
public class EduTeacherController {


    @Autowired
    private EduTeacherService eduTeacherService;


    //根据id 改讲师
    @PostMapping("/updateTeacherById/{id}")
    public Result updateTeacherById(
            @PathVariable String id,
            @RequestBody addTeacherVo vo
    ) {
        EduTeacher teacher = eduTeacherService.getById(id);
        System.err.println("传入的Vo+++++++++++++++: " + vo);
        System.err.println("teacher+++++++++++++++: " + teacher);

        if (teacher == null) {
            return Result.fail();
        }

        BeanUtils.copyProperties(vo, teacher);
        System.err.println("经过重新靠背后的teacher+++++++++++++++: " + teacher);

        if (!eduTeacherService.updateById(teacher)) {
            return Result.fail();
        }


        return Result.ok();
    }


    //根据ID 查讲师

    @GetMapping("/selectTeacherById/{id}")
    public Result selectTeacherById(
            @PathVariable String id
    ) {


        EduTeacher teacher = eduTeacherService.getById(id);

        if (teacher == null) {
            return Result.fail();
        }

        return Result.ok().data("Teacher", teacher);
    }

    @GetMapping("/listAllTeacher")
    public Result getAllTeacher() {

        List<EduTeacher> list = eduTeacherService.list(null);

        list.forEach(System.out::println);


        return Result.ok().data("list", list);

    }

    //    @DeleteMapping("/{id}")
    @GetMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable("id") String id) {


        boolean b = eduTeacherService.removeById(id);

        System.err.println(("删除结果:=====   " + b));


        return b;
    }


    @GetMapping("/page/{page}/{limit}")
    public Result getPageTeacherList(
            @PathVariable("page") Long page,
            @PathVariable("limit") Long limit
    ) {
        Page<EduTeacher> p = new Page<EduTeacher>(page, limit);

        eduTeacherService.page(p, null);

        long total = p.getTotal();
        List<EduTeacher> list = p.getRecords();


        return Result.ok().data("total", total).data("items", list);


    }


    @PostMapping("/query/{page}/{limit}")   //如果使用requestbody 则前台会传输一json 后台解析为字符串
    public Result queryTeacher(                //且前台必须要通过post 请求 才能生效
                                               @PathVariable("page") Long page,
                                               @PathVariable("limit") Long limit,
                                               @RequestBody(required = false) teacherForThreeConditionVo vo) {

        Page<EduTeacher> p = new Page<>(page, limit);

        QueryWrapper<EduTeacher> wapper = new QueryWrapper<>();
        wapper.equals(vo);

        System.err.println(vo);


        return eduTeacherService.pageListCondition(p, vo);
    }


    @PostMapping("/add")
    public Result addTeacher(
            @RequestBody addTeacherVo vo
    ) {

        System.err.println("vo:===" + vo);

        EduTeacher teacher = new EduTeacher();
        BeanUtils.copyProperties(vo, teacher);
        System.err.println("teacher:===" + teacher);

        boolean save = eduTeacherService.save(teacher);

        if (save == true) {
            return Result.ok();
        } else {
            return Result.fail();
        }


    }


    @GetMapping("/except")  //使用自定义异常
    public void except(String message , Integer code){
        throw new MyException(code,message);

//        return Result.fail().message("")
    }


}

