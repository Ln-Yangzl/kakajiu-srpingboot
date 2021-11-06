package com.springboot.kakajiu.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.kakajiu.mapper.TeacherInviteKeyMapper;
import com.springboot.kakajiu.mapper.UserMapper;
import com.springboot.kakajiu.pojo.TeacherInviteKey;
import com.springboot.kakajiu.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.BatchUpdateException;

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

    public int changePassword(User user, String password){
        UpdateWrapper<User> updateWrapper = Wrappers.update();
        updateWrapper.eq("user_id", user.getUserId());
        updateWrapper.set("password", password);
        int status = userMapper.update(null, updateWrapper);
        return status == 1 ? 0 : 1;
    }

}
