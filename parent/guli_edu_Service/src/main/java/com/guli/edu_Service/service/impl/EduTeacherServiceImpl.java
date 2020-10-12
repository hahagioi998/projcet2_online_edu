package com.guli.edu_Service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.Result;
import com.guli.edu_Service.Vo.queryVo.queryTeacherVo.teacherForThreeConditionVo;
import com.guli.edu_Service.bean.EduCourse;
import com.guli.edu_Service.bean.EduTeacher;
import com.guli.edu_Service.mapper.EduTeacherMapper;
import com.guli.edu_Service.service.EduCourseService;
import com.guli.edu_Service.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author xx
 * @since 2020-09-29
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {



    @Autowired
    EduCourseService eduCourseService;


    @Override
    public Result pageListCondition(Page<EduTeacher> p, teacherForThreeConditionVo vo) {

        if (StringUtils.isEmpty(vo)){
            return Result.ok().data("items" , baseMapper.selectPage(p ,null).getRecords());
        }
        QueryWrapper<EduTeacher> wapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(vo.getName())){
            wapper.like("name" ,vo.getName() );
        }

        if (!StringUtils.isEmpty(vo.getLevel())){
            wapper.eq("level" , vo.getLevel());

        }

        if (!StringUtils.isEmpty(vo.getBegin())){
            wapper.gt("gmt_create" , vo.getBegin());
        }

        if (!StringUtils.isEmpty(vo.getEnd())){
            wapper.lt("gmt_create" , vo.getEnd());

        }

        baseMapper.selectPage(p,wapper);

        return Result.ok().data("total" , p.getTotal()).data("items" , p.getRecords());


    }

    @Override
    public Map<String, Object> getPageTeacherListFront(Page<EduTeacher> condition) {

        HashMap<String, Object> map = new HashMap<>();

        baseMapper.selectPage(condition, null);

        Page<EduTeacher> pageParam = condition;

        List<EduTeacher> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);


        return map;
    }

    @Override
    public List<EduCourse> getCourseByTeacherId(String id) {

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id" , id);


        List<EduCourse> list = eduCourseService.list(wrapper);


        return list;
    }


}
