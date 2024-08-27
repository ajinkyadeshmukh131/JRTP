package com.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisCacheServerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCacheServerApiApplication.class, args);
	}

}
