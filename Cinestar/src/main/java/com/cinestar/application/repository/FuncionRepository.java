package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pelicula;
import com.cinestar.application.entity.Sala;

@Repository
public interface FuncionRepository extends CrudRepository<Funcion, Long>{

	Iterable<Funcion> findByPeliculaAndSala(Pelicula pelicula,Sala sala);

	Iterable<Funcion> findByPeliculaAndSalaOrderByDia(Pelicula pelicula, Sala sala);

	Iterable<Funcion> findAllByOrderByDiaAsc();
	
}
