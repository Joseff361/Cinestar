package com.cinestar.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cinestar.application.entity.User;
import com.cinestar.application.service.UserService;

@Controller
public class UserController {
	

	
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "login"; //automaticamente buscara en la carpeta templates
	}
	
	@GetMapping("/userForm")
	public String userForm(Model model) {
		//model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getAllUsers());
		//model.addAttribute("roles",roleRepository.findAll());
		//model.addAttribute("listTab","active");
		//return "user-form/user-view";
		return "user-list";//html
	}
}
