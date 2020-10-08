package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Sede;

@Repository
public interface SedeRepository extends CrudRepository<Sede, Long> {

	Iterable<Sede> findAllByOrderByNombreAsc();

}
