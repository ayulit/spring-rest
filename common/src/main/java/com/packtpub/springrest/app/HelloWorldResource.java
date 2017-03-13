package com.packtpub.springrest.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/* Controller to quickly test the service provided */

@RestController
public class HelloWorldResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public String helloWorld() {
		return "Hello, world!";
	}

}
