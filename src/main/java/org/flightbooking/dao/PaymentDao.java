package org.flightbooking.dao;

import java.util.List;
import java.util.Optional;

import org.flightbooking.dto.PaymentDto;
import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Payment;
import org.flightbooking.exception.IdNotFoundException;
import org.flightbooking.exception.NoResultFoundException;
import org.flightbooking.repository.PaymentRepository;
import org.flightbooking.util.ModeOfTranscation;
import org.flightbooking.util.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDao {

	@Autowired
	private PaymentRepository paymentRepository;

	public Payment savePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	public List<Payment> getAllPayment() {
		List<Payment> payment = paymentRepository.findAll();
		if (!payment.isEmpty()) {
			return payment;
		} else {
			throw new NoResultFoundException("No data found in the application");
		}
	}

	public Payment getPaymentById(Integer id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.isPresent()) {
			return payment.get();
		} else {
			throw new IdNotFoundException("Payment details not found on this " + id);
		}
	}

	public List<Payment> getPaymentByStatus(Enum<PaymentStatus> status) {
		return paymentRepository.getPaymentByStatus(status);
	}

	public List<Payment> getPaymentByModeOfTranscationStatus(Enum<ModeOfTranscation> modeOfTranscationStatus) {
		return paymentRepository.getPaymentByModeOfTransaction(modeOfTranscationStatus);
	}

	public PaymentDto getPaymentByBookingId(Integer id) {
		Payment pay = paymentRepository.getPaymentByBookingId(id);

		PaymentDto dto = new PaymentDto();
		dto.setPaymentId(pay.getId());
		dto.setPaymentDate(pay.getPaymentDate());
		dto.setAmount(pay.getAmount());
		dto.setModeOfTransaction(pay.getModeOfTranscation());
		dto.setPaymentStatus(pay.getStatus());

		return dto;
	}

	public Payment updatePaymentStatus(Integer id, Payment payment) {
		Payment p = paymentRepository.findById(id).orElse(null);
		if (p != null) {
			p.setStatus(payment.getStatus());
			return paymentRepository.save(p);
		}else {
			throw new NoResultFoundException("Could not update the Payment Status");
		}
	}

	public List<Payment> fetchPaymentWhereAmountGreaterThanValue(double price) {
		List<Payment> payments=paymentRepository.fetchPaymentWhereAmountGreaterThanValue(price);
		if(!payments.isEmpty()) {
			return payments;
		}else {
			throw new NoResultFoundException("No data available...");
		}
	}
	
	public Page<Payment> getPaymentswithPaginationandSort(Integer pageNo,Integer pageSize,String field,String sortDir){
		Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(field).ascending():Sort.by(field).descending();
		Page<Payment> page=paymentRepository.findAll(PageRequest.of(pageNo, pageSize,sort));
		if(!page.isEmpty()) {
			return page;
		}
		else {
			throw new NoResultFoundException("This page was empty");
		}
	}

}
