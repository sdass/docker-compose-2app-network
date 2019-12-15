package com.subra.dockercomposeproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	private Logger log = LoggerFactory.getLogger(HelloController.class);
	@ResponseBody
	@RequestMapping("/hi")
	String sayHello(){
	log.info("sayHello()...");
		return "say hello by compose";
		
	}
}
