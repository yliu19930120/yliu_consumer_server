package com.yliu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAsync
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
