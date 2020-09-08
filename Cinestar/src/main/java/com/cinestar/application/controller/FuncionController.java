package com.cinestar.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FuncionController {
	
	/**
	 * Muestra el tarifario
	 * @return
	 */
	@GetMapping("/tarifario")
	public String tarifario() {
		return "tarifario";//html
	}

}
