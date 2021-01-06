package com.cinestar.application.controller;

import java.time.LocalDate;

import com.cinestar.application.entity.Pago;

public interface PagoStrategy {
		public Pago realizarPago(Pago pago,LocalDate date) ;
}
