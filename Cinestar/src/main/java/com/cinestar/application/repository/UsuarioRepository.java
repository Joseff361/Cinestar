package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	//public Iterable<Usuario> findAllByStatus(String status);
	public Usuario findByUsername(String username);


}