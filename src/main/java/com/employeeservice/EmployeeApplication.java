package com.employeeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@ComponentScan(basePackages="com.employeeservice") 
public class EmployeeApplication {
	public static void main(String[] args) {
		
		SpringApplication.run(EmployeeApplication.class, args);
	}

}
