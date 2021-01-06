package com.cinestar.application.controller;

import java.time.LocalDate;

import com.cinestar.application.entity.Pago;

public class MastercardPagoStrategy implements PagoStrategy {
	
		
	public Pago realizarPago(Pago ppago, LocalDate date) {
		// Funcionamiento de Pago independiente de la app, al ser servicio de Mastercard
		
		
		ppago.setEstado("1");
		if (date.getDayOfMonth() <= 15) {
			if (ppago.getMonto() >= 30) {
				ppago.setMonto((float) (ppago.getMonto() * 0.8));
			}

		} else if (date.getDayOfMonth() > 15 && date.getDayOfMonth() <= 25 && ppago.getMonto() >= 30) {
			ppago.setMonto((float) (ppago.getMonto() * 0.75));

		}
		return ppago;

	}

}
