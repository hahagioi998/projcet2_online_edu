package com.guli.edu_Service.Vo.addTeacher;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class addTeacherVo implements Serializable {


    private String name;

    private String intro;

    private String career;

    private Integer level;

    private String avatar;

    private Integer sort;
}
