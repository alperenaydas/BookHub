package com.golddeers.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootPostgreSQLApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPostgreSQLApplication.class, args);
	}
}