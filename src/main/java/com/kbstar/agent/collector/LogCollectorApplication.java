package com.kbstar.agent.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LogCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogCollectorApplication.class, args);
	}

	// 프로그램 종료 
	public static void exit(ConfigurableApplicationContext context, Object bean, int exitCode) {
		SpringApplication.exit(context, () -> exitCode);
		System.exit(exitCode);
	}
}
