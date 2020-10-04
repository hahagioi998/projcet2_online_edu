package com.guli.edu_Service.service;

import com.guli.edu_Service.Vo.subjectVo.SubjectNestedVo;
import com.guli.edu_Service.bean.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu_Service.mapper.EduSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xx
 * @since 2020-10-02
 */
public interface EduSubjectService extends IService<EduSubject> {



    List<String> importSubject(MultipartFile file);

    List<SubjectNestedVo> nestedList();

    boolean deleteSubjectById(String id);

    boolean saveOneLevel(EduSubject eduSubject);

    boolean saveTwoLevel(EduSubject eduSubject);
}
