package com.cinestar.application.service;


import static java.util.Collections.emptyList;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.cinestar.application.entity.Usuario;
import com.cinestar.application.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	@Autowired
    UsuarioRepository userRepository;

    @Override
     public UserDetails loadUserByUsername(String username){
    	//Buscar el usuario con el repositorio y si no existe lanzar una exepcion
    	Usuario appUser =  userRepository.findByUsername(username);
		if(appUser == null) {
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		//Crear El objeto UserDetails que va a ir en sesion y retornarlo.
		return new User(appUser.getUsername(), appUser.getPassword(), emptyList());
    }

	public Usuario getUserByUsername(String username){
		return (userRepository.findByUsername(username));
	}


	public void insertUsuario(String name, String lastname, String email, String username, String password) {
		Usuario joseff = new Usuario();
		joseff.setFirstName(name);
		joseff.setLastName(lastname);
		joseff.setEmail(email);
		joseff.setUsername(username);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;
		joseff.setPassword(passwordEncoder.encode(password));
		userRepository.save(joseff);
	}




}