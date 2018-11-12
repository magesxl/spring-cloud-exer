package com.example.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 创建ConsumerController来消费COMPUTE-SERVICE的add服务
 */
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        //通过直接RestTemplate来调用服务，计算10 + 20的值。
        return restTemplate.getForEntity("http://compute-service/add?a=10&b=20", String.class).getBody();
    }

}
