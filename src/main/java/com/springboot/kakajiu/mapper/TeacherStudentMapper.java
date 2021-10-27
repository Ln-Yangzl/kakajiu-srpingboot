package com.springboot.kakajiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.kakajiu.entity.TeacherStudent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zlyang
 * date: 2021-10-27
 * discription: mapper for TeacherStudent
 */
@Mapper
@Repository
public interface TeacherStudentMapper extends BaseMapper<TeacherStudent> {
}
