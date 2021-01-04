package com.cinestar.application.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
	@Autowired
	PeliculaService peliculaService;
	@Autowired
	SedeService sedeService;

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

	public Funcion getFuncion(Long id) {
		Optional<Funcion> optional=repository.findById(id);
		if(optional.isPresent())
			return  optional.get();
		else
			return null; 
	}
	public Iterable<Funcion> getFuncionesPeliSede(Long idSede,Long idPelicula){
		ArrayList<Funcion> funcionesDeSede = new ArrayList<>() ;
		for(Sala sala: sedeService.getSede(idSede).getSalas()) {
				funcionesDeSede.addAll(
					(Collection<? extends Funcion>) getFuncionesPeliSala( peliculaService.getPelicula(idPelicula),sala));
		}
		return funcionesDeSede;
		
	}
	private Iterable<Funcion> getFuncionesPeliSala(Pelicula pelicula, Sala sala) {
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
