package com.cinestar.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cinestar.application.service.PeliculaService;

@Controller
public class PeliculaController {
	
	@Autowired
	PeliculaService peliculaService;
	
	/**
	 * Retorna lista de Peliculas
	 * @param model
	 * @return
	 */
	@GetMapping("/peliculas")
	public String peliculas(Model model) {
		model.addAttribute("peliculaList", peliculaService.getPeliculas());

		return "peliculas";//html
	}
}
