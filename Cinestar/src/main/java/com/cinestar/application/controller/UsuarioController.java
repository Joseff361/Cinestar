package com.cinestar.application.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinestar.application.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping({"/login"})
	public String login() {
		return "login";
	}

	@PostMapping("/registrar-usuario")
	public String registrarUsuario(Model model, HttpServletRequest request,
			@RequestParam("name") String name, @RequestParam("username") String username,
			@RequestParam("lastname") String lastname, @RequestParam("email") String email, @RequestParam("password") String password
			) {
		
		usuarioService.insertUsuario(name, lastname, email, username, password);
		return "redirect:/login";// html
	}
}