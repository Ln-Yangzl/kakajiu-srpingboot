package com.springboot.kakajiu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

    @GetMapping("/test")
    public String connectionTest(){
        return "connection success!";
    }

//    @GetMapping("/api/frontpage")
//    public Object frontpageVideos(){
//
//    }

}
