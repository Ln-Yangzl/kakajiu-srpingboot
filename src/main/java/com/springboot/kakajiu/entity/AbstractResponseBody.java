package com.springboot.kakajiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zlyang
 * date: 2021-10-28
 * discription: 网络消息返回的抽象基类，定义了基本数据
 *      data为返回数据内容实体
 *      status为状态，0为正常
 *      error为错误信息
 */

@Data
public abstract class AbstractResponseBody<T> {

    private List<T> data;
    private int status;
    private String error;

}
