package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Usuario;

@Repository
public interface PagoRepository extends CrudRepository<Pago, Long>{
	Iterable<Pago> findAllByUserOrderByHoraAsc(Usuario usuario);

	
	
	
}
