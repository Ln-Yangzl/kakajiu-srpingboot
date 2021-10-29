package com.springboot.kakajiu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.kakajiu.entity.FrontpageVideoList;
import com.springboot.kakajiu.mapper.VideoMapper;
import com.springboot.kakajiu.mapper.VideoTagMapper;
import com.springboot.kakajiu.pojo.Video;
import com.springboot.kakajiu.pojo.VideoTag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoInfo {

    @Resource
    VideoMapper videoMapper;

    @Resource
    VideoTagMapper videoTagMapper;

    public FrontpageVideoList getClassifiedVideos(){
        QueryWrapper<VideoTag> tagsWrapper = Wrappers.query();
        tagsWrapper.select("tag_name");
        List<VideoTag> videoTags = videoTagMapper.selectList(tagsWrapper);
        QueryWrapper<Video> videoWrapper = Wrappers.query();
        videoWrapper.select("title", "tag_name");
        List<Video> videoList = videoMapper.selectList(videoWrapper);
        Map<String, List<String>> classifiedVideos = new HashMap(videoTags.size());
        videoTags.forEach(item -> {
            classifiedVideos.put(item.getTagName(), new ArrayList<>());
        });
        videoList.forEach(item -> {
            classifiedVideos.get(item.getTagName()).add(item.getTitle());
        });
        return new FrontpageVideoList(classifiedVideos);
    };

}
