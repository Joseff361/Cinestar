package com.cinestar.application.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void realizarPago(Funcion funcion, Usuario user, Set<Asiento> asientos, String descripcion) {
		Pago P = new Pago();
		
		P.setHora(new Timestamp(System.currentTimeMillis()));

		P.setDescripcion(descripcion);
		P.setMonto(calculoCostoTotal(descripcion));// Ver costo si es variable por niños o no? La tarjeta y es mamada
		P.setUser(user);
		P.setEstado("0");//Estado 0 =  Incompleto el pago
		P = repository.save(P);
		for(Asiento a:asientos) {
			a.setPago(P);
			arepository.save(a);
		}
	}

	public void cancelarPago(long id) {
		Pago P=repository.findById(id).get();
		for(Asiento a:P.getAsientos()) {
			a.setPago(null);
			arepository.save(a);
		}
		repository.delete(P);
	}
	public void confirmarPago(long id) {
		Pago P=repository.findById(id).get();
		P.setEstado("1"); //Completo  el pago
		P=repository.save(P);
	}

	public float calculoCostoTotal(String descripcion) {
		String [] valores= descripcion.split("-");
		float monto=(float) 0.0;
		String diaSemana= ""+LocalDateTime.now().getDayOfWeek();

		//Adulto
		
		if(diaSemana=="MONDAY"|| diaSemana=="TUESDAY") {
			monto+=Integer.parseInt(valores[0])*9;
			monto+=Integer.parseInt(valores[1])*6;
			monto+=Integer.parseInt(valores[2])*7.5;
		}
		//Niño
		else if (diaSemana=="WENESDAY") {
			monto+=Integer.parseInt(valores[0])*10;
			monto+=Integer.parseInt(valores[1])*6.5;
			monto+=Integer.parseInt(valores[2])*8;			
		}
		//Adulto Mayor +60
		else {//THURSDAY SUNDAY
			monto+=Integer.parseInt(valores[0])*15;
			monto+=Integer.parseInt(valores[1])*10;
			monto+=Integer.parseInt(valores[2])*11.5;			
		}
		
		
		return  monto;
	}

	public Pago getPago(long id) {
			return repository.findById(id).get();
	}
	
}
