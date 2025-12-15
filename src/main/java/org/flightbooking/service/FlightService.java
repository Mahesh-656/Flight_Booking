package org.flightbooking.service;

import java.util.List;

import org.flightbooking.dao.FlightDao;
import org.flightbooking.dto.ResponseStructure;
import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Flight;
import org.flightbooking.entity.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

	@Autowired
	private FlightDao flightDao;

	public ResponseEntity<ResponseStructure<Flight>> saveFlight(Flight flight) {
		ResponseStructure<Flight> response = new ResponseStructure<Flight>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Flight details saved Successfully");
		response.setData(flightDao.saveFlight(flight));
		return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlight() {
		ResponseStructure<List<Flight>> response = new ResponseStructure<List<Flight>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrived all flights ");
		response.setData(flightDao.getAllFlights());
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Flight>> getFlightById(Integer flightId) {
		ResponseStructure<Flight> response = new ResponseStructure<Flight>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrived all flights");
		response.setData(flightDao.getFlightById(flightId));
		return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySourceAndDestination(String source,
			String destination) {

		ResponseStructure<List<Flight>> response = new ResponseStructure<List<Flight>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrived all flights");
		response.setData(flightDao.getFlightBySourceAndDestination(source,destination));
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightByAirLine(String airLine) {
		ResponseStructure<List<Flight>> response = new ResponseStructure<List<Flight>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrived all flights");
		response.setData(flightDao.getFlightByAirLine(airLine));
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Flight>> updateFlight(Integer id,Flight flight) {
		ResponseStructure<Flight> response = new ResponseStructure<Flight>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Flight details have been Successfully updated");
		response.setData(flightDao.updateFlight(id,flight));
		return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<String>> deleteFlight(Integer id) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.ACCEPTED.value());
		response.setMessage("Flight details have been deleted Successfully");
		response.setData("Flight Deleted");
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<Page<Flight>>> getFlightswithPaginationandSort(Integer pageNo,
			Integer pagedata, String field,String sortDir) {
		ResponseStructure<Page<Flight>> rs = new ResponseStructure<>();
		rs.setData(flightDao.getFlightswithPaginationandSort(pageNo, pagedata, field,sortDir));
		rs.setMessage("Pages succsfully Retrieved");
		rs.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Page<Flight>>>(rs, HttpStatus.OK);
	}

}
