package com.guli.edu_Service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu_Service.MyExceptions.MyException;
import com.guli.edu_Service.Vo.AllCourseInfo.CourseWebVo;
import com.guli.edu_Service.Vo.AllCourseInfo.allCourseInfo;
import com.guli.edu_Service.Vo.ChaptetViedoVo.ChapterVo;
import com.guli.edu_Service.Vo.form.CourseInfoForm;
import com.guli.edu_Service.bean.EduChapter;
import com.guli.edu_Service.bean.EduCourse;
import com.guli.edu_Service.bean.EduCourseDescription;
import com.guli.edu_Service.bean.EduTeacher;
import com.guli.edu_Service.mapper.EduCourseMapper;
import com.guli.edu_Service.service.EduChapterService;
import com.guli.edu_Service.service.EduCourseDescriptionService;
import com.guli.edu_Service.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu_Service.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xx
 * @since 2020-10-04
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {


    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    EduChapterService eduChapterService;

    @Autowired
    EduVideoService eduVideoService;


    @Override
    //需要向两张表中添加数据
    public String insertCourseInfo(CourseInfoForm infoForm) {

        //添加课程基本信息

        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(infoForm, course);
        int flage = baseMapper.insert(course);
        if (flage == 0) {
            //如果课程添加失败则直接抛出运行异常  禁止后续添加课程描述 否则课程描述无法获取 课程id 则为无效值
            throw new MyException(20001, "课程添加失败");

        }

        //添加课程描述
        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(infoForm.getDescription());

        description.setId(course.getId());

        boolean save = eduCourseDescriptionService.save(description);

        System.err.println(" insertCourseInfo: flage : " + flage + "   save :   " + save +" courseId :   " + course.getId() );

        if (save){

        return course.getId();

        }
        return "添加失败";
    }

    @Override
    public CourseInfoForm getCourseInfo(String id) {

        EduCourse eduCourse = baseMapper.selectById(id);

        if (eduCourse == null ){


             throw  new MyException(20001 , "没有该课程的信息");
        }


        CourseInfoForm info = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse , info);

        //差描述
        EduCourseDescription desc = eduCourseDescriptionService.getById(id);

        info.setDescription(desc.getDescription());

        System.err.println("封装之后的info ：  " + info);



        return info;
    }

    @Override
    public boolean updateCourse(CourseInfoForm infoForm) {


        EduCourse eduCourse = new EduCourse();
        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(infoForm ,eduCourse );
        BeanUtils.copyProperties(infoForm ,description );
        int i = baseMapper.updateById(eduCourse);
        if (i <=0){
            throw new MyException(20001 , "修改失败");
        }

        boolean b = eduCourseDescriptionService.updateById(description);

        System.err.println("infoForm:   ==== "+infoForm);
        System.err.println("eduCourse:   ==== "+eduCourse);
        System.err.println("description:   ==== "+description);

        return b;
    }

    @Override
    public List<EduCourse> getAllCourse() {

        List<EduCourse> list = baseMapper.selectList(null);

        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean deleteCourseById(String id) {

        eduChapterService.deleteChapterByCourseId(id);

        eduVideoService.deleteVideoByCourseId(id);

        eduCourseDescriptionService.removeById(id);


        System.err.println("deleteCourseById(String id)  :  " + id );

        int i = baseMapper.deleteById(id);


        return i>0;
    }

    @Override
    public allCourseInfo publishCourseInfo(String id) {


        allCourseInfo info =  baseMapper.publishCourseInfo(id);
        return info;
    }

    @Override
    public HashMap<String, Object> getPageCourseListFront(Page<EduCourse> pageParam) {

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        HashMap<String, Object> map = new HashMap<>();

        baseMapper.selectPage(pageParam , null);

        List<EduCourse> records = pageParam.getRecords();
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
    public CourseWebVo selectInfoWebById(String id) {



        CourseWebVo course =  baseMapper.selectInfoWebById(id);


        return course;
    }


}
