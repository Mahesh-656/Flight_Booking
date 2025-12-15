package org.flightbooking.dao;

import java.util.List;
import java.util.Optional;

import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Flight;
import org.flightbooking.exception.IdNotFoundException;
import org.flightbooking.exception.NoResultFoundException;
import org.flightbooking.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class FlightDao {

	@Autowired
	private FlightRepository flightRepository;

	public Flight saveFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	public Flight getFlightById(Integer flightId) {
		Optional<Flight> opt = flightRepository.findById(flightId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("Cannot find the flight based on passed Id " + flightId);
		}

	}

	public List<Flight> getFlightBySourceAndDestination(String source, String destination) {
		return flightRepository.findBySourceAndDestination(source, destination);
	}

	public List<Flight> getFlightByAirLine(String airLine) {
		List<Flight> li = flightRepository.findByAirLine(airLine);
		if (!li.isEmpty()) {
			return li;
		} else {
			throw new NoResultFoundException("No data available on " + airLine);
		}
	}

	public Flight updateFlight(Integer id, Flight flight) {
		Optional<Flight> flight2 = flightRepository.findById(id);
		if (flight2.isPresent()) {
			Flight f = flight2.get();
			f.setAirLine(flight.getAirLine());
			f.setArrivalDateTime(flight.getArrivalDateTime());
			f.setDepartureDateTime(flight.getDepartureDateTime());
			f.setDestination(flight.getDestination());
			f.setPrice(flight.getPrice());
			f.setSource(flight.getSource());
			f.setTotalSeats(flight.getTotalSeats());
			return flightRepository.save(f);
		} else {
			throw new IdNotFoundException("Coudn't update the record, because of Invalid Id : " + id);
		}
	}

	public void deleteFlight(Integer id) {
		Optional<Flight> flight = flightRepository.findById(id);
		if (flight.isPresent()) {
			flightRepository.delete(flight.get());
		} else {
			throw new IdNotFoundException("Id not available : " + id);
		}
	}
	
	public Page<Flight> getFlightswithPaginationandSort(Integer pageNo,Integer pageSize,String field,String sortDir){
		Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(field).ascending():Sort.by(field).descending();
		Page<Flight> page=flightRepository.findAll(PageRequest.of(pageNo, pageSize,sort));
		if(!page.isEmpty()) {
			return page;
		}
		else {
			throw new NoResultFoundException("This page was empty");
		}
	}
}
