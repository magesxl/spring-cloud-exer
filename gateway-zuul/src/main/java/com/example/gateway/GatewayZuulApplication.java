package com.example.gateway;

import com.example.gateway.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class GatewayZuulApplication {

	@Bean
	public AccessFilter accessFilter(){
		return new AccessFilter();
	}
	public static void main(String[] args) {
		SpringApplication.run(GatewayZuulApplication.class, args);
	}
}
