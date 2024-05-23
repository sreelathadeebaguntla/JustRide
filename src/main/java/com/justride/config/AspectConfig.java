package com.justride.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.justride.aspect.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {
	@Bean
	public LoggingAspect loggingAspect() {
	    return new LoggingAspect();
	}

}
