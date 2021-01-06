package com.cinestar.application.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cinestar.application.entity.Pago;

@SpringBootTest
class VisaPagoStrategyTest {

	@Test
	void test_realizarPago() {
		Pago pago=new Pago();
		realizarPagoN(pago,(float)100.0,(float)90.0,12,1);
		realizarPagoN(pago,(float)100.0,(float)100.0,12,2);
		realizarPagoN(pago,(float)10.0,(float)10.0,12,1);
		realizarPagoN(pago,(float)10.0,(float)10.0,12,2);
		realizarPagoN(pago,(float)100.0,(float)103.5,11,1);
		realizarPagoN(pago,(float)100.0,(float)115.0,12,6);
		realizarPagoN(pago,(float)10.0,(float)10.0,11,1);
		realizarPagoN(pago,(float)10.0,(float)10.0,12,6);
	}
	
	void realizarPagoN(Pago pago,float monto_entrada,float monto_salida,int mes,int dia) {
		pago.setMonto(monto_entrada);
		PagoStrategy pm= new VisaPagoStrategy();
		assertEquals(monto_salida,pm.realizarPago(pago, LocalDateTime.of(2020, mes, dia, 12, 30).toLocalDate()).getMonto());
	
	}

}
