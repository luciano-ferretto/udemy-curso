package br.com.prof_ferretto.cambio_service.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prof_ferretto.cambio_service.entities.CambioEntity;
import br.com.prof_ferretto.cambio_service.repositories.CambioRepository;

@RestController
@RequestMapping("cambio-service")
public class CambioController {
	
	private final CambioRepository cambioRepository;
	
	public CambioController(CambioRepository cambioRepository) {
		super();
		this.cambioRepository = cambioRepository;
	}

	@Value("${server.port}")
	private int portServer;
	
	@GetMapping("/{amount}/{from}/{to}")
	public CambioEntity getCampio(
			@PathVariable BigDecimal amount,
			@PathVariable String from,
			@PathVariable String to) throws Exception {
		CambioEntity cambio = cambioRepository.findByFromAndTo(from , to).orElseThrow(() -> new Exception("Currency Unsupoorted"));
		cambio.setConvertedValue(cambio.getConversionFactor().multiply(amount).setScale(2, RoundingMode.CEILING));
		cambio.setEnviroment(String.valueOf(portServer));
		return cambio;
	}

}
