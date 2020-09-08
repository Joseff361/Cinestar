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
public class Funcion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3864507835066720080L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	

	@Column
	private String tipo;
	@Column
	private String hora;
	@Column
	private String dia;
	@Column
	private Float precio;
	
	@OneToMany(
	        mappedBy = "funcion",
	        cascade = CascadeType.PERSIST,
	        fetch = FetchType.LAZY
	    )
	private Set<Asiento> asientos;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSala", referencedColumnName = "id")
    private Sala sala;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPelicula", referencedColumnName = "id")
    private Pelicula pelicula;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Set<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(Set<Asiento> asientos) {
		this.asientos = asientos;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	
	

}