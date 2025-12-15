package org.flightbooking.controller;

import java.util.List;
import java.util.Map;

import org.flightbooking.dto.ResponseStructure;
import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Passenger;
import org.flightbooking.entity.Payment;
import org.flightbooking.service.BookingService;
import org.flightbooking.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> createBooking(@RequestBody Booking booking) {
		return bookingService.saveBooking(booking);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBooking() {
		return bookingService.getAllBooking();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(@PathVariable Integer id) {
		return bookingService.getBookingById(id);
	}

	@GetMapping("/flight/{id}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByflight(@PathVariable Integer id) {
		return bookingService.getBookingByFlightId(id);
	}

	@GetMapping("/bookings/{date}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(@PathVariable String date) {
		return bookingService.getBookingByDate(date);
	}

	@GetMapping("/bookings/{status}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(@PathVariable String status) {
		return bookingService.getBookingByStatus(status);
	}

	@GetMapping("/booking/{id}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengersInABooking(@PathVariable Integer id) {
		return bookingService.getAllPassengersByBooking(id);
	}

	@GetMapping("/payment/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentDetailsOfBooking(@PathVariable Integer id) {
		return bookingService.getPaymentDetailsOfBooking(id);
	}

	@PatchMapping("/updateStatus/{id}")
	public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(@PathVariable Integer id,
			@RequestBody Map<String, String> status) {
		return bookingService.updateBookingStatus(id, status.get("status"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBookingById(@PathVariable Integer id) {
		return bookingService.deleteBooking(id);
	}

	@GetMapping("/sort")
    public ResponseEntity<ResponseStructure<Page<Booking>>> getPaginationWithSortInBooking(@RequestParam int pageNo,@RequestParam int pageData,@RequestParam String sortBy ,@RequestParam String sortDir){
		return bookingService.getBookingswithPaginationandSort(pageNo, pageData, sortBy,sortDir);
	}

}
