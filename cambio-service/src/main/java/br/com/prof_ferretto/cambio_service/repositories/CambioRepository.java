package br.com.prof_ferretto.cambio_service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prof_ferretto.cambio_service.entities.CambioEntity;

public interface CambioRepository extends JpaRepository<CambioEntity, Long>{
	
	Optional<CambioEntity> findByFromAndTo(String from, String to);

}