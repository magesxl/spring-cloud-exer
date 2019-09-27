package com.nacos.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientApplication.class, args);
    }

    @RestController
    @RefreshScope
    public class TestController {

        @Value("${title}")
        private String value;

        @RequestMapping(value = "/echo/config", method = RequestMethod.GET)
        public String echo() {
            return value;
        }
    }

}
