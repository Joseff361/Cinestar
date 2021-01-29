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
	@Test
	void test_insertUsuario() {
		usuarioService.insertUsuario("nombre", "appellido","email@", "NombreAp", "12345");
		Usuario user= (Usuario) usuarioService.getUserByUsername("NombreAp");
		assertEquals("NombreAp",user.getUsername());
		assertEquals("nombre",user.getFirstName());
		assertEquals("appellido",user.getLastName());
		usuarioService.eliminarUsuario(user);
	}

}
