package com.comcast.coding.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author likhithkumarmatta
 *
 * Main class for Comcast Exercise Application
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.comcast.coding.test.*"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
