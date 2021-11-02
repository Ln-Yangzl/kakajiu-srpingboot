package com.springboot.kakajiu.controller;

import com.springboot.kakajiu.entity.LoginResponse;
import com.springboot.kakajiu.entity.SimpleResponseInfo;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.service.UserService;
import com.springboot.kakajiu.utils.JwtUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/noauth")
    public Object unauthorized(){
        SimpleResponseInfo responseInfo = new SimpleResponseInfo();
        responseInfo.setStatus(-1);
        responseInfo.setError("no auth!");
        return responseInfo;
    }

}
