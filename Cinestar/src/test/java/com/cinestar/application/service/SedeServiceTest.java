package com.cinestar.application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pelicula;
import com.cinestar.application.entity.Sede;

@SpringBootTest
class SedeServiceTest {
	@Autowired
	SedeService sedeService;
	
	@Test
	void test_getSede() {
		Sede sede = sedeService.getSede((long)1);
		assertEquals((long)1,sede.getId());
	}
	@Test
	void test_getSedeNull() {
		Sede sede = sedeService.getSede((long)0);
		assertNull(sede);
	}
	@Test
	void test_getSedes() {
		Iterable<Sede> lista=  sedeService.getSedes();
		Iterator<Sede> i=lista.iterator();
		Sede previo= i.next();
		Sede actual;	
		int dif;
		while(i.hasNext()) {
			actual=i.next();
			dif=previo.getNombre().compareTo(actual.getNombre());
			assertEquals(true,dif<0);
			previo=actual;	
		}
	}
	@Test
	void test_getPeliculasPorSede() {
		Iterable<Pelicula> lista= sedeService.getPeliculasPorSede((long)1);
		Iterator<Pelicula> i=lista.iterator();
		Pelicula actual;
		while(i.hasNext()) {
			actual=i.next();
			assertEquals(true,test_getPeliculaPorSedeInterno(actual));	
		}
	}

	@Test
	void test_getPeliculasPorSedeGenero() {
		Iterable<Pelicula> lista= sedeService.getPeliculasPorSedeGenero((long)1,"Terror");
		Iterator<Pelicula> i=lista.iterator();
		Pelicula actual;
		while(i.hasNext()) {
			actual=i.next();
			assertEquals(true,test_getPeliculaPorSedeInterno(actual));	
			assertEquals("Terror",actual.getGenero());
		}
	}
	Boolean test_getPeliculaPorSedeInterno(Pelicula pelicula) {
		for(Funcion funcion:pelicula.getFunciones()) {
			if(funcion.getSala().getSede().getId()==(long)1)
				return true;
		}
		return false;
	}


}
