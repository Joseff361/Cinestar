package com.cinestar.application.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
public class Pago implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3864507835066720080L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	

	@Column(nullable= true)
	private Float monto;

	@Column (nullable= true)
	private java.sql.Timestamp hora  ;
	
	@Column (nullable=true)
	private String descripcion;
	
	@Column (nullable=false,length=1)
	private String estado;
	
	
	@OneToMany(
	        mappedBy = "pago",
	        cascade = CascadeType.PERSIST,
	        fetch = FetchType.LAZY
	    )
	private Set<Asiento> asientos;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private Usuario user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}



	public Timestamp getHora() {
		return hora;
	}

	public void setHora(Timestamp hora) {
		this.hora = hora;
	}

	public Set<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(Set<Asiento> asientos) {
		this.asientos = asientos;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}


	
	
}