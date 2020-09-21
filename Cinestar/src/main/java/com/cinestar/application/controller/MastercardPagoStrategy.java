package com.cinestar.application.controller;

import java.time.LocalDateTime;

import com.cinestar.application.entity.Pago;

public class MastercardPagoStrategy implements PagoStrategy {
	public Pago RealizarPago(Pago P) {
		// Funcionamiento de Pago independiente de la app, al ser servicio de Mastercard

		if (Comprobacion(P)) {
			P.setEstado("1");
			if (LocalDateTime.now().getDayOfMonth() <= 15) {
				if (P.getMonto() >= 30) {
					P.setMonto((float) (P.getMonto() * 0.8));
				}

			} else if (LocalDateTime.now().getDayOfMonth() > 15 && LocalDateTime.now().getDayOfMonth() <= 25)
				if (P.getMonto() >= 30) {
					P.setMonto((float) (P.getMonto() * 0.75));
				}
		} 
		return P;

	}

	private boolean Comprobacion(Pago P) {
		return true;
	}

}
