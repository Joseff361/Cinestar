package com.cinestar.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.repository.PagoRepository;

@Service
public class PagoService {
	@Autowired
	PagoRepository repository;
}
