package com.lauren.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lauren.springboot.model.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long>{

}
