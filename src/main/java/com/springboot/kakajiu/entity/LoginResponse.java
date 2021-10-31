package com.springboot.kakajiu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zlyang
 * @date 2021-10-31
 * @discription loginresponse
 */
@Data
public class LoginResponse implements Serializable {

    private String identity;
    private String token;

}
