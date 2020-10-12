package com.guli.edu_Service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu_Service.Vo.AllCourseInfo.CourseWebVo;
import com.guli.edu_Service.Vo.AllCourseInfo.allCourseInfo;
import com.guli.edu_Service.Vo.ChaptetViedoVo.ChapterVo;
import com.guli.edu_Service.Vo.form.CourseInfoForm;
import com.guli.edu_Service.bean.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xx
 * @since 2020-10-04
 */
public interface EduCourseService extends IService<EduCourse> {

    String insertCourseInfo(CourseInfoForm infoForm);

    CourseInfoForm getCourseInfo(String id);

    boolean updateCourse(CourseInfoForm infoForm);

    List<EduCourse> getAllCourse();

    Boolean deleteCourseById(String id);

    allCourseInfo publishCourseInfo(String id);

    HashMap<String, Object> getPageCourseListFront(Page<EduCourse> condition);

    CourseWebVo selectInfoWebById(String id);


}
