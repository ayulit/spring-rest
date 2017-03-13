package com.packtpub.springrest.app;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* For making service executable using Spring Boot.
 * 
 * This class bootstraps our application by discovering all the web components and
   loading them.
 *  */

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) throws IOException {
		
		SpringApplication.run(WebApplication.class, args);	

		System.out.println("Done");
		
	}

}
