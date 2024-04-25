package com.car.rental.justride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JustrideApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(JustrideApplication.class, args);
	}

}
