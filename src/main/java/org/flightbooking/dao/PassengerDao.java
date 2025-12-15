package org.flightbooking.dao;

import java.util.List;
import java.util.Optional;

import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Passenger;
import org.flightbooking.exception.IdNotFoundException;
import org.flightbooking.exception.NoResultFoundException;
import org.flightbooking.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerDao {

	@Autowired
	private PassengerRepository passengerRepository;

	public Passenger savePassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}

	public List<Passenger> getAllPassenger() {
		List<Passenger> li = passengerRepository.findAll();
		if (!li.isEmpty()) {
			return li;
		} else {
			throw new NoResultFoundException("No Passenger's available in the database");
		}
	}

	public Passenger getPassengerById(Integer id) {
		Optional<Passenger> opt = passengerRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IdNotFoundException("Given Id is Not Available : " + id);
		}
	}

	public Passenger getPassengerByContactNo(Long contactNo) {
		Passenger passenger = passengerRepository.findByContactNo(contactNo);
		if (passenger != null) {
			return passenger;
		} else {
			throw new NoResultFoundException("Provided contact number couldn't found : " + contactNo);
		}
	}

	public Passenger updatePassenger(Integer id, Passenger passenger) {
		Optional<Passenger> p = passengerRepository.findById(id);
		if (p.isPresent()) {
			Passenger p1 = p.get();
			p1.setAge(passenger.getAge());
			p1.setBooking(passenger.getBooking());
			p1.setContactNo(passenger.getContactNo());
			p1.setGender(passenger.getGender());
			p1.setName(passenger.getName());
			p1.setSeatNumber(passenger.getSeatNumber());
			return passengerRepository.save(p1);
		} else {
			throw new IdNotFoundException("could not update the record");
		}
	}

	public List<Passenger> getPassengerByFlightId(Integer id) {
		System.out.println(passengerRepository.getPassengerByFlightId(id));
		return passengerRepository.getPassengerByFlightId(id);
	}
	
	public Page<Passenger> getPassengerswithPaginationandSort(Integer pageNo,Integer pageSize,String field,String sortDir){
		Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(field).ascending():Sort.by(field).descending();
		Page<Passenger> page=passengerRepository.findAll(PageRequest.of(pageNo, pageSize,sort));
		if(!page.isEmpty()) {
			return page;
		}
		else {
			throw new NoResultFoundException("This page was empty");
		}
	}

}
