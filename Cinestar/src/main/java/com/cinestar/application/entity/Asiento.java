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
	private Integer idFila;
	@Column
	private String idColumna;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFuncion", referencedColumnName = "id")
    private Funcion funcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPago", referencedColumnName = "id")
    private Pago pago;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIdFila() {
		return idFila;
	}

	public void setIdFila(Integer idFila) {
		this.idFila = idFila;
	}

	public String getIdColumna() {
		return idColumna;
	}

	public void setIdColumna(String idColumna) {
		this.idColumna = idColumna;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	
	
}
