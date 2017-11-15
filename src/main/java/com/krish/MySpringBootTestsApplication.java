package com.krish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = " com.krish.*")
@ComponentScan({"com.krish","com.krish.rest.service"})
public class MySpringBootTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootTestsApplication.class, args);
		System.out.println("Krishnas : First Spring boot");
	}
}
