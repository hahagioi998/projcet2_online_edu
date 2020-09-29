package com.guli.edu_Service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.Result;
import com.guli.edu_Service.Vo.queryVo.queryTeacherVo.teacherForThreeConditionVo;
import com.guli.edu_Service.bean.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author xx
 * @since 2020-09-29
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Result pageListCondition(Page<EduTeacher> p, teacherForThreeConditionVo vo);
}
