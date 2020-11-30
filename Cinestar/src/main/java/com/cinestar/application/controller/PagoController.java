package com.cinestar.application.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cinestar.application.entity.Asiento;
import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Sala;
import com.cinestar.application.entity.Usuario;
import com.cinestar.application.service.AsientoService;
import com.cinestar.application.service.FuncionService;
import com.cinestar.application.service.PagoService;
import com.cinestar.application.service.UsuarioService;

@Controller
public class PagoController {
	@Autowired
	FuncionService funcionService;
	@Autowired
	PagoService pagoService;
	@Autowired
	AsientoService asientoService;
	@Autowired
	UsuarioService usuarioService;

	/**
	 * Retorna funciones por id de sede e id de peliculas
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/asientos/{id}")
	public String eleccionAsientos(@PathVariable String id, Model model) {

		model.addAttribute("funcion", funcionService.getFuncion(Long.parseLong(id)));
		model.addAttribute("asientoList", asientoService.findAsientos(funcionService.getFuncion(Long.parseLong(id)).get()));

		return "asientos";// Html;
	}

	/**
	 * Enviar los valores de los Asientos funcion y User
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/asientos/{id}")
	public String enviarAsientos(HttpServletRequest request,
			// @RequestParam("descripcion") String descripcion,
			@RequestParam("asientos") String asientos, @RequestParam("adulto") String adulto,
			@RequestParam("nino") String nino, @RequestParam("adultoMayor") String adultoMayor,
			 @RequestParam("user") String user ,
			@PathVariable String id
			,RedirectAttributes redirectAttributes) {

		Funcion funcion = funcionService.getFuncion(Long.parseLong(id)).get();

		Set<Asiento> asientoLista = new HashSet<>();
		for (String colufila : asientos.split("-")) {
			asientoLista.addAll((Collection<Asiento>) asientoService.findByColumnaAndByFilaAndByFuncion(
					colufila.substring(0, 1), Integer.parseInt(colufila.substring(1)), funcion));
		}
		Pago nuevo =pagoService.realizarPago(funcion, usuarioService.getUserByUsername(user), asientoLista,
				adulto + "-" + nino + "-" + adultoMayor);
	
		//Pago.id -->El codigo que se debe enviar
		System.out.println(adulto);
		System.out.println(nino);
		System.out.println(adultoMayor);
		System.out.println(user);
		System.out.println(asientos);
		
		return "redirect:/compra/"+nuevo.getId();
	}

	/**
	 * Recuperar la info de la compra hecha
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/compra/{id}")
	public String compraAsientos(@PathVariable String id, Model model) {
		Pago pa= pagoService.getPago(Long.parseLong(id));
		model.addAttribute("funcionList",pa);
		String result ="";
		

		for(Asiento a: pa.getAsientos()) {
			result+="-";
			result+=a.getIdFila();
			result+=a.getIdColumna().toString();
			
		
		}
		
		result=result.substring(1);
		model.addAttribute("asientos",result);
		model.addAttribute("diaSemana",pa.getAsientos().iterator().next().getFuncion().getDia().getDay());


		return "compra";// Html;
	}

	/**
	 * Enviar los valores de los Asientos funcion y User
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/compra/{id}")
	public String confirmarCompra(HttpServletRequest request,
			@RequestParam("medioPago") String medioPago, 
			@PathVariable String id) {

		// Chequeo Tarjeta
		
	/*	Pago P = pagoService.realizarPagoOficial(Long.parseLong(id), new VisaPagoStrategy());
		if (P.getEstado().equals("1")) {
			pagoService.confirmarPago(Long.parseLong(id));
		}

		else {
			pagoService.cancelarPago(Long.parseLong(id));

		}
		// cancelar
		pagoService.cancelarPago(Long.parseLong(id));*/
		
		System.out.println(medioPago);
		
		return "redirect:/pago/"+id+"/"+medioPago;
	}
	
	
	@GetMapping("/pago/{id}/{id1}")
	public String pago(@PathVariable String id, @PathVariable String id1,Model model) {

		//model.addAttribute("funcionList", pagoService.getPago(Long.parseLong(id)));
		return "pago";// Html;
	}
	
	
	/**
	 * Enviar los valores de los Asientos funcion y User
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/pago/{id}/{id1}")
	public String confirmarPago(HttpServletRequest request,
			@RequestParam("fechaVenc") String fechaVenc, 
			@RequestParam("numeroTarjeta") String numeroTarjeta, 
			@RequestParam("cvc") String cvc, 
			@PathVariable String id,
			@PathVariable String id1) {

		// Aceptar
		Pago P=null;
		if(id1.equals("0"))
				 P = pagoService.realizarPagoOficial(Long.parseLong(id), new VisaPagoStrategy());
		else if(id1.equals("1"))
			 P = pagoService.realizarPagoOficial(Long.parseLong(id), new MastercardPagoStrategy());
		if(P!=null) {
			if (P.getEstado().equals("1")) {
				pagoService.confirmarPago(Long.parseLong(id));
			}
	
			else {
				pagoService.cancelarPago(Long.parseLong(id));
	
			}
		}
		
		//pagoService.cancelarPago(Long.parseLong(id));
		
		System.out.println(fechaVenc);
		System.out.println(numeroTarjeta);
		System.out.println(cvc);
		return "redirect:/peliculas";
	}
	
	@GetMapping({"/tabla-pagos"})
	public String login(Model model, HttpServletRequest request) {
		
		//Recogiendo el usuario desde SpringSecurity y aniadiendolo al modelo
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		model.addAttribute("user", currentPrincipalName);
		
		Usuario usuario = usuarioService.getUserByUsername(currentPrincipalName);
		model.addAttribute("usuario",usuario);
		
		ArrayList<Pago> pagos = new ArrayList<>() ;
		
		for(Pago pago: pagoService.getPagosUsuario(usuario)) {
			pagos.add(pago);
		}
		model.addAttribute("pagos",pagos);
		return "tabla-pagos";
	}

}