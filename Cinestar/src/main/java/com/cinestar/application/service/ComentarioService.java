package com.cinestar.application.service;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.entity.Comentario;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Sede;
import com.cinestar.application.entity.Usuario;
import com.cinestar.application.repository.ComentarioRepository;

@Service 
public class ComentarioService {
	@Autowired
	ComentarioRepository repository;
	public Iterable<Comentario> getComentarios() {
		return repository.findAllByOrderByHoraAsc();

	}
	public Comentario getComentario(Long id ) {
		Optional<Comentario> optional =  repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return new Comentario();
	}
	
	public void enviarComentario(String descripcion, Pago pago) {
		Comentario C= new Comentario();
		C.setHora(new Timestamp(System.currentTimeMillis()));
		C.setDescripcion(descripcion);
		C.setPago(pago);
		C = repository.save(C);
	}
	public Iterable<Comentario> verComentarioPorSede(Sede sede) {
		Set<Comentario> comentarios = new LinkedHashSet<>();
		for(Comentario C: repository.findAllByOrderByHoraAsc()) {
			
			if (C.getPago().getAsientos().iterator().next().getFuncion().getSala().getSede() == sede ) {
				comentarios.add(C);
			}
		}
		return comentarios;
	}
	public Iterable<Comentario> verComentarioPorUsuario(Usuario user) {
		Set<Comentario> comentarios = new LinkedHashSet<>();
		for(Comentario C: repository.findAllByOrderByHoraAsc()) {
			
			if (C.getPago().getUser()==user) {
				comentarios.add(C);
			}
		}
		return comentarios;
	}
	public void eliminarComentario(Comentario comentario) {
		repository.delete(comentario);
	}
}