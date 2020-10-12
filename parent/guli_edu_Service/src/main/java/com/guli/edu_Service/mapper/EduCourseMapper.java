package com.guli.edu_Service.mapper;

import com.guli.edu_Service.Vo.AllCourseInfo.CourseWebVo;
import com.guli.edu_Service.Vo.AllCourseInfo.allCourseInfo;
import com.guli.edu_Service.bean.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xx
 * @since 2020-10-04
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    allCourseInfo publishCourseInfo(String id);

    CourseWebVo selectInfoWebById(String id);
}
