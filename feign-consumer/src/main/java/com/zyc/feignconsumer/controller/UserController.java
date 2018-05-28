package com.zyc.feignconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zyc.feignconsumer.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/feign-consumer")
    @HystrixCommand(fallbackMethod = "fallbackIndex")
    public String index(){
        return userService.hello();
    }

    public String fallbackIndex(){
        return "error";
    }

    @RequestMapping("/feign-consumer-param")
    public String indexParam(@RequestParam("name") String name ,@RequestParam("age") Integer age){
        return userService.paramUser(name,age);
    }
}
