package com.cinestar.application.controller;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pelicula;
import com.cinestar.application.entity.Sede;
import com.cinestar.application.service.FuncionService;
import com.cinestar.application.service.SedeService;

@Controller
public class SedeController {

	@Autowired
	SedeService sedeService;
	@Autowired
	FuncionService funcionService;

	/**
	 * Retorna lista de Sedes
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/sedes")
	public String sedes(Model model) {
		model.addAttribute("sedeList", sedeService.getSedes());

		return "sedes";// html
	}

	/**
	 * Retorna lista de peliculas en sede id
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/sedes/{id}")
	public String peliculasId(@PathVariable Long id, Model model) {

		Set<Pelicula> peliculas = new LinkedHashSet<>();

		for (Funcion func : funcionService.getFunciones()) {

			// guardar en un lista de Peliculas de todas las funciones que tenga la sede ID
			if (id != null && func.getSala().getSede().getId()!= null && func.getSala().getSede().getId().equals(id)) {
				peliculas.add(func.getPelicula());
			}

		}
		
		Optional<Sede> optional = sedeService.getSede(id);
		if(optional.isPresent()) {
			model.addAttribute("sede", optional.get());
		}
		model.addAttribute("peliculaList", peliculas);

		return "sede-peliculas";// html
	}

	/**
	 * Retorna lista de peliculas en sede id por categoria
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/sedes/{id}/categoria")
	public String peliculasIdCategoria(@PathVariable Long id, @RequestParam String genero, Model model) {

		Set<Pelicula> peliculas = new LinkedHashSet<>();

		for (Funcion func : funcionService.getFunciones()) {

			// guardar en un lista de Peliculas de todas las funciones que tenga la sede ID
			if (id!=null && func.getSala().getSede().getId()!=null && func.getSala().getSede().getId().equals(id) &&
					func.getPelicula().getGenero().equals(genero)) {
					peliculas.add(func.getPelicula());
			}

		}
		model.addAttribute("peliculaList", peliculas);
		model.addAttribute("sede", sedeService.getSede(id));
		return "sedes-pelicula";// html
	}
}
