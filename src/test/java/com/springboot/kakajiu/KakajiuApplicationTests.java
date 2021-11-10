package com.springboot.kakajiu;

import com.springboot.kakajiu.entity.*;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.mapper.UserMapper;
import com.springboot.kakajiu.service.RolesService;
import com.springboot.kakajiu.service.VideoInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
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
        User user = new User();
        user.setUsername("test2");
        user.setPassword("abcde");
        user.setRoles("student");
        int i = userMapper.insert(user);
        System.out.println(i);
        System.out.println(user);
    }

    @Test
    void testGetClassifiedVideos(){
        FrontpageVideoList classifiedVideos = videoInfo.getClassifiedVideos();
        classifiedVideos.getData().forEach(System.out::println);
    }

    @Test
    void testGetTagsAndGetVideosByTag(){
        ResponseDataBody<TagResponseData> tags = videoInfo.getTags();
        tags.getData().forEach(System.out::println);
        ResponseDataBody<String> videos = videoInfo.getVideosByTag("央视");
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

    @Test
    void testChangePassword(){
        User user = new User();
        user.setUserId(3);
        int status = rolesService.changePassword(user, "abcd");
        System.out.println(status);
    }

    @Test
    void getTeacherStudents(){
        User user = new User();
        user.setUserId(9);
        List<String> students = rolesService.getStudents(user);
        System.out.println(students);
        students.forEach(System.out::println);
    }

    @Test
    void teacherDeleteStudent(){
        User teacher = new User();
        teacher.setUserId(9);
        int status = 0;
        try {
            status = rolesService.teacherDeleteStudent(teacher, "student3");
        } catch (Exception throwables) {
            System.out.println(throwables.getMessage());
        }
        System.out.println(status);
    }

    @Test
    void testVideoCache(){
        FrontpageVideoList classifiedVideos = videoInfo.getClassifiedVideos();
        List<FrontpageVideoListData> data = classifiedVideos.getData();
        System.out.println("FrontpageVideoListData");
        data.forEach(System.out::println);
        System.out.println("=============================");
        VideoSrcResponse videoSrc = videoInfo.getVideoSrcByName("山东卫视-《我们正青春》");
        System.out.println(videoSrc);
    }

}
