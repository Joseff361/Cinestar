package com.cinestar.application.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SedeTest {

	@Test
	void test_getId() {
		Sede sede= new Sede();
		sede.setId((long)1);
		sede.setNombre("Aviacion");
		sede.setDireccion("Av Aviacion");
		sede.setImagen("Aviacion.jpg");
		
		assertEquals((long)1,sede.getId());
	}
	@Test
	void test_getNombre() {
		Sede sede= new Sede();
		sede.setId((long)1);
		sede.setNombre("Aviacion");
		sede.setDireccion("Av Aviacion");
		sede.setImagen("Aviacion.jpg");
		
		assertEquals("Aviacion",sede.getNombre());
	}
	@Test
	void test_getDireccion() {
		Sede sede= new Sede();
		sede.setId((long)1);
		sede.setNombre("Aviacion");
		sede.setDireccion("Av Aviacion");
		sede.setImagen("Aviacion.jpg");
		
		assertEquals("Av Aviacion",sede.getDireccion());
	}
	@Test
	void test_getImagen() {
		Sede sede= new Sede();
		sede.setId((long)1);
		sede.setNombre("Aviacion");
		sede.setDireccion("Av Aviacion");
		sede.setImagen("Aviacion.jpg");
		
		assertEquals("Aviacion.jpg",sede.getImagen());
	}
	@Test
	void test_getSalas() {
		Sede sede= new Sede();
		sede.setId((long)1);
		sede.setNombre("Aviacion");
		sede.setDireccion("Av Aviacion");
		sede.setImagen("Aviacion.jpg");
		Sala sala1=new Sala();
		Sala sala2=new Sala();
		sala1.setId((long)1);
		sala2.setId((long)2);
		Set<Sala> salas= new LinkedHashSet<>();
		salas.add(sala1);
		salas.add(sala2);
		sede.setSalas(salas);
		assertEquals(salas,sede.getSalas());
	}
}
