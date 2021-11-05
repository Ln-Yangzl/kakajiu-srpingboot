package com.springboot.kakajiu.controller;

import com.springboot.kakajiu.entity.InviteKeyResponse;
import com.springboot.kakajiu.entity.SimpleResponseInfo;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.service.RolesService;
import com.springboot.kakajiu.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zlyang
 * @date 2021-11-5
 * @discription controller for relationship between teacher student and admin
 */
@RestController
public class RolesController {

    @Resource
    UserService userService;

    @Resource
    RolesService rolesService;

    @GetMapping("/api/getinvitekey")
    public Object getInviteKey(@RequestHeader(value = "Authorization") String token){
        User user = userService.validationToken(token);
        try{
            System.out.println(user.getUserId());
            String inviteKey = rolesService.getInviteKey(user.getUserId());
            InviteKeyResponse response = new InviteKeyResponse();
            response.setStatus(0);
            response.setInvitekey(inviteKey);
            return response;
        }catch (NullPointerException e){
            SimpleResponseInfo response = new SimpleResponseInfo();
            response.setStatus(2);
            response.setError("未查询到该教师");
            return response;
        }
    }

    @PostMapping("/api/setinvitekey")
    public Object setInviteKey(@RequestParam(value = "invitekey") String inviteKey, @RequestHeader(value = "Authorization") String token){
        User user = userService.validationToken(token);
        int status = rolesService.setInviteKey(user.getUserId(), inviteKey);
        SimpleResponseInfo response = new SimpleResponseInfo();
        response.setStatus(0);
        if(status != 1){
            response.setStatus(1);
            response.setError("sql update error!");
        }
        return response;
    }

}
