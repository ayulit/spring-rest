package com.packtpub.springrest.inventory;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/* For making service executable using Spring Boot.
 * 
 * This class bootstraps our application by discovering all the web components and
   loading them.
 *  */

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) throws IOException {
		
		ConfigurableApplicationContext context = 
				SpringApplication.run(new Object[]{WebApplication.class, "inventory.xml"},
						              args);	

		InventoryService inventoryService = context.getBean(InventoryService.class);
		
		
	
		
		System.out.println("Done");
		
	}

}
