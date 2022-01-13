package com.lauren.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lauren.springboot.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
