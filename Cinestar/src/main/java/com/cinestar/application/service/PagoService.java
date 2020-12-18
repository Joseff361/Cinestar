package com.cinestar.application.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import com.cinestar.application.controller.PagoStrategy;
import com.cinestar.application.entity.Asiento;
import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Usuario;
import com.cinestar.application.repository.AsientoRepository;
import com.cinestar.application.repository.PagoRepository;

@Service
public class PagoService {
	@Autowired
	PagoRepository repository;
	@Autowired
	AsientoRepository arepository;

	public Pago realizarPago(Funcion funcion, Usuario user, Set<Asiento> asientos, String descripcion) {
		Pago P = new Pago();
		
		P.setHora(new Timestamp(System.currentTimeMillis()));

		P.setDescripcion(descripcion);
		P.setMonto(calculoCostoTotal(funcion,descripcion));// Ver costo si es variable por niños o no? La tarjeta y es mamada
		P.setUser(user);
		P.setEstado("0");//Estado 0 =  Incompleto el pago
		P = repository.save(P);
		for(Asiento a:asientos) {
			a.setPago(P);
			arepository.save(a);
		}
		return P;
	}

	public void cancelarPago(long id) {
		Optional<Pago> optional = repository.findById(id);
		if(optional.isPresent()) {
			Pago P = optional.get();
			for(Asiento a:P.getAsientos()) {
				a.setPago(null);
				arepository.save(a);
			}
			repository.delete(P);
		}
	}
	
	public Pago realizarPagoOficial(long id ,PagoStrategy estrategiaPago) {
		Optional<Pago> optional =  repository.findById(id);
		if(optional.isPresent()) {
			Pago P = optional.get();
			estrategiaPago.RealizarPago(P);
			return P;
		}
		
		return new Pago();
	}
	
	public void confirmarPago(long id ) {
		Optional<Pago> optional =  repository.findById(id);
		if(optional.isPresent()) {
			Pago P = optional.get();
			P = repository.save(P);
		}
	}

	public float calculoCostoTotal(Funcion funcion,String descripcion) {
		String [] valores= descripcion.split("-");
		float monto=(float) 0;
		 int diaSemana=funcion.getDia().getDay();

		//Adulto
		 System.out.println("DiaSemana:"+diaSemana);
		System.out.println("Adulto"+valores[0]);
		System.out.println("Niños"+valores[1]);
		System.out.print("+60"+valores[2]);
		if(diaSemana==1|| diaSemana==2) {
			monto+=Integer.parseInt(valores[0])*9;
			monto+=Integer.parseInt(valores[1])*6;
			monto+=Integer.parseInt(valores[2])*7.5;
		}

		else if (diaSemana==3) {
			monto+=Integer.parseInt(valores[0])*10;
			monto+=Integer.parseInt(valores[1])*6.5;
			monto+=Integer.parseInt(valores[2])*8;			
		}

		else {//THURSDAY -SUNDAY
			monto+=Integer.parseInt(valores[0])*15;
			monto+=Integer.parseInt(valores[1])*10;
			monto+=Integer.parseInt(valores[2])*11.5;			
		}
		System.out.println("monto"+monto);
		
		return  monto;
	}

	public Pago getPago(long id) {
		Optional<Pago> optional = repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return new Pago();
	}
	
	public Iterable<Pago> getPagosUsuario(Usuario usuario) {
		return repository.findAllByUserOrderByHoraAsc(usuario);
	}
}
