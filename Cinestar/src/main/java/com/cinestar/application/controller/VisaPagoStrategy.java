package com.cinestar.application.controller;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.cinestar.application.entity.Pago;

public class VisaPagoStrategy implements PagoStrategy{
	public Pago RealizarPago(Pago P) {
		// Funcionamiento de Pago independiente de la app, al ser servicio de Mastercard

		if (!TarjetaAnulada(P)) {
			P.setEstado("1");
			if (LocalDateTime.now().getDayOfMonth() ==1) {
				if (P.getMonto() >= 30) {
					P.setMonto((float) (P.getMonto() * 0.9));
				}

			} 
			//anteriormente: LocalDateTime.now().getDayOfWeek().equals("SUNDAY")
			if (LocalDateTime.now().getDayOfWeek().name().equals("SUNDAY"));
				if (P.getMonto() >= 30) {
					P.setMonto((float) (P.getMonto() * 1.15));
				}
		} 
		return P;

	}

	private boolean TarjetaAnulada(Pago P) {
		return false;
	}
}
