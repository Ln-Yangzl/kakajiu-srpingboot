package com.springboot.kakajiu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.kakajiu.entity.FrontpageVideoList;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.mapper.UserMapper;
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

}
