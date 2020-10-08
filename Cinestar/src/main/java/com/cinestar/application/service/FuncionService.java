package com.cinestar.application.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

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

		Set<Funcion> result = new LinkedHashSet<>();
		long millis = System.currentTimeMillis();
		Date today = Date.valueOf("2020-09-24");
		
		for (Funcion func : repository.findAllByOrderByDiaAsc()) {
			if (func.getDia().getDate()<=26)
				
				result.add(func);
		}
				
		
		return result;
	}

	public Optional<Funcion> getFuncion(Long id) {
		return repository.findById(id);
	}

	public Iterable<Funcion> getFuncionesPeliSede(Pelicula pelicula, Sala sala) {

		Set<Funcion> result = new LinkedHashSet<>();
		long millis = System.currentTimeMillis();
		Date today = Date.valueOf("2020-09-24");

		for (Funcion func : repository.findByPeliculaAndSalaOrderByDia(pelicula, sala)) {
			if (func.getDia().getDate()<=26)
				result.add(func);
		}
		return result;
	}
}
