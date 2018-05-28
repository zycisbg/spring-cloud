package com.zyc.providerservice.controller;

import com.zyc.providerservice.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/user")
    public String  index(){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/user,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "this is userService";
    }

    @RequestMapping("/user-param")
    public String  paramUser(@RequestParam("name") String name,@RequestParam("age") Integer age){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/user,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "name:"+name+",age:"+age;
    }

    @RequestMapping("/user-post")
    public String  postUser(@RequestBody User user,@RequestParam(value = "name",required = false) String name,@RequestParam(value="age",required = false) Integer age){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/user,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        logger.info(user);
        logger.info("name:"+name);
        logger.info("age:"+age);
        return "post";
    }
}
