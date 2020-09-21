package com.cinestar.application.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cinestar.application.entity.Asiento;
import com.cinestar.application.entity.Funcion;
import com.cinestar.application.entity.Pago;
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

		// model.addAttribute("funcion",
		// funcionService.getFuncion(Long.parseLong(id)).get().getAsientos());
		model.addAttribute("funcion", asientoService.findAsientos(funcionService.getFuncion(Long.parseLong(id)).get()));

		return "asientos";// Html;
	}

	/**
	 * Enviar los valores de los Asientos funcion y User
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/asientos/{id}")
	@ResponseBody
	public String enviarAsientos(HttpServletRequest request,
			// @RequestParam("descripcion") String descripcion,
			@RequestParam("asientos") String asientos, @RequestParam("adulto") String adulto,
			@RequestParam("nino") String nino, @RequestParam("adultoMayor") String adultoMayor,
			 @RequestParam("user") String user ,
			@PathVariable String id) {

		Funcion funcion = funcionService.getFuncion(Long.parseLong(id)).get();

		Set<Asiento> asientoLista = new HashSet<>();
		for (String colufila : asientos.split("-")) {
			asientoLista.addAll((Collection<Asiento>) asientoService.findByColumnaAndByFilaAndByFuncion(
					colufila.substring(0, 1), Integer.parseInt(colufila.substring(1)), funcion));
		}
		pagoService.realizarPago(funcion, usuarioService.getUserByUsername(user), asientoLista,
				nino + "-" + adulto + "-" + adultoMayor);// get Usuario xd

		return "asientos";// Html;
	}

	/**
	 * Recuperar la info de la compra hecha
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/compra/{id}")
	public String compraAsientos(@PathVariable String id, Model model) {

		model.addAttribute("funcionList", pagoService.getPago(Long.parseLong(id)));
		return "compra";// Html;
	}

	/**
	 * Enviar los valores de los Asientos funcion y User
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/compra/{id}")
	@ResponseBody
	public String confirmarCompra(HttpServletRequest request,
			// @RequestParam("descripcion") String descripcion,
			@RequestParam("asientos") String asientos, @RequestParam("adulto") String adulto,
			@RequestParam("nino") String nino, @RequestParam("adultoMayor") String adultoMayor,
			@PathVariable String id) {

		// Chequeo Tarjeta
		
		Pago P = pagoService.realizarPagoOficial(Long.parseLong(id), new VisaPagoStrategy());
		if (P.getEstado().equals("1")) {
			pagoService.confirmarPago(Long.parseLong(id));
		}

		else {
			pagoService.cancelarPago(Long.parseLong(id));

		}
		// cancelar
		pagoService.cancelarPago(Long.parseLong(id));

		return "asientos";// Html;
	}

}
