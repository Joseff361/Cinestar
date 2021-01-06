package com.cinestar.application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cinestar.application.entity.Funcion;
@SpringBootTest
class FuncionServiceTest {
	@Autowired
	FuncionService funcionService;
	@Test
	void test_getFunciones() {
		Iterable<Funcion> lista=  funcionService.getFunciones();
		Iterator<Funcion> i=lista.iterator();
		Funcion actual;	
		Calendar cal=Calendar.getInstance();
		while(i.hasNext()) {
			actual=i.next();  
			cal.setTime(actual.getDia());
			assertEquals(true,cal.get(Calendar.DAY_OF_WEEK_IN_MONTH)<26);
		}
	}
	@Test
	void test_getFuncion() {
		Funcion funcion = funcionService.getFuncion((long)1);
		assertEquals((long)1,funcion.getId());
	}
	@Test
	void test_getFuncionNull() {
		Funcion funcion = funcionService.getFuncion((long)0);
		assertNull(funcion);
	}
	@Test
	void test_getFuncionesPeliSede1() {
		Iterable<Funcion> lista=  funcionService.getFuncionesPeliSede((long)1,(long)1);
		Iterator<Funcion> i=lista.iterator();
		Funcion actual;	
		
		while(i.hasNext()) {
			actual=i.next();
			assertEquals((long)1,actual.getPelicula().getId());
			assertEquals((long)1,actual.getSala().getSede().getId());
			
		}
	}
	@Test
	void test_getFuncionesPeliSede28() {
		Iterable<Funcion> lista=  funcionService.getFuncionesPeliSede((long)3,(long)10);
		Iterator<Funcion> i=lista.iterator();
		Funcion actual;	
		
		while(i.hasNext()) {
			actual=i.next();
			assertEquals((long)1,actual.getPelicula().getId());
			assertEquals((long)1,actual.getSala().getSede().getId());
			
		}
	}

}
