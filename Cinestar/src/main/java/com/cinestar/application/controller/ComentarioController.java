package com.cinestar.application.controller;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinestar.application.entity.Comentario;
import com.cinestar.application.entity.ComentarioSede;
import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Pelicula;
import com.cinestar.application.entity.Sede;
import com.cinestar.application.service.ComentarioService;
import com.cinestar.application.service.FuncionService;
import com.cinestar.application.service.PagoService;
import com.cinestar.application.service.SedeService;

@Controller
public class ComentarioController {
	
	@Autowired
	ComentarioService comentarioService;
	@Autowired
	PagoService pagoService;
	@Autowired
	SedeService sedeService;
	
	@GetMapping("/comentar")
	public String sedes(Model model, HttpServletRequest request,
			@RequestParam("id") String id, @RequestParam("comentario") String comentario) {

		Pago pago = pagoService.getPago(Long.parseLong(id));
		comentarioService.enviarComentario(comentario, pago);
		return "redirect:/tabla-pagos";// html
	}
	
	@GetMapping("/comentarios/{id}")
	public String peliculas_id(@PathVariable Long id, Model model) {
		
		Sede sede = sedeService.getSede(id).get();

		Set<ComentarioSede> comentariosSede = new LinkedHashSet<>();

		for (Comentario comentario: comentarioService.verComentarioPorSede(sede)) {
			//Nuevo objeto comentarioSede
			ComentarioSede comentarioSede = new ComentarioSede(comentario.getDescripcion(), comentario.getHora(),
					comentario.getPago().getUser().getFirstName(), comentario.getPago().getUser().getLastName(), 
					comentario.getPago().getUser().getUsername());
			System.out.println("=================> " + comentarioSede.getComentario());
			comentariosSede.add(comentarioSede);
		}
		
		model.addAttribute("imagen", sede.getImagen());
		model.addAttribute("sede", sede.getNombre());
		model.addAttribute("comentarios", comentariosSede);

		return "comentarios";// html
	}

}
