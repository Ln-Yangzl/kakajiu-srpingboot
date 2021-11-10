package com.springboot.kakajiu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.kakajiu.mapper.TeacherInviteKeyMapper;
import com.springboot.kakajiu.mapper.TeacherStudentMapper;
import com.springboot.kakajiu.mapper.UserMapper;
import com.springboot.kakajiu.pojo.TeacherInviteKey;
import com.springboot.kakajiu.pojo.TeacherStudent;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.utils.JwtUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author zlyang
 * @date 2021-10-31
 * @discription service for user
 */
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    TeacherInviteKeyMapper teacherInviteKeyMapper;

    @Resource
    TeacherStudentMapper teacherStudentMapper;


    public User getUserByNameAndPsw(String username, String password){
        if(username == null || password == null){
            return null;
        }
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

    public User validationToken(String token){
        return JwtUtils.validationToken(token.substring("Bearer ".length()));
    }

    public String updateToken(String token){
        User user = JwtUtils.validationToken(token.substring("Bearer ".length()));
        if(user == null){
            return null;
        }
        String newToken = JwtUtils.generatorToken(user);
        return newToken;
    }

    /**
     *
     * @param teacherName
     * @param inviteKey
     * @return teacher id when verify success, return 0 when verify failed
     * @throws NullPointerException when teacherName or inviteKey not found in tables
     */
    public int verifyTeacherNameAndInviteKey(String teacherName, String inviteKey) throws NullPointerException{
        QueryWrapper<User> query = Wrappers.query();
        query.select("user_id").eq("username", teacherName);
        User user = userMapper.selectOne(query);
        TeacherInviteKey teacherInviteKey = teacherInviteKeyMapper.selectById(user.getUserId());
        int value = inviteKey.equals(teacherInviteKey.getInviteKey()) ? teacherInviteKey.getTeacherId() : 0;
        return value;
    }

    /**
     *
     * @param username
     * @param password
     * @param teacherName
     * @param inviteKey
     * @return 新插入的student的userId
     * @throws SQLException 插入执行错误时抛出
     * @throws IllegalArgumentException teacherName inviteKey不一致时抛出
     * @throws NullPointerException teacherName 错误时抛出
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertStudentUser(String username, String password, String teacherName, String inviteKey) throws SQLException, IllegalArgumentException, NullPointerException {
        int teacherId = verifyTeacherNameAndInviteKey(teacherName, inviteKey);
        if(teacherId == 0){
            throw new IllegalArgumentException("teacherName not correspond to inviteKey!");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles("student");
        int userInsetStatus = userMapper.insert(user);
        if(userInsetStatus != 1){
            throw new SQLException("Insert user failed!");
        }
        TeacherStudent teacherStudent = new TeacherStudent();
        teacherStudent.setTeacherId(teacherId);
        teacherStudent.setStudentId(user.getUserId());
        int teacherStudentInsertStatus = teacherStudentMapper.insert(teacherStudent);
        if(teacherStudentInsertStatus != 1){
            throw new SQLException("Insert teacher student failed!");
        }
        return user.getUserId();
    }

}
