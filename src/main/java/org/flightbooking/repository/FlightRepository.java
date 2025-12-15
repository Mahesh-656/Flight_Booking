package org.flightbooking.repository;

import java.util.List;

import org.flightbooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

	List<Flight> findBySourceAndDestination(String source, String destination);

	List<Flight> findByAirLine(String airLine);

}
