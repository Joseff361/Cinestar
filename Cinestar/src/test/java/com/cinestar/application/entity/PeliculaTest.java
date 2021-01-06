package com.cinestar.application.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PeliculaTest {

	@Test
	void test_getId() {
		Pelicula pelicula = new Pelicula();
		pelicula.setId((long)1);
		pelicula.setNombre("NombrePelicula");
		pelicula.setDescripcion("Descripcion...");
		pelicula.setGenero("Drama");
		pelicula.setImagen("logo.jpg");

		assertEquals((long)1, pelicula.getId());
	}
	@Test
	void test_getNombre() {
		Pelicula pelicula = new Pelicula();
		pelicula.setId((long)1);
		pelicula.setNombre("NombrePelicula");
		pelicula.setDescripcion("Descripcion...");
		pelicula.setGenero("Drama");
		pelicula.setImagen("logo.jpg");

		assertEquals("NombrePelicula", pelicula.getNombre());
	}
	@Test
	void test_getDescripcion() {
		Pelicula pelicula = new Pelicula();
		pelicula.setId((long)1);
		pelicula.setNombre("NombrePelicula");
		pelicula.setDescripcion("Descripcion...");
		pelicula.setGenero("Drama");
		pelicula.setImagen("logo.jpg");

		assertEquals("Descripcion...", pelicula.getDescripcion());
	}
	@Test
	void test_getGenero() {
		Pelicula pelicula = new Pelicula();
		pelicula.setId((long)1);
		pelicula.setNombre("NombrePelicula");
		pelicula.setDescripcion("Descripcion...");
		pelicula.setGenero("Drama");
		pelicula.setImagen("logo.jpg");

		assertEquals("Drama", pelicula.getGenero());
	}
	@Test
	void test_getImagen() {
		Pelicula pelicula = new Pelicula();
		pelicula.setId((long)1);
		pelicula.setNombre("NombrePelicula");
		pelicula.setDescripcion("Descripcion...");
		pelicula.setGenero("Drama");
		pelicula.setImagen("logo.jpg");

		assertEquals("logo.jpg", pelicula.getImagen());
	}
	@Test
	void test_getFunciones() {
		Pelicula pelicula = new Pelicula();
		pelicula.setId((long)1);
		pelicula.setNombre("NombrePelicula");
		pelicula.setDescripcion("Descripcion...");
		pelicula.setGenero("Drama");
		pelicula.setImagen("logo.jpg");
		Funcion funcion1=new Funcion();
		Funcion funcion2=new Funcion();
		funcion1.setId((long)1);
		funcion2.setId((long)2);
		Set<Funcion> funciones= new LinkedHashSet<>();
		funciones.add(funcion1);
		funciones.add(funcion2);
		pelicula.setFunciones(funciones);
		assertEquals(funciones, pelicula.getFunciones());
	}
}
