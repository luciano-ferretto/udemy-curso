package br.com.prof_ferretto.greeting_service.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.prof_ferretto.greeting_service.configs.GreetingConfig;
import br.com.prof_ferretto.greeting_service.entities.Greeting;

@RestController
public class GreetingController {

	private static final String template = "%s, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	private final GreetingConfig greetingConfig;

    GreetingController(GreetingConfig greetingConfig) {
        this.greetingConfig = greetingConfig;
    }
	
	@GetMapping("/greeting")
	public Greeting greeting (@RequestParam(defaultValue = "") String name) {
		
		if (name.isEmpty()) name = greetingConfig.getDefaultValue();
		
		return new Greeting(counter.incrementAndGet(), String.format(template, greetingConfig.getGreeting(), name));
	}
}
