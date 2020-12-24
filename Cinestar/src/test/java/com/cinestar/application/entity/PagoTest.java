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
	void test_getDescripcion() {
		Pago pago = new Pago();
		pago.setId((long) 1);
		pago.setDescripcion("1-1-1");
		pago.setEstado("1");
		pago.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		pago.setMonto((float) 1.5);
		assertEquals("1-1-1",pago.getDescripcion());
	}
	void test_getEstado() {
		Pago pago = new Pago();
		pago.setId((long) 1);
		pago.setDescripcion("1-1-1");
		pago.setEstado("1");
		pago.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		pago.setMonto((float) 1.5);
		assertEquals("1",pago.getEstado());
	}
	void test_getHora() {
		Pago pago = new Pago();
		pago.setId((long) 1);
		pago.setDescripcion("1-1-1");
		pago.setEstado("1");
		pago.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		pago.setMonto((float) 1.5);
		assertEquals(Timestamp.valueOf("2020-12-01 12:30:30"),pago.getHora());
	}
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
