package com.cinestar.application.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cinestar.application.service.SedeService;
import com.cinestar.application.service.UsuarioService;

@Controller
public class PerfilController {
	
	@Autowired
	UsuarioService usuarioService;

	@GetMapping({"/perfil"})
	public String login(Model model, HttpServletRequest request) {
		
		//Recogiendo el usuario desde SpringSecurity y aniadiendolo al modelo
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		model.addAttribute("user", currentPrincipalName);
		
		model.addAttribute("usuario", usuarioService.getUserByUsername(currentPrincipalName));
		
		return "perfil";
	}
}
