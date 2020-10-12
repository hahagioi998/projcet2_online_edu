package com.guli.edu_Service.Vo.AllCourseInfo;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "课程发布信息")
@Data
public class allCourseInfo implements Serializable {

    private static final long serialVersionUID = 1L;
//    SELECT c.title , c.price , c.cover , cd.description , t.`name` ,s1.title t1 , s2.title t2

    private String id;

    private String title;

    private String cover;

    private String price;//只用于显示

    private String description;

    private String teacherName;

    private String levelOne;

    private String levelTwo;

}
