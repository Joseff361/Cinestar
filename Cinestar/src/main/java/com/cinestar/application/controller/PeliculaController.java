package com.cinestar.application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import com.cinestar.application.entity.Pelicula;

import com.cinestar.application.service.PeliculaService;

@Controller
public class PeliculaController {

	@Autowired
	PeliculaService peliculaService;
	
	private static final String PELICULA_LIST = "peliculaList"; 

	/**
	 * Retorna lista de Peliculas
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping({"/peliculas","/","/index"})
	public String peliculas(Model model) {
		model.addAttribute(PELICULA_LIST, peliculaService.getPeliculas());
		return "index";// html
	}

	/**
	 * Retorna lista de Peliculas
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/peliculas/categoria")
	public String peliculasCategoria(@RequestParam String genero, Model model) {
		model.addAttribute(PELICULA_LIST, peliculaService.getPeliculasByGenero(genero));
		return "index";// html
	}

	/**
	 * Retorna pelicula por id y las sedes donde esté
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/peliculas/{id}")
	public String peliculasId(@PathVariable Long id, Model model) {
		Pelicula p = peliculaService.getPelicula(id);
		model.addAttribute(PELICULA_LIST, p);
		model.addAttribute("sedeList", peliculaService.getSedesPorPelicula(id));
		return "pelicula";// html
	}
}
