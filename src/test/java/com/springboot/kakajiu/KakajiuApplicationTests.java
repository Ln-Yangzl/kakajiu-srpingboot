package com.springboot.kakajiu;

import com.springboot.kakajiu.entity.User;
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

}
