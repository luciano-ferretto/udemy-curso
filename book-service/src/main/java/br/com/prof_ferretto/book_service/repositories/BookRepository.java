package br.com.prof_ferretto.book_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prof_ferretto.book_service.entities.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long>{

}