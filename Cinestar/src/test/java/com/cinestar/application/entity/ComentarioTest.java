package com.cinestar.application.entity;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComentarioTest {

	@Test
	void test_getId() {
		Comentario comentario =new Comentario();
		comentario.setId((long)1);
		comentario.setDescripcion("Buena");
		comentario.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		assertEquals((long)1,comentario.getId());
	}
	@Test
	void test_getDescripcion() {
		Comentario comentario =new Comentario();
		comentario.setId((long)1);
		comentario.setDescripcion("Buena");
		comentario.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		assertEquals("Buena",comentario.getDescripcion());
	}
	@Test
	void test_getHora() {
		Comentario comentario =new Comentario();
		comentario.setId((long)1);
		comentario.setDescripcion("Buena");
		comentario.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		assertEquals(Timestamp.valueOf("2020-12-01 12:30:30"),comentario.getHora());
	}
	@Test
	void test_getPago() {
		Comentario comentario =new Comentario();
		comentario.setId((long)1);
		comentario.setDescripcion("Buena");
		comentario.setHora(Timestamp.valueOf("2020-12-01 12:30:30"));
		Pago pago= new Pago();
		pago.setId((long)15);
		comentario.setPago(pago);
		//Comentario comentario = new Comentario((long)1,"Buena",Timestamp.valueOf("2020-12-01 12:30:30"),pago);
		assertEquals(pago,comentario.getPago());
	}

}
