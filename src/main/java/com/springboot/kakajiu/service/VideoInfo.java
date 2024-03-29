package com.springboot.kakajiu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.kakajiu.entity.FrontpageVideoList;
import com.springboot.kakajiu.entity.ResponseDataBody;
import com.springboot.kakajiu.entity.TagResponseData;
import com.springboot.kakajiu.entity.VideoSrcResponse;
import com.springboot.kakajiu.mapper.VideoMapper;
import com.springboot.kakajiu.mapper.VideoTagMapper;
import com.springboot.kakajiu.pojo.Video;
import com.springboot.kakajiu.pojo.VideoTag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Cacheable(value = "video-cache", key = "'getClassifiedVideos'", unless = "#result==null")
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

    @Cacheable(value = "video-cache", key = "'getVideoSrcByName:' + #videoName", unless = "#result.getStatus()!=0")
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

    @Cacheable(value = "video-cache", key = "'getTags'")
    public ResponseDataBody<TagResponseData> getTags(){
        QueryWrapper<VideoTag> wrapper = Wrappers.query();
        wrapper.select("tag_name");
        List<VideoTag> videoTags = videoTagMapper.selectList(wrapper);
        List<TagResponseData> responseData = videoTags.stream().map((videoTag) -> new TagResponseData(videoTag.getTagName())).collect(Collectors.toList());
        ResponseDataBody<TagResponseData> responseDataBody = new ResponseDataBody<>();
        responseDataBody.setStatus(0);
        responseDataBody.setData(responseData);
        return responseDataBody;
    }

    @Cacheable(value = "video-cache", key = "'getVideosByTag' + #tagName")
    public ResponseDataBody<String> getVideosByTag(String tagName){
        QueryWrapper<Video> wrapper = Wrappers.query();
        wrapper.select("title");
        wrapper.eq("tag_name", tagName);
        List<Video> videos = videoMapper.selectList(wrapper);
        List<String> videoNames = videos.stream().map((e) -> e.getTitle()).collect(Collectors.toList());
        ResponseDataBody<String> responseDataBody = new ResponseDataBody<>();
        responseDataBody.setStatus(0);
        responseDataBody.setData(videoNames);
        return responseDataBody;
    }

}
