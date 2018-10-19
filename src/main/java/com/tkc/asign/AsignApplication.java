package com.tkc.asign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.tkc.controller")
public class AsignApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsignApplication.class, args);
	}
}
