package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Comentario;


@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long>{
	public Iterable<Comentario> findAllByOrderByHoraAsc();	
}