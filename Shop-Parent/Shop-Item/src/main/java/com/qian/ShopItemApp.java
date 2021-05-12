package com.qian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.qian.mapper")
public class ShopItemApp {

	public static void main(String[] args) {
		SpringApplication.run(ShopItemApp.class, args);
	}
}
