package com.cinestar.application.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.boot.test.context.SpringBootTest;

import com.cinestar.application.entity.Usuario;



@SpringBootTest
class UsuarioServiceTest {
	@Autowired
    UsuarioService usuarioService;

	@Test
	void test_getUserByUsername() {
		Usuario usuario = usuarioService.getUserByUsername("kstrauss0");
		assertEquals("kstrauss0",usuario.getUsername());
	}
	
	@Test
	void test_loadUserByUsername() {
		User user = (User) usuarioService.loadUserByUsername("kstrauss0");
		assertEquals("kstrauss0",user.getUsername());
	}

}
