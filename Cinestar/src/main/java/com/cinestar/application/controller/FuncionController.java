package com.cinestar.application.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.service.FuncionService;
import com.cinestar.application.service.PeliculaService;

@Controller
public class FuncionController {
	
	@Autowired
	FuncionService funcionService;
	@Autowired
	PeliculaService peliculaService;
	
	/**
	 * Muestra el tarifario
	 * @return
	 */
	@GetMapping("/tarifario")
	public String tarifario() {
		return "tarifario";//html
	}
	/**
	 * Retorna funciones por id de sede e id de peliculas
	 * @param model
	 * @return
	 */
	@GetMapping("/peliculas/{id1}/sedes/{id2}/funciones")
	public String funciones(@PathVariable Long id1,@PathVariable Long id2, Model model) {
		ArrayList<Funcion> funcionesDeSede = (ArrayList<Funcion>) funcionService.getFuncionesPeliSede(id2, id1);
		model.addAttribute("pelicula", peliculaService.getPelicula(id1));
		model.addAttribute("funcionList",funcionesDeSede );
		return "funciones";
	}
	/**
	 * Muestra el tarifario
	 * @return
	 */
	@GetMapping("/cine")
	public String nosotros() {
		return "cine";
	}
}
