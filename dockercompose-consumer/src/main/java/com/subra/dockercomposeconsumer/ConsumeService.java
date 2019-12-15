package com.subra.dockercomposeconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumeService {
		
	@Autowired
	private RestTemplate restTemplate;
	
	private final Logger log = LoggerFactory.getLogger(ConsumeService.class);
	
	public String getValueFromOtherService(){
		
		//dockercompose-producer>docker run --name producer-c -p 9090:9090  dkr-compose-producer
		//localhost will be replaced by container name (producer-c), which is (1) specified in the cmdline "--name producer-c" or 
		//(2) in docker-compose under service
		String result = "";
		String dockerUrl = "http://producer-c:9090/hi";
		//String url = "http://localhost:9090/hi";
		
		
		String url = dockerUrl;
		log.info("dockerUrl :"  + url );
		result = restTemplate.getForObject(url, String.class);
		log.info("received from " + url + " result=" + result);
		
		return result;
		
	}
	
	

}
