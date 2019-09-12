package com.example.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * 该注解能激活Eureka中的DiscoveryClient实现，才能实现Controller中对服务信息的输出。
 */
//@EnableDiscoveryClient
@SpringBootApplication
@EnableZipkinServer
public class ZipkinServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServiceApplication.class, args);
    }
}
