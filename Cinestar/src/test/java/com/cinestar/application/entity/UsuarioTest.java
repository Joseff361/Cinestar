package com.cinestar.application.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsuarioTest {

	@Test
	void test_getId() {
		Usuario usuario=new Usuario();
		usuario.setId((long)1);
		usuario.setEmail("j.com");
		usuario.setFirstName("Joas");
		usuario.setLastName("Guares");
		usuario.setPassword("12345");
		usuario.setUsername("JJKER");
	
		assertEquals((long)1,usuario.getId());
	}
	@Test
	void test_getEmail() {
		Usuario usuario=new Usuario();
		usuario.setId((long)1);
		usuario.setEmail("j.com");
		usuario.setFirstName("Joas");
		usuario.setLastName("Guares");
		usuario.setPassword("12345");
		usuario.setUsername("JJKER");
	
		assertEquals("j.com",usuario.getEmail());
	}
	@Test
	void test_getFirstName() {
		Usuario usuario=new Usuario();
		usuario.setId((long)1);
		usuario.setEmail("j.com");
		usuario.setFirstName("Joas");
		usuario.setLastName("Guares");
		usuario.setPassword("12345");
		usuario.setUsername("JJKER");
	
		assertEquals("Joas",usuario.getFirstName());
	}
	@Test
	void test_getLastName() {
		Usuario usuario=new Usuario();
		usuario.setId((long)1);
		usuario.setEmail("j.com");
		usuario.setFirstName("Joas");
		usuario.setLastName("Guares");
		usuario.setPassword("12345");
		usuario.setUsername("JJKER");
	
		assertEquals("Guares",usuario.getLastName());
	}
	@Test
	void test_getPassword() {
		Usuario usuario=new Usuario();
		usuario.setId((long)1);
		usuario.setEmail("j.com");
		usuario.setFirstName("Joas");
		usuario.setLastName("Guares");
		usuario.setPassword("12345");
		usuario.setUsername("JJKER");
	
		assertEquals("12345",usuario.getPassword());
	}
	@Test
	void test_getUsername() {
		Usuario usuario=new Usuario();
		usuario.setId((long)1);
		usuario.setEmail("j.com");
		usuario.setFirstName("Joas");
		usuario.setLastName("Guares");
		usuario.setPassword("12345");
		usuario.setUsername("JJKER");
	
		assertEquals("JJKER",usuario.getUsername());
	}

}
