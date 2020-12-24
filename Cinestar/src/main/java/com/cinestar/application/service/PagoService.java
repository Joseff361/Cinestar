package com.cinestar.application.service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.controller.PagoStrategy;
import com.cinestar.application.entity.Asiento;
import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Usuario;
import com.cinestar.application.repository.AsientoRepository;
import com.cinestar.application.repository.PagoRepository;

import java.util.logging.Logger;

@Service
public class PagoService {
	@Autowired
	PagoRepository repository;
	@Autowired
	AsientoRepository arepository;

	Logger logger = Logger.getLogger(PagoService.class.getName()); 
	
	public Pago realizarPago(Funcion funcion, Usuario user, Set<Asiento> asientos, String descripcion) {
		Pago pago = new Pago();
		
		pago.setHora(new Timestamp(System.currentTimeMillis()));

		pago.setDescripcion(descripcion);
		pago.setMonto(calculoCostoTotal(funcion,descripcion));// Ver costo si es variable por niños o no? La tarjeta y es mamada
		pago.setUser(user);
		pago.setEstado("0");//Estado 0 =  Incompleto el pago
		pago = repository.save(pago);
		for(Asiento a:asientos) {
			a.setPago(pago);
			arepository.save(a);
		}
		return pago;
	}

	public void cancelarPago(long id) {
		Optional<Pago> optional = repository.findById(id);
		if(optional.isPresent()) {
			Pago pago = optional.get();
			for(Asiento a:pago.getAsientos()) {
				a.setPago(null);
				arepository.save(a);
			}
			repository.delete(pago);
		}
	}
	
	public Pago realizarPagoOficial(long id ,PagoStrategy estrategiaPago) {
		Optional<Pago> optional =  repository.findById(id);
		if(optional.isPresent()) {
			Pago pago = optional.get();
			estrategiaPago.realizarPago(pago);
			return pago;
		}
		
		return new Pago();
	}
	
	public void confirmarPago(long id ) {
		Optional<Pago> optional =  repository.findById(id);
		if(optional.isPresent()) {
			Pago pago = optional.get();
			repository.save(pago);
		}
	}

	public float calculoCostoTotal(Funcion funcion,String descripcion) {
		String [] valores= descripcion.split("-");
		float monto=(float) 0;
		 int diaSemana=funcion.getDia().getDay();
		
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
