package com.lauren.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lauren.springboot.model.Customer;
import com.lauren.springboot.model.Flight;
import com.lauren.springboot.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByCustomer(Customer customer);
	List<Reservation> findByFlight(Flight flight);
}
