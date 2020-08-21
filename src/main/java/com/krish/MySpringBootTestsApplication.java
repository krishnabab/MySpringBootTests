package com.krish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@ComponentScan(basePackages = " com.krish.*")
//@ComponentScan({"com.krish","com.krish.rest.service"})
@EnableScheduling
@EnableFeignClients
public class MySpringBootTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootTestsApplication.class, args);
		System.out.println("Krishnas : First Spring boot");
	}
	
}
