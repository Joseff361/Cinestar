package com.cinestar.application.service;

import java.sql.Timestamp;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.entity.Asiento;
import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.User;
import com.cinestar.application.repository.PagoRepository;

@Service
public class PagoService {
	@Autowired
	PagoRepository repository;

	public Iterable<Pago> getPagosFuncion(Funcion funcion) {
		return repository.findAllByFuncion(funcion);
	}

	public void realizarPago(Funcion funcion, User user, Set<Asiento> asientos) {
		Pago P = new Pago();
		P.setAsientos(asientos);
		P.setHora(new Timestamp(System.currentTimeMillis()));
		P.setId(ConteoPagos());
		P.setMonto(funcion.getPrecio() * asientos.size());// Ver costo si es variable por niños o no? La tarjeta y esa
															// mamada
		P.setDni(user.getLastName() + " " + user.getFirstName());
		P = repository.save(P);

	}

	public long ConteoPagos() {
		return repository.count();

	}
}
