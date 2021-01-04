package com.cinestar.application.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.controller.MastercardPagoStrategy;
import com.cinestar.application.controller.PagoStrategy;
import com.cinestar.application.controller.VisaPagoStrategy;
import com.cinestar.application.entity.Asiento;
import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Usuario;
import com.cinestar.application.repository.AsientoRepository;
import com.cinestar.application.repository.PagoRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PagoService {
	@Autowired
	PagoRepository repository;
	@Autowired
	AsientoRepository arepository;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	AsientoService asientoService;
	
	Logger logger = Logger.getLogger(PagoService.class.getName()); 
	
	public Pago previoPago(Funcion funcion, String asientos,String adulto, String nino, String adultoMayor,String user) {
		Set<Asiento> asientoLista = new HashSet<>();
		for (String colufila : asientos.split("-")) {
			asientoLista.addAll((Collection<Asiento>) asientoService.findByColumnaAndByFilaAndByFuncion(
					colufila.substring(0, 1), Integer.parseInt(colufila.substring(1)), funcion));
		}
	
		logger.log(Level.INFO, adulto);
		logger.log(Level.INFO, nino);
		logger.log(Level.INFO, user);
		logger.log(Level.INFO, adultoMayor);
		logger.log(Level.INFO, asientos);
		return realizarPago(funcion, usuarioService.getUserByUsername(user), asientoLista,
				adulto + "-" + nino + "-" + adultoMayor);

	
	}
	private Pago realizarPago(Funcion funcion, Usuario user, Set<Asiento> asientos, String descripcion) {
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
	public Integer obtenerFechaFuncion(Pago pago) {
		Calendar cal= Calendar.getInstance();
		cal.setTime(pago.getAsientos().iterator().next().getFuncion().getDia());
		return cal.get(Calendar.DAY_OF_WEEK)-1;
	}
	public String generarStringAsientos(Pago pago) {
		StringBuilder result = new StringBuilder();
		for(Asiento a: pago.getAsientos()) {
			result.append("-");
			result.append(a.getIdFila());
			result.append(a.getIdColumna().toString());		
		}
	
		return result.toString().substring(1);
	}
	private void cancelarPago(long id) {
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
	
	private Pago realizarPagoOficial(long id ,PagoStrategy estrategiaPago) {
		Optional<Pago> optional =  repository.findById(id);
		if(optional.isPresent()) {
			Pago pago = optional.get();
			estrategiaPago.realizarPago(pago);
			return pago;
		}
		
		return new Pago();
	}
	
	private void confirmarPago(long id ) {
		Optional<Pago> optional =  repository.findById(id);
		if(optional.isPresent()) {
			Pago pago = optional.get();
			repository.save(pago);
		}
	}
	
	public void accionPago(String fechaVenc, String numeroTarjeta, String cvc, String id, String id1) {
		// Aceptar
		Pago pago =null;
		if(id1.equals("0"))
			pago = realizarPagoOficial(Long.parseLong(id), new VisaPagoStrategy());
		else if(id1.equals("1"))
			pago = realizarPagoOficial(Long.parseLong(id), new MastercardPagoStrategy());
		if(pago!=null) {
			if (pago.getEstado().equals("1")) {
				confirmarPago(Long.parseLong(id));
			}
	
			else {
				cancelarPago(Long.parseLong(id));
	
			}
		}
		
		logger.log(Level.INFO, fechaVenc);
		logger.log(Level.INFO, numeroTarjeta);
		logger.log(Level.INFO, cvc);
		
	}
	private float calculoCostoTotal(Funcion funcion,String descripcion) {
		String [] valores= descripcion.split("-");
		Calendar cal= Calendar.getInstance();
		float monto=(float) 0;
		cal.setTime(funcion.getDia());
		 int diaSemana=cal.get(Calendar.DAY_OF_WEEK)-1;
		
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