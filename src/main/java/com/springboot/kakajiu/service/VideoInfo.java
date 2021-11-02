package com.springboot.kakajiu.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.kakajiu.entity.FrontpageVideoList;
import com.springboot.kakajiu.entity.VideoSrcResponse;
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

/**
 * @author zlyang
 * @date 2021-10-31
 * @discription service for videos
 */
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

    public VideoSrcResponse getVideoSrcByName(String videoName){
        QueryWrapper<Video> videoWrapper = Wrappers.query();
        videoWrapper.select("video_src");
        videoWrapper.eq("title", videoName);
        Video video = videoMapper.selectOne(videoWrapper);
        VideoSrcResponse response = new VideoSrcResponse();
        if(video == null){
            response.setStatus(2);
            response.setError("查询结果为空，检查videoname是否正确");
        } else {
            response.setStatus(0);
            response.setVideoSrc(video.getVideoSrc());
        }
        return response;
    }

}
