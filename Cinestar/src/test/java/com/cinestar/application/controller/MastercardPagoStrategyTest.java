package com.cinestar.application.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cinestar.application.entity.Pago;

@SpringBootTest
class MastercardPagoStrategyTest {

	
	@Test
	void test_realizarPago() {
		Pago pago=new Pago();
		realizarPagoN(pago,(float)100.0,(float)80.0,12,1);
		realizarPagoN(pago,(float)10.0,(float)10.0,12,1);
		realizarPagoN(pago,(float)100.0,(float)75.0,12,16);
		realizarPagoN(pago,(float)100.0,(float)100.0,12,30);
		realizarPagoN(pago,(float)10.0,(float)10.0,12,16);
		realizarPagoN(pago,(float)10.0,(float)10.0,12,30);
		realizarPagoN(pago,(float)10.0,(float)10.0,12,25);
		realizarPagoN(pago,(float)100.0,(float)75.0,12,25);
	}
	
	void realizarPagoN(Pago pago,float monto_entrada,float monto_salida,int mes,int dia) {
		pago.setMonto(monto_entrada);
		PagoStrategy pm= new MastercardPagoStrategy();
		assertEquals(monto_salida,pm.realizarPago(pago, LocalDateTime.of(2020, mes, dia, 12, 30).toLocalDate()).getMonto());
	}



}
