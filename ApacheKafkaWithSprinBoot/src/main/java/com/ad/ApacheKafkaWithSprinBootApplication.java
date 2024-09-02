package com.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ApacheKafkaWithSprinBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApacheKafkaWithSprinBootApplication.class, args);
	}

}
