package com.cinestar.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cinestar.application.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}
