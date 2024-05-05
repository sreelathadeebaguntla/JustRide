package com.justride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, ValidationAutoConfiguration.class })
public class JustrideApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(JustrideApplication.class, args);
	}

}
