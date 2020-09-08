package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Asiento;

@Repository
public interface AsientoRepository  extends CrudRepository<Asiento, Long> {

}
