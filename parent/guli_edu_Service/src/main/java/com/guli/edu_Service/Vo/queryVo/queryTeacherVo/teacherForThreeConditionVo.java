package com.guli.edu_Service.Vo.queryVo.queryTeacherVo;

import lombok.Data;

import java.io.Serializable;



@Data
public class teacherForThreeConditionVo implements Serializable {

    private String name;

    private String level;

    private String begin;

    private String end;



}
