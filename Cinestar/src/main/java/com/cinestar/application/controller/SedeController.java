package com.cinestar.application.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinestar.application.service.SedeService;

@Controller
public class SedeController {

	@Autowired
	SedeService sedeService;

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
		model.addAttribute("sede", sedeService.getSede(id));
		model.addAttribute("peliculaList", sedeService.getPeliculasPorSede(id));
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

		
		model.addAttribute("peliculaList", sedeService.getPeliculasPorSede(id));
		model.addAttribute("sede", sedeService.getSede(id));
		return "sedes-pelicula";// html
	}
}
