package com.example.demo;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;



@SpringBootApplication
@EnableAsync
public class DbmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbmsApplication.class, args);
	}
	
	@Bean(name="taskExecutor")
	public Executor taskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(2);
	    executor.setMaxPoolSize(2);
	    executor.setQueueCapacity(500);
	    executor.setThreadNamePrefix("GithubLookup-");
	    executor.initialize();
	    return executor;
	 }

}
