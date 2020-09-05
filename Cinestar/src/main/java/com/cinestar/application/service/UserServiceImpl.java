package com.cinestar.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.entity.User;
import com.cinestar.application.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	//Inyeccion de dependencias de spring se encarga de traer recursos para no crear y eso 
	@Autowired
	UserRepository repository;
	
	@Override
	public Iterable<User> getAllUsers() {
		//return repository.findAllByStatus("Active");
		return repository.findAll();
	}
	

}
