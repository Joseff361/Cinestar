package com.cinestar.application.service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pelicula;
import com.cinestar.application.entity.Sede;
import com.cinestar.application.repository.SedeRepository;

@Service
public class SedeService {
	@Autowired
	SedeRepository repository;
	@Autowired
	FuncionService funcionService;
	public Iterable<Sede> getSedes() {
		return repository.findAllByOrderByNombreAsc();
	}

	public Sede getSede(Long id) {
		Optional<Sede> optional= repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}
	public Iterable<Pelicula> getPeliculasPorSede(Long id){
		Set<Pelicula> peliculas = new LinkedHashSet<>();
		for (Funcion func : funcionService.getFunciones()) {
			// guardar en un lista de Peliculas de todas las funciones que tenga la sede ID
			if (id != null && func.getSala().getSede().getId()!= null && func.getSala().getSede().getId().equals(id)) {
				peliculas.add(func.getPelicula());
			}

		}
		return peliculas;
	}
	public Iterable<Pelicula> getPeliculasPorSedeGenero(Long id, String genero){
		Set<Pelicula> peliculas = new LinkedHashSet<>();
		for (Funcion func : funcionService.getFunciones()) {
			// guardar en un lista de Peliculas de todas las funciones que tenga la sede ID
			if (id!=null && func.getSala().getSede().getId()!=null && func.getSala().getSede().getId().equals(id) &&
					func.getPelicula().getGenero().equals(genero)) {
					peliculas.add(func.getPelicula());
			}

		}
		return peliculas;
	}
}
