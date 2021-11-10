package com.springboot.kakajiu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.kakajiu.mapper.TeacherInviteKeyMapper;
import com.springboot.kakajiu.mapper.TeacherStudentMapper;
import com.springboot.kakajiu.mapper.UserMapper;
import com.springboot.kakajiu.pojo.TeacherInviteKey;
import com.springboot.kakajiu.pojo.TeacherStudent;
import com.springboot.kakajiu.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zlyang
 * @date 2021-11-5
 * @discription Service for relationship between teacher student and admin
 */
@Service
public class RolesService {

    @Resource
    TeacherInviteKeyMapper teacherInviteKeyMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    TeacherStudentMapper teacherStudentMapper;

    public String getInviteKey(int teacherId) throws NullPointerException {
        TeacherInviteKey teacherInviteKey = teacherInviteKeyMapper.selectById(teacherId);
        return teacherInviteKey.getInviteKey();
    }

    /**
     *
     * @param teacherId
     * @param newKey
     * @return 1 if update success, 0 if teacherId not found
     */
    public int setInviteKey(int teacherId, String newKey){
        TeacherInviteKey teacherInviteKey = new TeacherInviteKey();
        teacherInviteKey.setTeacherId(teacherId);
        teacherInviteKey.setInviteKey(newKey);
        return teacherInviteKeyMapper.updateById(teacherInviteKey);
    }

    public boolean checkUserPassword(User user, String password) throws NullPointerException{
        User userSelect = userMapper.selectById(user.getUserId());
        return password.equals(userSelect.getPassword());
    }

    /**
     *
     * @param user
     * @param password
     * @return 1 if update success, 0 if teacherId not found
     */
    public int changePassword(User user, String password){
        UpdateWrapper<User> updateWrapper = Wrappers.update();
        updateWrapper.eq("user_id", user.getUserId());
        updateWrapper.set("password", password);
        int status = userMapper.update(null, updateWrapper);
        return status;
    }

    public List<String> getStudents(User teacher){
        QueryWrapper<TeacherStudent> teacherStudentWrapper = Wrappers.query();
        teacherStudentWrapper.select("student_id").eq("teacher_id", teacher.getUserId());
        List<TeacherStudent> teacherStudents = teacherStudentMapper.selectList(teacherStudentWrapper);
        List<Integer> studentIds = teacherStudents.stream().map(e -> e.getStudentId()).collect(Collectors.toList());
        // 当该名老师没有学生时，直接返回空list，不再查询
        if(studentIds.isEmpty()){
            return new ArrayList<String>();
        }
        QueryWrapper<User> studentNameQuery = Wrappers.query();
        studentNameQuery.select("username").in("user_id", studentIds);
        List<User> users = userMapper.selectList(studentNameQuery);
        List<String> studentNames = users.stream().map(e -> e.getUsername()).collect(Collectors.toList());
        return studentNames;
    }

    /**
     *
     * @param teacher
     * @param studentName
     * @return 1 if everything is ok
     * @throws NullPointerException when teacher is null
     * @throws SQLException when delete in teacher_student or user table failed
     */
    @Transactional(rollbackFor = Exception.class)
    public int teacherDeleteStudent(User teacher, String studentName) throws NullPointerException, SQLException {
        QueryWrapper<User> studentNameQuery = Wrappers.query();
        studentNameQuery.select("user_id").eq("username",studentName);
        User student = userMapper.selectOne(studentNameQuery);
        QueryWrapper<TeacherStudent> teacherStudentDeleteWrapper = Wrappers.query();
        teacherStudentDeleteWrapper.eq("teacher_id", teacher.getUserId()).eq("student_id", student.getUserId());
        int teacherStudentDeleteStatus = teacherStudentMapper.delete(teacherStudentDeleteWrapper);
        if(teacherStudentDeleteStatus != 1){
            throw new SQLException("Delete teacher_student table failed!");
        }
        int studentDeleteStatus = userMapper.deleteById(student.getUserId());
        if(studentDeleteStatus != 1){
            throw new SQLException("Delete student in user table failed!");
        }
        return 1;
    }

}
