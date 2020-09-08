package com.cinestar.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinestar.application.repository.SalaRepository;

@Service
public class SalaService {
	@Autowired
	SalaRepository repository;
}
