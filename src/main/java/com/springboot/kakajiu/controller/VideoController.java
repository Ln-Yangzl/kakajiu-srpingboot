package com.springboot.kakajiu.controller;

import com.springboot.kakajiu.entity.SimpleResponseInfo;
import com.springboot.kakajiu.service.VideoInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/api/getvideourl")
    public Object getVideoUrl(@RequestParam(value="videoname") String videoName){
        if(videoName.isBlank()){
            SimpleResponseInfo responseInfo = new SimpleResponseInfo();
            responseInfo.setStatus(2);
            responseInfo.setError("接收到videoname参数为空");
            return responseInfo;
        }
        return videoInfo.getVideoSrcByName(videoName);
    }

}
