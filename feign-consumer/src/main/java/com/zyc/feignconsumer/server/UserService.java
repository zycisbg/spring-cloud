package com.zyc.feignconsumer.server;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 该类可以抽出一个单独的API 。提供者和消费者都可以使用。
 * spring-cloud 微服务实战中206页游详细内容。
 */
@FeignClient("user-service")
public interface UserService {

    //不带参数
    @RequestMapping("/user")
    String hello();

    //带参数
    @RequestMapping("/user-param")
    String  paramUser(@RequestParam("name") String name, @RequestParam("age") Integer age);
}
