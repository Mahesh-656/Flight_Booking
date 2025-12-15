package org.flightbooking.repository;

import java.util.List;

import org.flightbooking.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

	Passenger findByContactNo(Long contactNo);

	@Query("select p from Passenger p where p.booking.flight.id=:id")
	List<Passenger> getPassengerByFlightId(Integer id);

}
