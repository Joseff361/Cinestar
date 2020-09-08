package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	public Iterable<User> findAllByStatus(String status);
	
	
}
