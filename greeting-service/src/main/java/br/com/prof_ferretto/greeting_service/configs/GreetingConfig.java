package br.com.prof_ferretto.greeting_service.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope //para permitir atualizar sem reiniciar a applicação - http://localhost:8080/actuator/refresh
@ConfigurationProperties("greeting-service")
public class GreetingConfig {
	
	private String greeting;
	private String defaultValue;
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	
	
}
