package com.guli.video_Service.Utils;


import org.springframework.beans.factory.InitializingBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReadConfigValues implements InitializingBean {

    @Value("${aliyun.vod.file.keyid}")
    private String id ;


    @Value("${aliyun.vod.file.keysecret}")
    private String secret;

    public static String KEYID;


    public static String KEYSECURIT;


    @Override
    public void afterPropertiesSet() throws Exception {

        KEYID = id;
        KEYSECURIT = secret;



    }
}
