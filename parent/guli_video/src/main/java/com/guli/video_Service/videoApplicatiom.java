package com.guli.video_Service;


import com.guli.video_Service.Utils.ReadConfigValues;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class videoApplicatiom {


    public static void main(String[] args) {

        SpringApplication.run(videoApplicatiom.class , args);

        System.err.println(ReadConfigValues.KEYID);
        System.err.println(ReadConfigValues.KEYSECURIT);



    }
}
