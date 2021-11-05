package com.springboot.kakajiu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.kakajiu.entity.FrontpageVideoList;
import com.springboot.kakajiu.entity.ResponseBody;
import com.springboot.kakajiu.entity.TagResponseData;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.mapper.UserMapper;
import com.springboot.kakajiu.service.RolesService;
import com.springboot.kakajiu.service.VideoInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class KakajiuApplicationTests {

    @Resource
    UserMapper userMapper;

    @Resource
    VideoInfo videoInfo;

    @Resource
    RolesService rolesService;


    @Test
    void testDatabaseConnection() {
        List<User> users = userMapper.selectAll();
        users.forEach(System.out::println);
    }

    @Test
    void testWrapper(){
        QueryWrapper<User> userQueryWrapper = Wrappers.query();
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper();
        userQueryWrapper.select("username", "password");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testGetClassifiedVideos(){
        FrontpageVideoList classifiedVideos = videoInfo.getClassifiedVideos();
        classifiedVideos.getData().forEach(System.out::println);
    }

    @Test
    void testGetTagsAndGetVideosByTag(){
        ResponseBody<TagResponseData> tags = videoInfo.getTags();
        tags.getData().forEach(System.out::println);
        ResponseBody<String> videos = videoInfo.getVideosByTag("央视");
        videos.getData().forEach(System.out::println);
    }

    @Test
    void testGetInviteKey(){
        String inviteKey = rolesService.getInviteKey(2);
        System.out.println(inviteKey);
    }

    @Test
    void testSetInviteKey(){
        System.out.println(rolesService.setInviteKey(2, "kamisato"));
    }

}
