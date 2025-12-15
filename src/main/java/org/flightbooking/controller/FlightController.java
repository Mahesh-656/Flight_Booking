package org.flightbooking.controller;

import java.util.List;

import org.flightbooking.dto.ResponseStructure;
import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Flight;
import org.flightbooking.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Flight>> addFlight(@RequestBody Flight flight) {
		return flightService.saveFlight(flight);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlight() {
		return flightService.getAllFlight();
	}

	@GetMapping("/{flightId}")
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(@PathVariable Integer flightId) {
		return flightService.getFlightById(flightId);
	}

	@GetMapping("/search")
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySourceAndDestination(@RequestParam String source,
			@RequestParam String destination) {
		return flightService.getFlightBySourceAndDestination(source, destination);
	}

	@GetMapping("/airline/{airLine}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightByAirLine(@PathVariable String airLine) {
		return flightService.getFlightByAirLine(airLine);
	}

	@PutMapping("/airline/{id}")
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(@PathVariable Integer id,
			@RequestBody Flight flight) {
		return flightService.updateFlight(id, flight);
	}

	@DeleteMapping("/airline/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteFlight(@PathVariable Integer id) {
		return flightService.deleteFlight(id);
	}
	
	@GetMapping("/sort")
    public ResponseEntity<ResponseStructure<Page<Flight>>> getPaginationWithSortInFlight(@RequestParam int pageNo,@RequestParam int pageData,@RequestParam String sortBy,@RequestParam String sortDir){
		return flightService.getFlightswithPaginationandSort(pageNo, pageData, sortBy,sortDir);
	}

}
