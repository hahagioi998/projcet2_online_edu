package com.guli.edu_Service.Vo.ChaptetViedoVo;


import lombok.Data;

import java.io.Serializable;


@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer isFree;
    private String videoSourceId;


}
