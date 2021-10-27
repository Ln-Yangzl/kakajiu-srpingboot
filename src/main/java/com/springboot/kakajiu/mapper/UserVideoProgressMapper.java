package com.springboot.kakajiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.kakajiu.entity.UserVideoProgress;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zlyang
 * date: 2021-10-27
 * discription: mapper for UserVideoPorgress
 */
@Mapper
@Repository
public interface UserVideoProgressMapper extends BaseMapper<UserVideoProgress> {
}
