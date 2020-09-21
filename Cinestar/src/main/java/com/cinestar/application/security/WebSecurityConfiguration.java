package com.cinestar.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.cinestar.application.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	String[] resources = new String[] { "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**","/bootstrap/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(resources).permitAll()
		.antMatchers("/", "/login","/peliculas","/peliculas/categoria","/peliculas/{id}","/sedes","/sedes/{id}", 
				"/sedes/{id}/categoria","/peliculas/{id}/sedes/{id}/funciones").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/peliculas")
				.failureUrl("/login?error=true").usernameParameter("username").passwordParameter("password")
			.and()
				.logout().permitAll().logoutSuccessUrl("/peliculas");
		//"/login?logout"
	}

	BCryptPasswordEncoder bCryptPasswordEncoder;

	// Crea el encriptador de contraseñas
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}



	@Autowired
    UsuarioService userDetailsService;

    //Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }

}