package com.cinestar.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.entity.Asiento;
import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pelicula;
import com.cinestar.application.entity.Sala;
import com.cinestar.application.repository.AsientoRepository;
import com.cinestar.application.repository.PeliculaRepository;

@Service
public class AsientoService {
	@Autowired
	AsientoRepository repository;
	public Iterable<Asiento> findByColumnaAndByFilaAndByFuncion(String fila,Integer columna ,Funcion funcion){
		return repository.findByIdFilaAndIdColumnaAndFuncion(fila,columna ,funcion);
	}
	public Iterable<Asiento> findAsientos(Funcion f) {
		return repository.findByFuncionOrderById(f);
	}
}
