package br.com.prof_ferretto.book_service.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cambio-service")
public interface CambioProxy {
	
	@GetMapping("/cambio-service/{amount}/{from}/{to}")
	public CambioResponse getCambio(@PathVariable Double amount, @PathVariable String from, @PathVariable String to);
}