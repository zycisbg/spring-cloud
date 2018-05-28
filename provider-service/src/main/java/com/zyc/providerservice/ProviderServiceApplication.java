package com.zyc.providerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 在主类上增加@EnableDiscoveryClient注解，激活Eureka中的
 * DiscoveryClient实现。让该应用注册为Eureka客户端。
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderServiceApplication.class, args);
	}
}
