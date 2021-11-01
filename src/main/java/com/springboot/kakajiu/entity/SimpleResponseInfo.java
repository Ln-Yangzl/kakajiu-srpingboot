package com.springboot.kakajiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zlyang
 * @date 2021-11-1
 * @discription 为只需要简单回复状态码和信息的controller提供支持
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleResponseInfo implements Serializable {

    private int status;
    private String error;
    private String info;

}
