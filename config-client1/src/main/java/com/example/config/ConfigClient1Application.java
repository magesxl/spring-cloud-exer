package com.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClient1Application.class, args);
	}
}
