package com.cinestar.application.entity;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cinestar.application.repository.AsientoRepository;
import com.cinestar.application.repository.FuncionRepository;

@SpringBootTest
class AsientoTest {
	AsientoRepository repoAsiento;
    FuncionRepository repoFuncion;
    
	@Test
	void test_getId() {
		Asiento asiento= new Asiento();
		asiento.setId((long)1);
		asiento.setIdFila("A");
		asiento.setIdColumna(2);
		Pago pago= new Pago();
		pago.setId((long) 10);
		asiento.setPago(pago);
		assertEquals((long)1,asiento.getId());
		
	}
	@Test
	void test_getIdFila() {
		Asiento asiento= new Asiento();
		asiento.setId((long)1);
		asiento.setIdFila("A");
		asiento.setIdColumna(2);	
		assertEquals("A",asiento.getIdFila());
		
	}
	@Test
	void test_getIdColumna() {
		Asiento asiento= new Asiento();
		asiento.setId((long)1);
		asiento.setIdFila("A");
		asiento.setIdColumna(2);	
		assertEquals(2,asiento.getIdColumna());
		
	}
	@Test
	void test_getFuncion() {
		Asiento asiento= new Asiento();
		asiento.setId((long)1);
		asiento.setIdFila("A");
		asiento.setIdColumna(2);
		Funcion funcion= new Funcion();
		funcion.setId((long) 10);
		asiento.setFuncion(funcion);
		assertEquals(funcion,asiento.getFuncion());	
	}

	@Test
	void test_getPago() {
		Asiento asiento= new Asiento();
		asiento.setId((long)1);
		asiento.setIdFila("A");
		asiento.setIdColumna(2);
		Pago pago= new Pago();
		pago.setId((long) 10);
		asiento.setPago(pago);
		assertEquals(pago,asiento.getPago());
		
	}

}
