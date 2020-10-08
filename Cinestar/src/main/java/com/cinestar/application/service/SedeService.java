package com.cinestar.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cinestar.application.entity.Sede;
import com.cinestar.application.repository.SedeRepository;

@Service
public class SedeService {
	@Autowired
	SedeRepository repository;

	public Iterable<Sede> getSedes() {
		return repository.findAllByOrderByNombreAsc();
	}

	public Optional<Sede> getSede(Long id) {
		return repository.findById(id);
	}

}
