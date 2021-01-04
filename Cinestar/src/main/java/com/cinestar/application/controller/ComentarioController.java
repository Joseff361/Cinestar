package com.cinestar.application.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Sede;
import com.cinestar.application.service.ComentarioService;
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
	public String peliculasId(@PathVariable Long id, Model model) {
		
			Sede sede = sedeService.getSede(id);

			model.addAttribute("imagen", sede.getImagen());
			model.addAttribute("sede", sede.getNombre());
			model.addAttribute("comentarios", comentarioService.verComentarioSede(sede));
		

		return "comentarios";// html
	}

}
