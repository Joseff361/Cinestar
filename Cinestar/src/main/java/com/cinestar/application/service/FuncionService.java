package com.cinestar.application.service;

import java.util.Calendar;
import java.util.LinkedHashSet;
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

		Set<Funcion> result = new LinkedHashSet<>();
		Calendar cal= Calendar.getInstance();
		
		for (Funcion func : repository.findAllByOrderByDiaAsc()) {
			cal.setTime(func.getDia());
			
			if (cal.get(Calendar.DAY_OF_MONTH)<=26)
				
				result.add(func);
		}
				
		
		return result;
	}

	public Optional<Funcion> getFuncion(Long id) {
		return repository.findById(id);
	}

	public Iterable<Funcion> getFuncionesPeliSede(Pelicula pelicula, Sala sala) {
		
		Set<Funcion> result = new LinkedHashSet<>();
		Calendar cal= Calendar.getInstance();
		

		for (Funcion func : repository.findByPeliculaAndSalaOrderByDia(pelicula, sala)) {
			cal.setTime(func.getDia());
			if (cal.get(Calendar.DAY_OF_MONTH)<=26)
				result.add(func);
		}
		return result;
	}
}
