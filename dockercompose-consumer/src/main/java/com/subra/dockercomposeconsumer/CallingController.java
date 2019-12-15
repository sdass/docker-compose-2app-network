package com.subra.dockercomposeconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallingController {

	@Autowired ConsumeService consumesvc;
	private final Logger log = LoggerFactory.getLogger(ConsumeService.class);
	
	@RequestMapping("/getother")
	String getFromOther(){
		
		String rcvd = consumesvc.getValueFromOtherService();
		log.info("getFromOther(). . . .");
		
		return rcvd;
	}
	
	@RequestMapping("/testing")
	String getTesting(){
				
		log.info("getTesting(). . . .");
		
		return "testing passed";
	}
	
}
