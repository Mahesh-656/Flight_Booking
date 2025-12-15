package org.flightbooking.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.flightbooking.util.ModeOfTranscation;
import org.flightbooking.util.PaymentStatus;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@CreationTimestamp
	private LocalDateTime paymentDate;
	private Double amount;
	@Enumerated(EnumType.STRING)
	private ModeOfTranscation modeOfTranscation = ModeOfTranscation.CREDIT_CARD;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status = PaymentStatus.PENDING;

	@JoinColumn
	@JsonIgnore
	@OneToOne
	private Booking booking;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public ModeOfTranscation getModeOfTranscation() {
		return modeOfTranscation;
	}

	public void setModeOfTranscation(ModeOfTranscation modeOfTranscation) {
		this.modeOfTranscation = modeOfTranscation;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
