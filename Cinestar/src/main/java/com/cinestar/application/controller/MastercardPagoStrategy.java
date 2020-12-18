package com.cinestar.application.controller;

import java.time.LocalDateTime;

import com.cinestar.application.entity.Pago;

public class MastercardPagoStrategy implements PagoStrategy {
	
		
	public Pago realizarPago(Pago ppago) {
		// Funcionamiento de Pago independiente de la app, al ser servicio de Mastercard

		ppago.setEstado("1");
		if (LocalDateTime.now().getDayOfMonth() <= 15) {
			if (ppago.getMonto() >= 30) {
				ppago.setMonto((float) (ppago.getMonto() * 0.8));
			}

		} else if (LocalDateTime.now().getDayOfMonth() > 15 && LocalDateTime.now().getDayOfMonth() <= 25 && ppago.getMonto() >= 30) {
			ppago.setMonto((float) (ppago.getMonto() * 0.75));

		}
		return ppago;

	}

}
