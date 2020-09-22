package com.cinestar.application.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

		Set<Funcion> result = new HashSet<>();
		long millis = System.currentTimeMillis();
		Date today = Date.valueOf("2020-09-22");
		for (Funcion func : repository.findAll()) {
			if (func.getDia().equals(today))
				result.add(func);
		}
		return result;
	}

	public Optional<Funcion> getFuncion(Long id) {
		return repository.findById(id);
	}

	public Iterable<Funcion> getFuncionesPeliSede(Pelicula pelicula, Sala sala) {

		Set<Funcion> result = new HashSet<>();
		long millis = System.currentTimeMillis();
		Date today = Date.valueOf("2020-09-22");

		for (Funcion func : repository.findByPeliculaAndSala(pelicula, sala)) {
			if (func.getDia().equals(today))
				result.add(func);
		}
		return result;
	}
}
