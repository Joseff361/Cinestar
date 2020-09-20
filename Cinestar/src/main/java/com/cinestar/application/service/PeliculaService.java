package com.cinestar.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.entity.Pelicula;
import com.cinestar.application.repository.PeliculaRepository;

@Service
public class PeliculaService {
	@Autowired
	PeliculaRepository repository;

	public Iterable<Pelicula> getPeliculas() {
		return repository.findAll();
	}

	public Iterable<Pelicula> getPeliculasByGenero(String genero) {
		return repository.findAllByGenero(genero);
	}

	public Optional<Pelicula> getPelicula(Long id) {
		return repository.findById(id);
	}
}
