package com.cinestar.application.service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pelicula;
import com.cinestar.application.entity.Sede;
import com.cinestar.application.repository.PeliculaRepository;

@Service
public class PeliculaService {
	@Autowired
	PeliculaRepository repository;
	@Autowired
	FuncionService funcionService;
	public Iterable<Pelicula> getPeliculas() {
		return repository.findAllByOrderByNombreAsc();
		
	}

	public Iterable<Pelicula> getPeliculasByGenero(String genero) {
		return repository.findAllByGeneroOrderByNombreAsc(genero);
	}

	public Pelicula getPelicula(Long id) {
		Optional<Pelicula> optional= repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			return null;
		}
	}
	
	public Iterable<Sede> getSedesPorPelicula(Long id){
		Set<Sede> sedes = new LinkedHashSet<>();
		for (Funcion func : funcionService.getFunciones()) {

			/* guardar en un lista de sedes todas las funciones con la peli ID
			   el operador == puede fallar, java no tiene buen soporte con el objeto Long*/
			if (id != null && func.getPelicula().getId()!= null && func.getPelicula().getId().equals(id)) {
				sedes.add(func.getSala().getSede());
			}

		}
		return sedes;
	}
}
