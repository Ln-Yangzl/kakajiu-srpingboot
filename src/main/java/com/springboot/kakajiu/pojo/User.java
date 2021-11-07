package com.springboot.kakajiu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

/**
 * @author zlyang
 * date: 2021-10-27
 * discription: 用户对象对接数据库user表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer userId = 0;
    private String username;
    private String password;
    private String roles;

}
