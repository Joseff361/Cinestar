package com.cinestar.application.service;


import static java.util.Collections.emptyList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

     //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
     Usuario appUser =  userRepository.findByUsername(username);


		if(appUser == null) {

			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}


    //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
    UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), emptyList());
         return user;
    }

	public Object getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<Usuario> getUser(Long id) {
		return userRepository.findById( id);
	}







}