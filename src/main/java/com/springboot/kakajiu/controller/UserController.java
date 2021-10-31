package com.springboot.kakajiu.controller;

import com.springboot.kakajiu.entity.LoginResponse;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.service.UserService;
import com.springboot.kakajiu.utils.JwtUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zlyang
 * @date 2021-10-31
 * @discription controller for user, included login, auth
 */
@RestController
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public Object login(String username, String password){
        User user = userService.getUserByNameAndPsw(username, password);
        String token = JwtUtils.generatorToken(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setIdentity(user.getRoles());
        loginResponse.setToken(token);
        return loginResponse;
    }

}
