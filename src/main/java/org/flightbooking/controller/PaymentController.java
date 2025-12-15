package org.flightbooking.controller;

import java.util.List;

import org.flightbooking.dto.PaymentDto;
import org.flightbooking.dto.ResponseStructure;
import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Payment;
import org.flightbooking.service.PaymentService;
import org.flightbooking.util.ModeOfTranscation;
import org.flightbooking.util.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Payment>> recordPayment(@RequestBody Payment payment) {
		return paymentService.savePayment(payment);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment() {
		return paymentService.getAllPayment();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(@PathVariable Integer id) {
		return paymentService.getPaymentById(id);
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentById(@PathVariable String status) {
		Enum<PaymentStatus> paymentStatus = PaymentStatus.valueOf(status.toUpperCase());
		return paymentService.getPaymentByStatus(paymentStatus);
	}

	@GetMapping("/modeoftransaction/{status}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByModeOfTranscation(@PathVariable String status) {
		Enum<ModeOfTranscation> MOTstatus = ModeOfTranscation.valueOf(status.toUpperCase());
		return paymentService.getPaymentByModeOfTranscation(MOTstatus);
	}

	@GetMapping("/payment/booking/{id}")
	public ResponseEntity<ResponseStructure<PaymentDto>> getPaymentByBookingId(@PathVariable Integer id) {
		return paymentService.getPaymentByBookingId(id);
	}

	@PatchMapping("/updateStatus/{id}")
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(@PathVariable Integer id,
			@RequestBody Payment payment) {
		return paymentService.updatePaymentStatus(id, payment);
	}

	@GetMapping("/payment/{price}")
	public ResponseEntity<ResponseStructure<List<Payment>>> fetchPaymentWhereAmountGreaterThanValue(
			@PathVariable double price) {
		return paymentService.fetchPaymentWhereAmountGreaterThanValue(price);
	}

	@GetMapping("/sort")
	public ResponseEntity<ResponseStructure<Page<Payment>>> getPaginationWithSortInPayment(@RequestParam int pageNo,@RequestParam int pageData,@RequestParam String field,@RequestParam String sortDir) {
		return paymentService.getPaymentswithPaginationandSort(pageNo, pageData, field,sortDir);
	}
}
