package com.lauren.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lauren.springboot.model.Airport;


@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>{
	Optional<Airport> findByAirportCode(String airportCode);

}
