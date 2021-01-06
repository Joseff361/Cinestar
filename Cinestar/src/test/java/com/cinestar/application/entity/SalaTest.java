package com.cinestar.application.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SalaTest {

	@Test
	void test_getId() {
		Sala sala = new Sala();
		sala.setId((long)1);
		sala.setNum((long)5);
		assertEquals((long)1,sala.getId());
	}
	@Test
	void test_getNum() {
		Sala sala = new Sala();
		sala.setId((long)1);
		sala.setNum((long)5);
		assertEquals((long)5,sala.getNum());
	}
	@Test
	void test_getFunciones() {
		Sala sala = new Sala();
		sala.setId((long)1);
		sala.setNum((long)5);
		Funcion funcion1=new Funcion();
		Funcion funcion2=new Funcion();
		funcion1.setId((long)1);
		funcion2.setId((long)2);
		Set<Funcion> funciones= new LinkedHashSet<>();
		funciones.add(funcion1);
		funciones.add(funcion2);
		sala.setFunciones(funciones);
		assertEquals(funciones, sala.getFunciones());

	}
	@Test
	void test_get() {
		Sala sala = new Sala();
		sala.setId((long)1);
		sala.setNum((long)5);
		Sede sede=new Sede();
		sede.setId((long)1);
		sala.setSede(sede);
		sala.setSede(sede);
		assertEquals(sede,sala.getSede());
	}
}
