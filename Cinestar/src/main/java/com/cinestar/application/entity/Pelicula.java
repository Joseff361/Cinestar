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
	
	
	@Column
	private Long nombre;
	@Column
	private Long descripcion;
	@Column
	private Long imagen;
	@Column
	private Long genero;
	@OneToMany(
	        mappedBy = "pelicula",
	        cascade = CascadeType.PERSIST,
	        fetch = FetchType.LAZY
	    )
	private Set<Funcion> funciones;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNombre() {
		return nombre;
	}
	public void setNombre(Long nombre) {
		this.nombre = nombre;
	}
	public Long getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(Long descripcion) {
		this.descripcion = descripcion;
	}
	public Long getImagen() {
		return imagen;
	}
	public void setImagen(Long imagen) {
		this.imagen = imagen;
	}
	public Long getGenero() {
		return genero;
	}
	public void setGenero(Long genero) {
		this.genero = genero;
	}
	public Set<Funcion> getFunciones() {
		return funciones;
	}
	public void setFunciones(Set<Funcion> funciones) {
		this.funciones = funciones;
	}
	
	

}
