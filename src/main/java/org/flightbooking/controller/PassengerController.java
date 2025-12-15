package org.flightbooking.controller;

import java.util.List;

import org.flightbooking.dto.ResponseStructure;
import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Passenger;
import org.flightbooking.service.PassengerService;
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
@RequestMapping("/api/passengers")
public class PassengerController {
	
	@Autowired
	private PassengerService passengerService;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Passenger>> addPassenger(@RequestBody Passenger passenger){
		return passengerService.savePassenger(passenger);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassenger(){
		return passengerService.getAllPassenger();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(@PathVariable Integer id){
		return passengerService.getPassengerById(id);
	}
	
	@GetMapping("/contact/{contactNo}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContactNo(@PathVariable Long contactNo){
		return passengerService.getPassengerByContactNo(contactNo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Passenger>> updatePassengerInfo(@PathVariable Integer id,@RequestBody Passenger passenger){
		return passengerService.updatePassenger(id,passenger);
	}
	
	@GetMapping("/flight/{id}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerFlightId(@PathVariable Integer id){
		return passengerService.getPassengerByFlightId(id);
	}
	
	@GetMapping("/sort")
    public ResponseEntity<ResponseStructure<Page<Passenger>>> getPaginationWithSortInPassenger(@RequestParam int pageNo,@RequestParam int pageData,@RequestParam String field,@RequestParam String sortBy){
		return passengerService.getPassengerswithPaginationandSort(pageNo, pageData,field, sortBy);
	}

}
