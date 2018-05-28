package com.zyc.ribbonconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zyc.ribbonconsumer.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerController {
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * 通过RestTemplate来实现对user-service的接口调用。
     */
    @Autowired
    RestTemplate restTemplate;

    /**
     *getForEntity返回的是一个ResponseEntity对象，他包含请求头，请求体，状态等。
     *getForObject是getForEntity进一步封装，他只返回最后的结果。他的用法和getForEntity一样。
     */
    @RequestMapping("/rpc-user-get")
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloConsumerGet(){
        //不传参
        logger.info("远程调用开始");
        ResponseEntity<String> entity = restTemplate.getForEntity("http://user-service/user", String.class);
        logger.info("返回请求头："+entity.getHeaders());
        logger.info("返回body："+entity.getBody());
        logger.info("返回codeValue："+entity.getStatusCodeValue());
        logger.info("远程调用结束");
        //如果传参可以选择下边几种方式传参
        //1，占位符
//        ResponseEntity<String> entityByParam = restTemplate.getForEntity("http://user-service/user-param?name={1}&age={2}", String.class,"laosan",18);
        //2，map方式
//        Map paramMap = new HashMap(2);
//        paramMap.put("name","laosi");
//        paramMap.put("age",22);
//        ResponseEntity<String> entityByParam = restTemplate.getForEntity("http://user-service/user-param?name={name}&age={age}",String.class,paramMap);
        //3，URI方式
//        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http;://user-service/user-param?name={1}&age={2}")
//                .build().expand("laowu",26).encode();
//        URI uri = uriComponents.toUri();
//        ResponseEntity<String> entityByParam = restTemplate.getForEntity(uri,String.class);
//        logger.info("返回请求头："+entityByParam.getHeaders());
//        logger.info("返回body："+entityByParam.getBody());
//        logger.info("返回codeValue："+entityByParam.getStatusCodeValue());
//
//        String forObject = restTemplate.getForObject("http://user-service/user-param?name=laoliu&age=29", String.class);
//        logger.info("getForObject："+forObject);

        return entity.getBody();
    }

    public String helloFallback(){
        return "123";
    }
    @RequestMapping("/rpc-user-post")
    public String helloConsumerPost(){
        User user = new User();
        user.setAge(18);
        user.setName("laosan");
        //不传参
        logger.info("远程调用开始");
        ResponseEntity<String> entity = restTemplate.postForEntity("http://user-service/user-post",user,String.class);
        logger.info("返回请求头："+entity.getHeaders());
        logger.info("返回body："+entity.getBody());
        logger.info("返回codeValue："+entity.getStatusCodeValue());
        logger.info("远程调用结束");
        //如果传参可以选择下边几种方式传参
        //1，占位符
//        ResponseEntity<String> entityByParam = restTemplate.postForEntity("http://user-service/user-post?name={1}&age={2}",user, String.class,"laosu",22);
        //2，map方式
        Map paramMap = new HashMap(2);
        paramMap.put("name","laosi");
        paramMap.put("age",22);
//        ResponseEntity<String> entityByParam = restTemplate.postForEntity("http://user-service/user-param?name={name}&age={age}",user,String.class,paramMap);
        //3，URI方式
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://user-service/user-param?name={1}&age={2}")
                .build().expand("laowu",26).encode();
        URI uri = uriComponents.toUri();
        ResponseEntity<String> entityByParam = restTemplate.postForEntity(uri,user,String.class);
        logger.info("返回请求头："+entityByParam.getHeaders());
        logger.info("返回body："+entityByParam.getBody());
        logger.info("返回codeValue："+entityByParam.getStatusCodeValue());

        String forObject = restTemplate.getForObject("http://user-service/user-param?name=laoliu&age=29", String.class);
        logger.info("getForObject："+forObject);

        return entityByParam.getBody();
    }
}
