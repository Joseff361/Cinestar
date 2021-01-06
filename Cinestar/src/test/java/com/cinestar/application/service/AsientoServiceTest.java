package com.cinestar.application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cinestar.application.entity.Asiento;
import com.cinestar.application.entity.Funcion;
@SpringBootTest
class AsientoServiceTest {
	@Autowired
	AsientoService asientoService;
	@Autowired
	FuncionService funcionService;
	
	@Test
	void test_findByColumnaAndByFilaAndByFuncion() {
		
		Funcion funcion= funcionService.getFuncion((long)1);
		Iterable<Asiento> asientos= asientoService.findByColumnaAndByFilaAndByFuncion("B", 2,funcion );
		Iterator<Asiento> i=asientos.iterator();
		Asiento asiento=i.next();
		
		assertEquals("B",asiento.getIdFila());
		assertEquals(2,asiento.getIdColumna());
	}
	@Test
	void test_findAsientos() {

		Funcion funcion= funcionService.getFuncion((long)1);
		Iterable<Asiento> asientos= asientoService.findAsientos(funcion );
		Iterator<Asiento> i=asientos.iterator();		
		Asiento actual;	
		while(i.hasNext()) {
			actual=i.next();
			assertEquals((long)1,actual.getFuncion().getId());
		}
	}
	
}
