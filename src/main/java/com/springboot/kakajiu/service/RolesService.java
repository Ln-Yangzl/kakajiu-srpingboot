package com.springboot.kakajiu.service;

import com.springboot.kakajiu.mapper.TeacherInviteKeyMapper;
import com.springboot.kakajiu.pojo.TeacherInviteKey;
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

}
