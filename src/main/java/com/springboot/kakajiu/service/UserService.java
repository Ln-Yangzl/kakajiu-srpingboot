package com.springboot.kakajiu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.kakajiu.mapper.UserMapper;
import com.springboot.kakajiu.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zlyang
 * @date 2021-10-31
 * @discription service for user
 */
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public User getUserById(int userId){
        User user = userMapper.selectById(userId);
        return user;
    }

    public User getUserByNameAndPsw(String username, String password){
        QueryWrapper<User> query = Wrappers.query();
        query.select("user_id", "roles").eq("password", password).eq("username", username);
        User user = userMapper.selectOne(query);
        if(user == null || user.getUserId() == 0){
            return null;
        }
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

}