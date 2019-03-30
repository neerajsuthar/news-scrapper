package org.nrj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Runner class for the appication.
 * 
 * @author Neeraj Suthar
 *
 */
@SpringBootApplication
public class NewsScrapperRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(NewsScrapperRunner.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
