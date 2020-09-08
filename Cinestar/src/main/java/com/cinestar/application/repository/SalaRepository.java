package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Sala;

@Repository
public interface SalaRepository extends CrudRepository<Sala, Long>{

}
