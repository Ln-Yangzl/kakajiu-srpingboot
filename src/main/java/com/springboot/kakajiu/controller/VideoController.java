package com.springboot.kakajiu.controller;

import com.springboot.kakajiu.service.VideoInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zlyang
 * date: 2021-10-30
 * discription: controller for videos
 */
@RestController
public class VideoController {

    @Resource
    VideoInfo videoInfo;

    @GetMapping("/test")
    public String connectionTest(){
        return "connection success!";
    }

    @GetMapping("/api/frontpage")
    public Object frontpageVideos(){
        return videoInfo.getClassifiedVideos();
    }

}
