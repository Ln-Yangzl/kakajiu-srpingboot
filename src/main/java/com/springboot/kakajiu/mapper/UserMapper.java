package com.springboot.kakajiu.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.kakajiu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author zlyang
 * date: 2021-10-27
 * discription mapper for User
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询表中的所有用户
     * @return 所有表中的用户
     */
    @Select("select * from user")
    public List<User> selectAll();

}
