package com.guli.edu_Service.controller;


import com.guli.common.Result;
import com.guli.edu_Service.Vo.subjectVo.SubjectNestedVo;
import com.guli.edu_Service.bean.EduSubject;
import com.guli.edu_Service.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author xx
 * @since 2020-10-02
 */
@RestController
@RequestMapping("/edu_Service/edu-subject")
@Transactional
@CrossOrigin
public class EduSubjectController {


    @Autowired
    private EduSubjectService eduSubjectService;


    @PostMapping("/import")
    public Result importExcelSubject(@RequestParam("file") MultipartFile file) {

        List<String> strings = eduSubjectService.importSubject(file);
        System.err.println(strings);

        if (strings.size() == 0) {

            return Result.ok();

        } else {
            return Result.fail().data("msgList", strings);
        }

    }

    @GetMapping("/subjectTree")
    public Result nestedList() {


        List<SubjectNestedVo> subjectNestedVoList = eduSubjectService.nestedList();

        return Result.ok().data("items", subjectNestedVoList);
    }


    @DeleteMapping("/{id}")
    public Result deleteSubjectId(
            @PathVariable("id") String id
    ) {
        boolean b = eduSubjectService.deleteSubjectById(id);

        System.err.println("deleteSubjectId   boolean  b :   " + b);

        if (b == true) {

            return Result.ok();
        }

            return Result.fail();


    }


    //添加一级分类

    @RequestMapping("/addOneLevel")
    public Result addOneLevel(@RequestBody EduSubject eduSubject) {


        boolean flage = eduSubjectService.saveOneLevel(eduSubject);

        if (flage) {
            return Result.ok();
        } else {
            return Result.fail();

        }


    }


    @RequestMapping("/addTwoLevel")
    public Result addTwoLevel(@RequestBody EduSubject eduSubject) {

        boolean flage = eduSubjectService.saveTwoLevel(eduSubject);

        if (flage) {
            return Result.ok();
        }
        return Result.fail();


    }


}

