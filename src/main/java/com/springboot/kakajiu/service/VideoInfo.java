package com.springboot.kakajiu.service;

import com.springboot.kakajiu.entity.FrontpageVideoList;
import com.springboot.kakajiu.mapper.VideoMapper;
import com.springboot.kakajiu.mapper.VideoTagMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VideoInfo {

    @Resource
    VideoMapper videoMapper;

    @Resource
    VideoTagMapper videoTagMapper;

//    public FrontpageVideoList getClassifiedVideos(){
//
//    };

}
