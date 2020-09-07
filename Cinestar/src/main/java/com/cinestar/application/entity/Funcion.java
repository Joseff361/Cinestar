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
	private Long precio;
	
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

}