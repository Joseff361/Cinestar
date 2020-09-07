package com.cinestar.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Asiento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3864507835066720080L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	@Column
	private Long idFila;
	@Column
	private Long idColumna;


	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFuncion", referencedColumnName = "id")
    private Funcion funcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPago", referencedColumnName = "id")
    private Pago pago;
}
