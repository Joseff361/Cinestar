package com.cinestar.application.entity;




import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FuncionTest {
	@Test
	void test_getId() {
		Funcion funcion= new Funcion();
		funcion.setId((long)1);
		funcion.setHora("3:00");
		funcion.setDia(Date.valueOf("2020-12-01"));
		funcion.setPrecio((float)3.00);
		
		assertEquals((long)1, funcion.getId());
	}

	@Test
	void test_getHora() {
		Funcion funcion= new Funcion();
		funcion.setId((long)1);
		funcion.setHora("3:00");
		funcion.setDia(Date.valueOf("2020-12-01"));
		funcion.setPrecio((float)3.00);
		
		assertEquals("3:00", funcion.getHora());
	}
	@Test
	void test_getDia() {
		Funcion funcion= new Funcion();
		funcion.setId((long)1);
		funcion.setHora("3:00");
		funcion.setDia(Date.valueOf("2020-12-01"));
		funcion.setPrecio((float)3.00);
		
		assertEquals(Date.valueOf("2020-12-01"), funcion.getDia());
	}
	@Test
	void test_getPrecio() {
		Funcion funcion= new Funcion();
		funcion.setId((long)1);
		funcion.setHora("3:00");
		funcion.setDia(Date.valueOf("2020-12-01"));
		funcion.setPrecio((float)3.00);
		
		assertEquals((float)3.00, funcion.getPrecio());
	}
	@Test
	void test_getTipo() {
		Funcion funcion= new Funcion();
		funcion.setId((long)1);
		funcion.setHora("3:00");
		funcion.setDia(Date.valueOf("2020-12-01"));
		funcion.setPrecio((float)3.00);
		funcion.setTipo("3D");

		assertEquals("3D", funcion.getTipo());

	}
	@Test
	void test_getPelicula() {
		Funcion funcion= new Funcion();
		funcion.setId((long)1);
		funcion.setHora("3:00");
		funcion.setDia(Date.valueOf("2020-12-01"));
		funcion.setPrecio((float)3.00);
		Pelicula pelicula=new Pelicula();
		pelicula.setId((long)2);
		funcion.setPelicula(pelicula);
		assertEquals(pelicula, funcion.getPelicula());


	}
	@Test
	void test_getSala() {
		Funcion funcion= new Funcion();
		funcion.setId((long)1);
		funcion.setHora("3:00");
		funcion.setDia(Date.valueOf("2020-12-01"));
		funcion.setPrecio((float)3.00);
		funcion.setTipo("3D");

		Sala sala=new Sala();
		sala.setId((long)2);
		funcion.setSala(sala);
		assertEquals(sala, funcion.getSala());
	}
	@Test
	void test_getAsientos() {
		Funcion funcion= new Funcion();
		funcion.setId((long)1);
		funcion.setHora("3:00");
		funcion.setDia(Date.valueOf("2020-12-01"));
		funcion.setPrecio((float)3.00);
		funcion.setTipo("3D");
		
		Set<Asiento> asientos=new LinkedHashSet<>();
		Asiento asiento1 = new Asiento();
		Asiento asiento2 = new Asiento();
		asiento1.setId((long)1);
		asiento2.setId((long)2);
		asientos.add(asiento1);
		asientos.add(asiento2);
		
		funcion.setAsientos(asientos);
		assertEquals(asientos,funcion.getAsientos());
	}
}