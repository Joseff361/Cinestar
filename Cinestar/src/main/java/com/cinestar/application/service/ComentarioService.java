
package com.cinestar.application.service;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.entity.Comentario;
import com.cinestar.application.entity.ComentarioSede;
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
	
	public Long enviarComentario(String descripcion, Pago pago) {
		Comentario comentario = new Comentario();
		comentario.setHora(new Timestamp(System.currentTimeMillis()));
		comentario.setDescripcion(descripcion);
		comentario.setPago(pago);
		repository.save(comentario);
		return comentario.getId();
	}
	
	
	private Iterable<Comentario> verComentarioPorSede(Sede sede) {
		Set<Comentario> comentarios = new LinkedHashSet<>();
		
		for(Comentario C: repository.findAllByOrderByHoraAsc()) {
			if (C.getPago().getAsientos().iterator().next().getFuncion().getSala().getSede().getId().equals(sede.getId())) {
				comentarios.add(C);
				
			}
		}
		return comentarios;
	}
	public Iterable<ComentarioSede> verComentarioSede(Sede sede){
		Set<ComentarioSede> comentariosSede = new LinkedHashSet<>();

		for (Comentario comentario: verComentarioPorSede(sede)) {
			//Nuevo objeto comentarioSede
			ComentarioSede comentarioSede = new ComentarioSede(comentario.getDescripcion(), comentario.getHora(),
					comentario.getPago().getUser().getFirstName(), comentario.getPago().getUser().getLastName(), 
					comentario.getPago().getUser().getUsername());

			comentariosSede.add(comentarioSede);
		}
		return comentariosSede;
	}
	public Iterable<Comentario> verComentarioPorUsuario(Usuario user) {
		Set<Comentario> comentarios = new LinkedHashSet<>();
		for(Comentario C: repository.findAllByOrderByHoraAsc()) {
			
			if (C.getPago().getUser().getId().equals(user.getId())) {
				comentarios.add(C);
			}
		}
		return comentarios;
	}
	public void eliminarComentario(Comentario comentario) {
		repository.delete(comentario);
	}
}