package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Comentario;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Usuario;

@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long>{
	public Iterable<Comentario> findAllByOrderByHoraAsc();
	public Iterable<Comentario> findAllByUserOrderByHoraAsc(Usuario usuario);
	
}