package com.cinestar.application.service;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Iterator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pelicula;
import com.cinestar.application.entity.Sala;
import com.cinestar.application.entity.Sede;

@SpringBootTest
class PeliculaServiceTest {
	@Autowired
	PeliculaService peliculaService;
	@Autowired
	PeliculaService funcionService;
	@Test
	void test_getPeliculas() {
		Iterable<Pelicula> lista=  peliculaService.getPeliculas();
		Iterator<Pelicula> i=lista.iterator();
		Pelicula previo= i.next();
		Pelicula actual;	
		int dif;
		while(i.hasNext()) {
			actual=i.next();
			dif=previo.getNombre().compareTo(actual.getNombre());
			assertEquals(true,dif<0);
			previo=actual;	
		}
	}
	@Test
	void test_getPeliculasByGenero() {
		Iterable<Pelicula> lista=  peliculaService.getPeliculasByGenero("Terror");
		Iterator<Pelicula> i=lista.iterator();
		Pelicula previo= i.next();
		Pelicula actual;	
		int dif;
		while(i.hasNext()) {
			actual=i.next();
			dif=previo.getNombre().compareTo(actual.getNombre());
			assertEquals("Terror",previo.getGenero());
			assertEquals(true,dif<0);
			previo=actual;	
		}
	}
	@Test
	void test_getPelicula() {
		Pelicula pelicula = peliculaService.getPelicula((long)1);
		assertEquals((long)1,pelicula.getId());
	}
	@Test
	void test_getPeliculaNull() {
		Pelicula pelicula = peliculaService.getPelicula((long)0);
		assertNull(pelicula);
	}
	@Test
	void test_getSedesPorPelicula() {
		Iterable<Sede> lista= peliculaService.getSedesPorPelicula((long)1);
		Iterator<Sede> i=lista.iterator();
		Sede actual;
		while(i.hasNext()) {
			actual=i.next();
			assertEquals(true,test_getSedePorPeliculaInterno(actual));	
		}
	}
	Boolean test_getSedePorPeliculaInterno(Sede sede) {
		for(Sala sala:sede.getSalas()) {
			for(Funcion funcion:sala.getFunciones()) {
				if(funcion.getPelicula().getId().equals((long)1)) {
					return true;
				}
			}
		}
		return false;
	}

}
