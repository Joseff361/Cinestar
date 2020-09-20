package com.cinestar.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pelicula;
import com.cinestar.application.entity.Sala;
import com.cinestar.application.repository.FuncionRepository;

@Service
public class FuncionService {
	@Autowired
	FuncionRepository repository;

	public Iterable<Funcion> getFunciones() {
		return repository.findAll();
	}

	public Iterable<Funcion> getFuncionesPeliSede(Pelicula pelicula, Sala sala) {

		return repository.findByPeliculaAndSala(pelicula, sala);
	}
}
