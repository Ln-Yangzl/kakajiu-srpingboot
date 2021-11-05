package com.springboot.kakajiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.kakajiu.pojo.TeacherInviteKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zlyang
 * @date 2021-11-5
 * @discription mapper for teacher_invite_key
 */
@Mapper
@Repository
public interface TeacherInviteKeyMapper extends BaseMapper<TeacherInviteKey> {
}
