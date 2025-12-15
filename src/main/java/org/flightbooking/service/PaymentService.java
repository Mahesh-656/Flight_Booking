package org.flightbooking.service;

import java.util.List;

import org.flightbooking.dao.PaymentDao;
import org.flightbooking.dto.PaymentDto;
import org.flightbooking.dto.ResponseStructure;
import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Payment;
import org.flightbooking.util.ModeOfTranscation;
import org.flightbooking.util.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	public ResponseEntity<ResponseStructure<Payment>> savePayment(Payment payment) {
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Payment details saved Successfully");
		response.setData(paymentDao.savePayment(payment));
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment() {
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrieved all payment details");
		response.setData(paymentDao.getAllPayment());
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(Integer id) {
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Payment details retrieved based on Id : " + id);
		response.setData(paymentDao.getPaymentById(id));
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(Enum<PaymentStatus> paymentStatus) {
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrieved all payment details based on status " + paymentStatus);
		response.setData(paymentDao.getPaymentByStatus(paymentStatus));
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByModeOfTranscation(
			Enum<ModeOfTranscation> modeOfTranscationStatus) {
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrieved all payment details based on Mode Of Transaction " + modeOfTranscationStatus);
		response.setData(paymentDao.getPaymentByModeOfTranscationStatus(modeOfTranscationStatus));
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<PaymentDto>> getPaymentByBookingId(Integer id) {
		ResponseStructure<PaymentDto> response = new ResponseStructure<PaymentDto>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Payment details retrieved based on booking Id : " + id);
		response.setData(paymentDao.getPaymentByBookingId(id));
		return new ResponseEntity<ResponseStructure<PaymentDto>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(Integer id, Payment payment) {
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("PaymentStatus updated based on Id : " + id);
		response.setData(paymentDao.updatePaymentStatus(id, payment));
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> fetchPaymentWhereAmountGreaterThanValue(double price) {
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrieved all payment details based on price greater than a particular value " + price);
		response.setData(paymentDao.fetchPaymentWhereAmountGreaterThanValue(price));
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Page<Payment>>> getPaymentswithPaginationandSort(Integer pageNo,
			Integer pagedata, String field,String sortDir) {
		ResponseStructure<Page<Payment>> rs = new ResponseStructure<>();
		rs.setData(paymentDao.getPaymentswithPaginationandSort(pageNo, pagedata, field,sortDir));
		rs.setMessage("Pages succsfully Retrieved");
		rs.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Page<Payment>>>(rs, HttpStatus.OK);
	}

}
