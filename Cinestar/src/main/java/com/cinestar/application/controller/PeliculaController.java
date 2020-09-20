package com.cinestar.application.controller;

import java.util.HashSet;
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
import com.cinestar.application.service.PeliculaService;

@Controller
public class PeliculaController {

	@Autowired
	PeliculaService peliculaService;
	@Autowired
	FuncionService funcionService;

	/**
	 * Retorna lista de Peliculas
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/peliculas")
	public String peliculas(Model model) {
		model.addAttribute("peliculaList", peliculaService.getPeliculas());
		return "index";// html
	}

	/**
	 * Retorna lista de Peliculas
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/peliculas/categoria")
	public String peliculas_categoria(@RequestParam String genero, Model model) {
		model.addAttribute("peliculaList", peliculaService.getPeliculasByGenero(genero));
		return "index";// html
	}

	/**
	 * Retorna pelicula por id y las sedes donde esté
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/peliculas/{id}")
	public String peliculas_id(@PathVariable Long id, Model model) {
		Pelicula p = peliculaService.getPelicula(id).get();
		Set<Sede> sedes = new HashSet<>();

		for (Funcion func : funcionService.getFunciones()) {

			// guardar en un lista de sedes todas las funciones con la peli ID
			if (func.getPelicula().getId() == id) {
				sedes.add(func.getSala().getSede());
			}

		}
		model.addAttribute("peliculaList", p);
		model.addAttribute("sedeList", sedes);
		return "pelicula";// html
	}
}
