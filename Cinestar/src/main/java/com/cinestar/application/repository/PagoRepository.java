package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pago;

@Repository
public interface PagoRepository extends CrudRepository<Pago, Long>{

	Iterable<Pago> findAllByFuncion(Funcion funcion);
	
	
	
}
