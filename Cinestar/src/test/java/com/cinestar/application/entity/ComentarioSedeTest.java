package com.cinestar.application.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class ComentarioSedeTest {

	@Test
	void test_getComentario() {
		ComentarioSede cs = new ComentarioSede("Bueno", Timestamp.valueOf("2020-12-01 12:30:30"),"Joas","Guaces", "JJKER");
		cs.setComentario("Mal servici");
		assertEquals("Mal servici",cs.getComentario());
	}
	
	@Test
	void test_getHora() {
		ComentarioSede cs = new ComentarioSede("Bueno", Timestamp.valueOf("2020-12-01 12:30:30"),"Joas","Guaces", "JJKER");
		cs.setHora(Timestamp.valueOf("2021-12-01 12:30:30"));
		assertEquals(Timestamp.valueOf("2021-12-01 12:30:30"),cs.getHora());
	}
	
	@Test
	void test_getFirstName() {
		ComentarioSede cs = new ComentarioSede("Bueno", Timestamp.valueOf("2020-12-01 12:30:30"),"Joas","Guaces", "JJKER");
		cs.setFirstName("Jorge");
		assertEquals("Jorge",cs.getFirstName());
	}
	@Test
	void test_getLastName() {
		ComentarioSede cs = new ComentarioSede("Bueno", Timestamp.valueOf("2020-12-01 12:30:30"),"Joas","Guaces", "JJKER");
		cs.setLastName("Jauja");
		assertEquals("Jauja",cs.getLastName());
	}
	@Test
	void test_getUsername() {
		ComentarioSede cs = new ComentarioSede("Bueno", Timestamp.valueOf("2020-12-01 12:30:30"),"Joas","Guaces", "JJKER");
		cs.setUsername("User");
		assertEquals("User",cs.getUsername());
	}
	
}
