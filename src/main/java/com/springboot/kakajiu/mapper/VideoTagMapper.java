package com.springboot.kakajiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.kakajiu.pojo.VideoTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zlyang
 * date: 2021-10-28
 * discription: mapper for videoTag
 */
@Mapper
@Repository
public interface VideoTagMapper extends BaseMapper<VideoTag> {
}
