package com.cinestar.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cinestar.application.service.SedeService;

@Controller
public class SedeController {
	
	@Autowired
	SedeService sedeService;
	
	/**
	 * Retorna lista de Sedes
	 * @param model
	 * @return
	 */
	@GetMapping("/sedes")
	public String sedes(Model model) {
		model.addAttribute("sedeList", sedeService.getSedes());

		return "sedes";//html
	}

}
