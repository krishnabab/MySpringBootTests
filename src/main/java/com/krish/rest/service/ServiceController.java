package com.krish.rest.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/service")
public class ServiceController {

	private static final String template = "Hello, %s!";
	private static final String aadrTemplate = "Hello, %s. Are you from %s ??";
	private final AtomicLong counter = new AtomicLong();
	
	// http://localhost:9090/service/hello
	@RequestMapping(value="/hello",method= {RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value = "This is method is to say HI to all")
	public String sayHi() {
		return "Hello Buddy !";
	}
	

	// http://localhost:9090/service/hello/Prahaas
		@RequestMapping("/hello/{name}")
	public String sayHello(@PathVariable("name") String  name) {
		return "Hello Buddy ! "+ name;
	}
	
	// http://localhost:9090/service/greeting
	// http://localhost:9090/service/greeting?name=PragnyaSri
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	// http://localhost:9090/service/greeting/NJ?name=PragnyaSri
	@RequestMapping("/greeting/{addr}")
	public Greeting greetWAddr(@PathVariable("addr") String  addr, @RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(aadrTemplate, name,addr));
	}
	
	@ApiOperation(value = "Add new equipment", notes = "Add new equipment in case of swapped")
	@PostMapping(value = "/fulfillmentcenters/{fulfillmentCenterId}/techs/{techId}/equipments", produces = MediaType.APPLICATION_JSON_VALUE)
	EquipmentDTO addEquipmentToTechBuffer(@PathVariable(required = true) Long fulfillmentCenterId, @PathVariable(required = true) String techId,
			@RequestBody @Validated EquipmentDTO equipmentDTO) {
		log.info("in the add equipment");
		return null;
	}
	
	/* 
	 * RequestMapping default method: 
		http://localhost:9090/service
	
	
	@RequestMapping()
	public String defaultMethod(){
		return "default method";
	}
	
	/* Request fallback method 
	 * RequestMapping fallback method: We can create a fallback method for the controller class 
	 * to make sure we are catching all the client requests even though there are no matching 
	 * handler methods. It is useful in sending custom 404 response pages to users when there are 
	 * no handler methods for the request.
	 * http://localhost:9090/service/dd
	
	
	@RequestMapping("*")
	public String fallbackMethod(){
		return "fallback method";
	}
	*/
}
