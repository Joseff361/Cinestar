package com.cinestar.application.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Usuario;
import com.cinestar.application.service.AsientoService;
import com.cinestar.application.service.FuncionService;
import com.cinestar.application.service.PagoService;
import com.cinestar.application.service.UsuarioService;

import java.util.logging.Level; 
import java.util.logging.Logger;

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

	Logger logger = Logger.getLogger( PagoController.class.getName());
	
	/**
	 * Retorna funciones por id de sede e id de peliculas
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/asientos/{id}")
	public String eleccionAsientos(@PathVariable String id, Model model) {
		model.addAttribute("funcion", funcionService.getFuncion(Long.parseLong(id)));
		model.addAttribute("asientoList", asientoService.findAsientos(funcionService.getFuncion(Long.parseLong(id))));
		return "asientos";
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
		
			Funcion funcion = funcionService.getFuncion(Long.parseLong(id));
			Pago nuevo=pagoService.previoPago(funcion, asientos, adulto, nino, adultoMayor, user);	
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
		model.addAttribute("asientos",pagoService.generarStringAsientos(pa));
		model.addAttribute("diaSemana",pagoService.obtenerFechaFuncion(pa));
		return "compra";
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

		//Aqui deberia haber un chequeo de tarjeta
		logger.log(Level.INFO, medioPago);
		
		return "redirect:/pago/"+id+"/"+medioPago;
	}
	
	
	@GetMapping("/pago/{id}/{id1}")
	public String pago(@PathVariable String id, @PathVariable String id1,Model model) {
		return "pago";
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
		pagoService.accionPago(fechaVenc, numeroTarjeta, cvc, id, id1);
		return "redirect:/peliculas";
	}
	
	@GetMapping({"/tabla-pagos"})
	public String login(Model model, HttpServletRequest request) {
		
		//Recogiendo el usuario desde SpringSecurity y aniadiendolo al modelo
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Usuario usuario = usuarioService.getUserByUsername(currentPrincipalName);
		model.addAttribute("user", currentPrincipalName);
		model.addAttribute("usuario",usuario);
		model.addAttribute("pagos",pagoService.getPagosUsuario(usuario));
		return "tabla-pagos";
	}

}