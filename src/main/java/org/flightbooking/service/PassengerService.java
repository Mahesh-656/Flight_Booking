package org.flightbooking.service;

import java.util.List;

import org.flightbooking.dao.PassengerDao;
import org.flightbooking.dto.ResponseStructure;
import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

	@Autowired
	private PassengerDao passengerDao;

	public ResponseEntity<ResponseStructure<Passenger>> savePassenger(Passenger passenger) {
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Passenger details saved Successfully");
		response.setData(passengerDao.savePassenger(passenger));
		return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassenger() {
		List<Passenger> li = passengerDao.getAllPassenger();
		ResponseStructure<List<Passenger>> response = new ResponseStructure<List<Passenger>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrieved all Passenger details : " + li.size());
		response.setData(passengerDao.getAllPassenger());
		return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(Integer id) {
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Passenger detail retrieved Successfully based on id : "+id);
		response.setData(passengerDao.getPassengerById(id));
		return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContactNo(Long contactNo) {
		ResponseStructure<Passenger> response=new ResponseStructure<Passenger>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Passenger details retrieved Successfully based on contact No : "+contactNo);
		response.setData(passengerDao.getPassengerByContactNo(contactNo));
		return new ResponseEntity<ResponseStructure<Passenger>>(response,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(Integer id, Passenger passenger) {
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Passenger details updated Successfully");
		response.setData(passengerDao.updatePassenger(id,passenger));
		return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.CREATED);
	}

	

	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByFlightId(Integer id) {
		ResponseStructure<List<Passenger>> response = new ResponseStructure<List<Passenger>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Passenger details based on FLight Id : "+id);
		response.setData(passengerDao.getPassengerByFlightId(id));
		return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengerswithPaginationandSort(Integer pageNo,
			Integer pagedata, String field,String sortDir) {
		ResponseStructure<Page<Passenger>> rs = new ResponseStructure<>();
		rs.setData(passengerDao.getPassengerswithPaginationandSort(pageNo, pagedata, field,sortDir));
		rs.setMessage("Pages succsfully Retrieved");
		rs.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Page<Passenger>>>(rs, HttpStatus.OK);
	}
	
}
