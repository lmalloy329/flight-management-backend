package com.lauren.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lauren.springboot.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{

}
