package com.springboot.kakajiu.entity;

import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zlyang
 * @date 2021-10-31
 * @discription token传入Realm的载体
 */
@AllArgsConstructor
public class ShiroAuthToken implements AuthenticationToken {

    private String token;

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
