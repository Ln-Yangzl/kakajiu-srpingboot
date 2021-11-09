package com.springboot.kakajiu.controller;

import com.springboot.kakajiu.entity.InviteKeyResponse;
import com.springboot.kakajiu.entity.ResponseDataBody;
import com.springboot.kakajiu.entity.SimpleResponseInfo;
import com.springboot.kakajiu.pojo.User;
import com.springboot.kakajiu.service.RolesService;
import com.springboot.kakajiu.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

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

    @GetMapping("/api/checkpassword")
    public Object checkPassword(@RequestParam(value = "password") String password, @RequestHeader(value = "Authorization") String token){
        try{
            boolean status = rolesService.checkUserPassword(userService.validationToken(token), password);
            SimpleResponseInfo response = new SimpleResponseInfo();
            response.setStatus(0);
            if(!status){
                response.setStatus(2);
                response.setError("Invalid password");
            }
            return response;
        }catch (NullPointerException e){
            SimpleResponseInfo response = new SimpleResponseInfo();
            response.setStatus(2);
            response.setError("Teacher id in token is invalid!");
            e.printStackTrace();
            return response;
        }
    }

    @PostMapping("/api/changepassword")
    public Object changePassword(
            @RequestParam(value = "oldpassword") String oldPassword,
            @RequestParam(value = "newpassword") String newPassword,
            @RequestHeader(value = "Authorization") String token
    ){
        try{
            User user = userService.validationToken(token);
            if(!rolesService.checkUserPassword(user, oldPassword)){
                SimpleResponseInfo response = new SimpleResponseInfo();
                response.setStatus(2);
                response.setError("Wrong old password!");
                return response;
            } else {
                int status = rolesService.changePassword(user, newPassword);
                SimpleResponseInfo response = new SimpleResponseInfo();
                response.setStatus(status == 1 ? 0 : 1);
                return response;
            }
        }catch (NullPointerException e){
            SimpleResponseInfo response = new SimpleResponseInfo();
            response.setStatus(1);
            response.setError("Teacher id in token is invalid!");
            return response;
        }
    }

    @GetMapping("/api/getstudents")
    public Object getStudents(@RequestHeader(value = "Authorization") String token){
        User teacher = userService.validationToken(token);
        List<String> students = rolesService.getStudents(teacher);
        ResponseDataBody<String> responseDataBody = new ResponseDataBody<>();
        responseDataBody.setData(students);
        responseDataBody.setStatus(0);
        return responseDataBody;
    }

    @PostMapping("/api/teacherdeletestudent")
    public Object teacherDeleteStudent(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "studentname") String studentName
    ){
        User teacher = userService.validationToken(token);
        SimpleResponseInfo response = new SimpleResponseInfo();
        try {
            int status = rolesService.teacherDeleteStudent(teacher, studentName);
            response.setStatus(status == 1 ? 0 : 1);
            response.setError(status == 1 ? "" : "unknown error");
        } catch (SQLException e) {
            response.setStatus(1);
            response.setError(e.getMessage());
        } catch (NullPointerException e){
            response.setStatus(2);
            response.setError("StudentName not found in user!");
        }
        return response;
    }

}
