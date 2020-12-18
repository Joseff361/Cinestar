package com.cinestar.application.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pelicula;
import com.cinestar.application.entity.Sala;
import com.cinestar.application.entity.Sede;
import com.cinestar.application.service.FuncionService;
import com.cinestar.application.service.PeliculaService;
import com.cinestar.application.service.SedeService;

@Controller
public class FuncionController {
	
	@Autowired
	FuncionService funcionService;
	@Autowired
	SedeService sedeService;
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
		
		ArrayList<Funcion> funcionesDeSede = new ArrayList<>() ;
		Optional<Sede> optional = sedeService.getSede(id2);
		
		if(optional.isPresent()) {
			for(Sala sala: optional.get().getSalas()) {
				Optional<Pelicula> optional2 = peliculaService.getPelicula(id1);
				if(optional2.isPresent()) {
					funcionesDeSede.addAll(
							(Collection<? extends Funcion>) funcionService.getFuncionesPeliSede(optional2.get(),sala));
				}
			}
		}
		
		Optional<Pelicula> optional3 = peliculaService.getPelicula(id1);
		if(optional3.isPresent()) {
			model.addAttribute("pelicula", optional3.get());
		}
				
		model.addAttribute("funcionList",funcionesDeSede );

		return "funciones";//Html;
	}
	/**
	 * Muestra el tarifario
	 * @return
	 */
	@GetMapping("/cine")
	public String nosotros() {
		return "cine";//html
	}
}
