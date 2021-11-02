package com.springboot.kakajiu.controller;

import com.springboot.kakajiu.entity.LoginResponse;
import com.springboot.kakajiu.entity.SimpleResponseInfo;
import com.springboot.kakajiu.entity.UpdateTokenResponse;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.service.UserService;
import com.springboot.kakajiu.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;

/**
 * @author zlyang
 * @date 2021-10-31
 * @discription controller for user, included login, auth
 */
@RestController
public class UserController {

    @Resource
    UserService userService;
    
    @GetMapping("/testauth")
    public Object testauth(){
        SimpleResponseInfo responseInfo = new SimpleResponseInfo();
        responseInfo.setStatus(0);
        responseInfo.setInfo("test auth success !");
        return responseInfo;
    }

    @PostMapping({"/login","/api/login"})
    public Object login(String username, String password, ServletResponse response){
        if(username == null || password == null || username.isEmpty() || password.isEmpty()){
            SimpleResponseInfo responseInfo = new SimpleResponseInfo();
            responseInfo.setStatus(2);
            responseInfo.setError("缺失用户名或密码");
            return responseInfo;
        }
        User user = userService.getUserByNameAndPsw(username, password);
        if(user == null){
            SimpleResponseInfo responseInfo = new SimpleResponseInfo();
            responseInfo.setStatus(2);
            responseInfo.setError("用户名或密码错误");
            return responseInfo;
        }
        String token = JwtUtils.generatorToken(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setIdentity(user.getRoles());
        loginResponse.setToken(token);
        return loginResponse;
    }

    @GetMapping("/api/updatetoken")
    public Object updateToken(@RequestHeader(value = "Authorization") String token){
        String newToken = userService.updateToken(token);
        UpdateTokenResponse response = new UpdateTokenResponse();
        if(newToken == null){
             response.setStatus(1);
             response.setError("token 无效");
        } else {
            response.setStatus(0);
            response.setNewToken(newToken);
        }
        return response;
    }

}
