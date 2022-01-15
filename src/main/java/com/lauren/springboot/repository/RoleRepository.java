package com.lauren.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lauren.springboot.model.ERole;
import com.lauren.springboot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByName(ERole name);

}
