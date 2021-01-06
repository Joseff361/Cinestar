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
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pelicula implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3864507835066720080L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	
	@Column (unique=true,nullable=false)
	private String nombre;
	@Column(unique=true,nullable=false,length=512)
	private String descripcion;
	@Column (unique=true,nullable=false)
	private String imagen;
	@Column(nullable=false)
	private String genero;
	@OneToMany(
	        mappedBy = "pelicula",
	        cascade = CascadeType.PERSIST,
	        fetch = FetchType.EAGER
	    )
	private Set<Funcion> funciones;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Set<Funcion> getFunciones() {
		return funciones;
	}
	public void setFunciones(Set<Funcion> funciones) {
		this.funciones = funciones;
	}

}