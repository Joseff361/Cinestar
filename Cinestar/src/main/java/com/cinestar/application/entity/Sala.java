package com.cinestar.application.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Sala implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3864507835066720080L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	@Column(nullable=false)
	private Long num;
	

	
	@OneToMany(
	        mappedBy = "sala",
	        cascade = CascadeType.PERSIST,
	        fetch = FetchType.EAGER
	    )
	private Set<Funcion> funciones;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSede", referencedColumnName = "id")
    private Sede sede;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Set<Funcion> getFunciones() {
		return funciones;
	}

	public void setFunciones(Set<Funcion> funciones) {
		this.funciones = funciones;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
}