package br.com.prof_ferretto.book_service.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("book-service")
public class FooBarController {
	
	private static final Logger logger = LoggerFactory.getLogger(FooBarController.class);

	@GetMapping("/foo-bar")
	@Retry(name = "foo-bar-res4j", fallbackMethod = "fallback")
	public String fooBar() {
		System.out.println("Request foo-bar" + LocalDateTime.now());
		var response = new RestTemplate().getForEntity(
				"http://localhost:8080/foo-bar", String.class);
		//return "Foo-Bar!!!";
		return response.getBody();
	}
	
	public String fallback(Exception ex) {
		return "Fallback Method foo-bar!!!" + LocalDate.now();
	}
	
	@GetMapping("/other-bar")
	public String otherBar() {
		logger.info("Request to otherBar");
		var response = new RestTemplate().getForEntity(
				"http://localhost:8080/foo-bar", String.class);
		//return "Foo-Bar!!!";
		return response.getBody();
	}
	
	
}
