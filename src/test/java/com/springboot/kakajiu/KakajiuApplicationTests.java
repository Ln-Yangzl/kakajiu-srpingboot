package com.springboot.kakajiu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class KakajiuApplicationTests {

    @Resource
    UserMapper userMapper;

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

}
