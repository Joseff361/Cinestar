package com.cinestar.application.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cinestar.application.entity.Comentario;
import com.cinestar.application.entity.ComentarioSede;
import com.cinestar.application.entity.Pago;
import com.cinestar.application.entity.Sede;
import com.cinestar.application.entity.Usuario;

@SpringBootTest
class ComentarioServiceTest {
	@Autowired
	ComentarioService comentarioService;
	@Autowired
	PagoService pagoService;
	@Autowired
	SedeService sedeService;
	@Autowired
	UsuarioService usuarioService;
	@Test
	void test_getComentario() {
		Pago pago= pagoService.getPago((long)1);
		Long id_comentario=comentarioService.enviarComentario("Buenisima", pago);
		Comentario comentario = comentarioService.getComentario(id_comentario);
		assertEquals(id_comentario,comentario.getId());
		comentarioService.eliminarComentario(comentario);
	}
	@Test
	void test_getComentarioNull() {
		Comentario comentario= comentarioService.getComentario((long)0);
		assertNull(comentario.getId());
	}
	@Test
	void test_getComentarios() {
		for(Comentario c:comentarioService.getComentarios()) {
			assertNotNull(c);
		}
	}
	@Test
	void test_enviarComentario() {
		Pago pago= pagoService.getPago((long)1);
		Long id_comentario=comentarioService.enviarComentario("Buenisima", pago);
		Comentario comentario=comentarioService.getComentario(id_comentario);
		assertEquals("Buenisima",comentario.getDescripcion());
		comentarioService.eliminarComentario(comentario);
	}
	@Test
	void test_verComentarioSede1() {
		Sede sede= sedeService.getSede((long)1);
		
		Iterator<ComentarioSede> i= comentarioService.verComentarioSede(sede).iterator();
		ComentarioSede previo=new ComentarioSede("Buena", Timestamp.valueOf("2010-12-01 12:30:30"),"Joas","Guaces", "JJKER");
	
		ComentarioSede actual;
		while(i.hasNext()) {
			actual=i.next();
			
			assertEquals(true,actual.getHora().after(previo.getHora()));
			previo=actual;	
		}
		
	}
	@Test
	void test_verComentarioSede2() {
		Sede sede= sedeService.getSede((long)1);
		Pago pago= pagoService.getPago((long)1);
		Long id_comentario=comentarioService.enviarComentario("Buenisima", pago);
		Iterator<ComentarioSede> i= comentarioService.verComentarioSede(sede).iterator();
		ComentarioSede previo=new ComentarioSede("Buena", Timestamp.valueOf("2010-12-01 12:30:30"),"Joas","Guaces", "JJKER");
	
		ComentarioSede actual;
		while(i.hasNext()) {
			actual=i.next();
			
			assertEquals(true,actual.getHora().after(previo.getHora()));
			previo=actual;	
		}
		comentarioService.eliminarComentario(comentarioService.getComentario(id_comentario));
		
	}
	@Test
	void test_verComentarioPorUsuario1() {
		
		Usuario usuario=usuarioService.getUserByUsername("kstrauss0");
		Iterator<Comentario> i= comentarioService.verComentarioPorUsuario(usuario).iterator();
		Comentario previo=new Comentario();
		previo.setHora(Timestamp.valueOf("2010-12-01 12:30:30"));
	
		Comentario actual;
		while(i.hasNext()) {
			actual=i.next();
			
			assertEquals(true,actual.getHora().after(previo.getHora()));
			previo=actual;	
		}
		
	}
	@Test
	void test_verComentarioPorUsuario2() {
		
		Usuario usuario=usuarioService.getUserByUsername("kstrauss0");
		Pago pago= pagoService.getPago((long)1);
		Iterator<Comentario> i= comentarioService.verComentarioPorUsuario(usuario).iterator();
		Comentario previo=new Comentario();
		previo.setHora(Timestamp.valueOf("2010-12-01 12:30:30"));
		Long id_comentario=comentarioService.enviarComentario("Buenisima", pago);
		Comentario actual;
		while(i.hasNext()) {
			actual=i.next();
			
			assertEquals(true,actual.getHora().after(previo.getHora()));
			previo=actual;	
		}
		comentarioService.eliminarComentario(comentarioService.getComentario(id_comentario));
		
	}

}
