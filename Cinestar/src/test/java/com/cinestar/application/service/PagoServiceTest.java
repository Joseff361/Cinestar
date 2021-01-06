package com.cinestar.application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Usuario;

@SpringBootTest
class PagoServiceTest {
	@Autowired
	PagoService pagoService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	FuncionService funcionService;
	@Test
	void test_previoPago() {
		Funcion funcion=funcionService.getFuncion((long)1);
		Pago pago= pagoService.previoPago(funcion, "A1-A2-A3", "2", "1", "0", "ekornel3");
		assertEquals("2-1-0",pago.getDescripcion());
		assertEquals("0",pago.getEstado());
		pagoService.cancelarPago(pago.getId());
	}


	@Test
	void test_accionPago0() {
		Funcion funcion=funcionService.getFuncion((long)1);
		Pago pago= pagoService.previoPago(funcion, "B1", "1", "0", "0", "ekornel3");
		pagoService.accionPago("11/11","11111111","111",pago.getId().toString(),"0");
		assertEquals("1-0-0",pago.getDescripcion());
		pago=pagoService.getPago(pago.getId());
		assertEquals("1",pago.getEstado());
		pagoService.cancelarPago(pago.getId());
	}
	@Test
	void test_accionPago1() {
		Funcion funcion=funcionService.getFuncion((long)2);
		Pago pago= pagoService.previoPago(funcion, "B1", "1", "0", "0", "ekornel3");
		pagoService.accionPago("11/11","11111111","111",pago.getId().toString(),"1");
		assertEquals("1-0-0",pago.getDescripcion());
		pago=pagoService.getPago(pago.getId());
		assertEquals("1",pago.getEstado());
		pagoService.cancelarPago(pago.getId());
	}
	@Test
	void test_obtenerFechaFuncion() {
		Funcion funcion=funcionService.getFuncion((long)2);
		Pago pago= pagoService.previoPago(funcion, "B1", "1", "0", "0", "ekornel3");
		pagoService.accionPago("11/11","11111111","111",pago.getId().toString(),"0");
		Calendar cal= Calendar.getInstance();
		cal.setTime(funcion.getDia());
		pago=pagoService.getPago(pago.getId());
		assertEquals((cal.get(Calendar.DAY_OF_WEEK)-1),pagoService.obtenerFechaFuncion(pago));
		
		
		pagoService.cancelarPago(pago.getId());
	}
	@Test
	void test_generarStringAsientos() {
		Pago pago = pagoService.getPago((long)1);
		
		pago=pagoService.getPago(pago.getId());
		assertEquals("A1-A2",pagoService.generarStringAsientos(pago));	
		
	}
	@Test
	void test_getPagosUsuario() {
		Usuario usuario= usuarioService.getUserByUsername("kstrauss0");
		for(Pago pago:pagoService.getPagosUsuario(usuario)) {
			assertEquals(usuario.getUsername(),pago.getUser().getUsername());
		}
	}
	@Test
	void test_getPago() {
		Pago pago = pagoService.getPago((long)1);
		assertEquals((long)1,pago.getId());		
	}
	@Test
	void test_getPagoNull() {
		Pago pago = pagoService.getPago((long)0);
		assertNull(pago.getId());		
	}
	@Test
	void test_calculoCostoTotal() {
		Funcion funcion= new Funcion();
		test_calculoCostoTotalN(funcion,"2021-01-04",9);
		test_calculoCostoTotalN(funcion,"2021-01-06",10);
		test_calculoCostoTotalN(funcion,"2021-01-07",15);
		
	}
	void test_calculoCostoTotalN(Funcion funcion,String fecha,float salida) {
		funcion.setDia(Date.valueOf(fecha));
		assertEquals(salida,pagoService.calculoCostoTotal(funcion, "1-0-0"));
		
		
	}

}
