package com.cinestar.application.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PagoTest {

	@Test
	void test_getId() {
		Pago pago = new Pago();
		pago.setId((long) 1);
		pago.setDescripcion("1-1-1");
		pago.setEstado("1");
		pago.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		pago.setMonto((float) 1.5);
		assertEquals((long)1,pago.getId());
	}
	@Test
	void test_getMonto() {
		Pago pago = new Pago();
		pago.setId((long) 1);
		pago.setDescripcion("1-1-1");
		pago.setEstado("1");
		pago.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		pago.setMonto((float) 1.5);
		assertEquals((float) 1.5,pago.getMonto());
	}
	@Test
	void test_getDescripcion() {
		Pago pago = new Pago();
		pago.setId((long) 1);
		pago.setDescripcion("1-1-1");
		pago.setEstado("1");
		pago.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		pago.setMonto((float) 1.5);
		assertEquals("1-1-1",pago.getDescripcion());
	}
	@Test
	void test_getUser() {
		Pago pago = new Pago();
		pago.setId((long) 1);
		pago.setDescripcion("1-1-1");
		pago.setEstado("1");
		pago.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		pago.setMonto((float) 1.5);
		
		Usuario usuario=new Usuario();
		usuario.setId((long)1);
		usuario.setEmail("j.com");
		usuario.setFirstName("Joas");
		usuario.setLastName("Guares");
		usuario.setPassword("12345");
		usuario.setUsername("JJKER");
		
		pago.setUser(usuario);
		
		assertEquals(usuario,pago.getUser());
	}
	@Test
	void test_getEstado() {
		Pago pago = new Pago();
		pago.setId((long) 1);
		pago.setDescripcion("1-1-1");
		pago.setEstado("1");
		pago.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		pago.setMonto((float) 1.5);
		assertEquals("1",pago.getEstado());
	}
	@Test
	void test_getHora() {
		Pago pago = new Pago();
		pago.setId((long) 1);
		pago.setDescripcion("1-1-1");
		pago.setEstado("1");
		pago.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		pago.setMonto((float) 1.5);
		assertEquals(Timestamp.valueOf("2020-12-01 12:30:30"),pago.getHora());
	}
	@Test
	void test_getAsientos() {
		Pago pago = new Pago();
		pago.setId((long) 1);
		pago.setDescripcion("1-1-1");
		pago.setEstado("1");
		pago.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		pago.setMonto((float) 1.5);
		Set<Asiento> asientos=new LinkedHashSet<>();
		Asiento asiento1 = new Asiento();
		Asiento asiento2 = new Asiento();
		asiento1.setId((long)1);
		asiento2.setId((long)2);
		asientos.add(asiento1);
		asientos.add(asiento2);
		pago.setAsientos(asientos);
		assertEquals(asientos,pago.getAsientos());
	}



}
