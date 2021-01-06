package com.cinestar.application.controller;

import java.time.LocalDate;

import com.cinestar.application.entity.Pago;

public class VisaPagoStrategy implements PagoStrategy{
	public Pago realizarPago(Pago pago,LocalDate date) {
		// Funcionamiento de Pago independiente de la app, al ser servicio de Mastercard
		// Se debe evaluar tarjetas caducadas
		pago.setEstado("1");
		if (date.getDayOfMonth() ==1 && pago.getMonto() >= 30) {
			pago.setMonto((float) (pago.getMonto() * 0.9));
		} 
		
		
		//anteriormente: LocalDateTime.now().getDayOfWeek().equals("SUNDAY")
		if (date.getDayOfWeek().name().equals("SUNDAY") && pago.getMonto() >= 30) {
			pago.setMonto((float) (pago.getMonto() * 1.15));
		}
			
	 
		return pago;

	}

}
