package br.com.prof_ferretto.book_service.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prof_ferretto.book_service.entities.BookEntity;
import br.com.prof_ferretto.book_service.proxies.CambioProxy;
import br.com.prof_ferretto.book_service.repositories.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	private final BookRepository bookRepository;
	private final CambioProxy cambioProxy;
	
	public BookController(BookRepository bookRepository, CambioProxy cambioProxy) {
		super();
		this.bookRepository = bookRepository;
		this.cambioProxy = cambioProxy;
	}

	@Value("${server.port}")
	private int portServer;
	
	@GetMapping("/{id}/{currency}")
	public BookEntity findBook(
			@PathVariable Long id,
			@PathVariable String currency) throws Exception {
		
		var book = bookRepository.findById(id).orElseThrow(() -> new Exception("Book not Found"));
		var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);
		book.setPrice(cambio.getConvertedValue());
		
		book.setEnviroment("Book port: " + String.valueOf(portServer) + " -- Cambio Port: " + cambio.getEnviroment());
		
		return book;
	}

}