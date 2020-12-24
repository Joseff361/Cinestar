package com.cinestar.application.entity;

public class ComentarioSede {

	public String comentario;
	public java.sql.Timestamp hora;
	public String firstName;
	public String lastName;
	public String username;
	
	
	public ComentarioSede(String comentario,  java.sql.Timestamp hora, String firstName,String lastName, String username) {
		this.comentario = comentario;
		this.hora = hora;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public java.sql.Timestamp getHora() {
		return hora;
	}


	public void setHora(java.sql.Timestamp hora) {
		this.hora = hora;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String toString() {
		return "ComentarioSede [comentario=" + comentario + ", hora=" + hora + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", username=" + username + "]";
	}
	
	
	
	
	
}
