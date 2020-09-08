package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Pelicula;

@Repository
public interface PeliculaRepository extends CrudRepository<Pelicula, Long> {

}
