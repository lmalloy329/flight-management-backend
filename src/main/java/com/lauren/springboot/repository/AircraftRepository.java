package com.lauren.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lauren.springboot.model.Aircraft;


public interface AircraftRepository extends JpaRepository<Aircraft, Long>{
	Optional<Aircraft> findByAircraftCode(String aircraftCode);
}
