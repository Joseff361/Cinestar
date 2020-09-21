package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Asiento;
import com.cinestar.application.entity.Funcion;

@Repository
public interface AsientoRepository  extends CrudRepository<Asiento, Long> {
	Iterable<Asiento> findByIdFilaAndIdColumnaAndFuncion(String fila,Integer columna ,Funcion funcion);

	

	 Iterable<Asiento> findByFuncionOrderById(Funcion f);
}
