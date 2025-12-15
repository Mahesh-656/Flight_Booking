package org.flightbooking.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Flight;
import org.flightbooking.entity.Passenger;
import org.flightbooking.entity.Payment;
import org.flightbooking.exception.NoPassengerFoundException;
import org.flightbooking.exception.NoResultFoundException;
import org.flightbooking.repository.BookingRepository;
import org.flightbooking.repository.FlightRepository;
import org.flightbooking.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private FlightRepository flightRepository;

	public Booking saveBooking(Booking booking) {

		Optional<Flight> flight = flightRepository.findById(booking.getFlight().getId());

		if (flight.isPresent()) {
			booking.setFlight(flight.get());
		} else {
			throw new NoResultFoundException("Provided flight id is not available " + booking.getFlight().getId());
		}

		List<Passenger> passengers = booking.getPassengers();
		if(passengers!=null) {
			for (Passenger p : passengers) {
				p.setBooking(booking);
			}
		}else {
			throw new NoPassengerFoundException("Please provide passenger details for booking..");
		}

		Payment payment = booking.getPayment();
		if (payment == null) {
			payment = new Payment();
			booking.setPayment(payment);
		}

		payment.setBooking(booking);
		payment.setAmount(flight.get().getPrice() * passengers.size());

		return bookingRepository.save(booking);
	}

	public List<Booking> getAllBooking() {

		return bookingRepository.findAll();
	}

	public Booking getBookingById(Integer id) {
		Optional<Booking> booking = bookingRepository.findById(id);
		if (booking.isPresent()) {
			return booking.get();
		} else {
			throw new NoResultFoundException("Booking id is not found " + id);
		}
	}

	public List<Booking> getBookingByFlightId(Integer id) {
		List<Booking> booking = bookingRepository.getBookingByFlightId(id);
		return booking;
	}

	public List<Booking> getBookingByDate(String date) {
		LocalDate d = LocalDate.parse(date);
		return bookingRepository.findByBookingDate(d);
	}

	public List<Booking> getBookingByStatus(String status) {
		Enum<Status> st = Status.valueOf(status.toUpperCase());
		return bookingRepository.getBookingByStatus(st);
	}

	public List<Passenger> getAllPassengerInABooking(Integer id) {
		Optional<Booking> bookings = bookingRepository.findById(id);
		if (bookings.isPresent()) {
			return bookingRepository.getPassengersByBooking(id);
		} else {
			throw new NoResultFoundException("Passengers data not Found");
		}
	}

	public Payment getPaymentDetailsOfBooking(Integer id) {
		Payment payment = bookingRepository.getPaymentDetailsOfBooking(id);
		if (payment != null) {
			return payment;
		}
		throw new NoResultFoundException("No payment details available");
	}

	public Booking updateBookingStatus(Integer id, String status) {
		Optional<Booking> bookings = bookingRepository.findById(id);
		if (bookings.isPresent()) {
			Booking b = bookings.get();
			b.setStatus(Status.valueOf(status));
			return bookingRepository.save(b);
		} else {
			throw new NoResultFoundException("Passengers data not Found");
		}
	}

	public void deleteBooking(Integer id) {
		Optional<Booking> bookings = bookingRepository.findById(id);
		if (bookings.isPresent()) {
			bookingRepository.delete(bookings.get());
		} else {
			throw new NoResultFoundException("Passengers data not Found");
		}
	}

	public Page<Booking> getBookingswithPaginationandSort(
	        Integer pageNo, Integer pageSize, String field, String sortDir) {

	    Sort sort = sortDir.equalsIgnoreCase("asc") 
	                ? Sort.by(field).ascending() 
	                : Sort.by(field).descending();

	    PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
	    Page<Booking> page = bookingRepository.findAll(pageRequest);

	    if (page.isEmpty()) {
	        throw new NoResultFoundException("This page was empty");
	    }
	    return page;
	}


}
