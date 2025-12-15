package org.flightbooking.service;

import java.util.List;

import org.flightbooking.dao.BookingDao;
import org.flightbooking.dto.ResponseStructure;
import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Passenger;
import org.flightbooking.entity.Payment;
import org.flightbooking.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;

	public ResponseEntity<ResponseStructure<Booking>> saveBooking(Booking booking) {
		ResponseStructure<Booking> response = new ResponseStructure<Booking>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Booking details saved Successfully");
		response.setData(bookingDao.saveBooking(booking));
		return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBooking() {
		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All Booking details retrieved");
		response.setData(bookingDao.getAllBooking());
		return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Booking>> getBookingById(Integer id) {
		ResponseStructure<Booking> response = new ResponseStructure<Booking>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Booking details retrieved based on booking id : "+id);
		response.setData(bookingDao.getBookingById(id));
		return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlightId(Integer id) {
		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Booking details retrieved based on flight id : "+id);
		response.setData(bookingDao.getBookingByFlightId(id));
		return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(String date) {
		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Booking details retrieved based on date : "+date);
		response.setData(bookingDao.getBookingByDate(date));
		return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(String status) {
		ResponseStructure<List<Booking>> response = new ResponseStructure<List<Booking>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Booking details retrieved based on Status : "+status);
		response.setData(bookingDao.getBookingByStatus(status));
		return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengersByBooking(Integer id) {
		ResponseStructure<List<Passenger>> response = new ResponseStructure<List<Passenger>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Passengers retrieved based on id : "+id);
		response.setData(bookingDao.getAllPassengerInABooking(id));
		return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Payment>> getPaymentDetailsOfBooking(Integer id) {
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Payment details retrieved based on booking id : "+id);
		response.setData(bookingDao.getPaymentDetailsOfBooking(id));
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(Integer id,String status) {
		ResponseStructure<Booking> response = new ResponseStructure<Booking>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Booking Status Updated based on booking id : "+id);
		response.setData(bookingDao.updateBookingStatus(id,status));
		return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteBooking(Integer id) {
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Booking details deleted based on booking id : "+id);
		bookingDao.deleteBooking(id);
		response.setData("");
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Page<Booking>>> getBookingswithPaginationandSort(Integer pageNo,
			Integer pagedata, String field,String sortDir) {
		ResponseStructure<Page<Booking>> rs = new ResponseStructure<>();
		rs.setData(bookingDao.getBookingswithPaginationandSort(pageNo, pagedata, field,sortDir));
		rs.setMessage("Pages succsfully Retrieved");
		rs.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Page<Booking>>>(rs, HttpStatus.OK);
	}
}
