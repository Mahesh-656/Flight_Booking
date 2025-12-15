package org.flightbooking.dto;

import java.time.LocalDateTime;

import org.flightbooking.util.ModeOfTranscation;
import org.flightbooking.util.PaymentStatus;

public class PaymentDto {
	private Integer paymentId;
	private LocalDateTime paymentDate;
	private Double amount;
	private ModeOfTranscation modeOfTransaction;
	private PaymentStatus paymentStatus;

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
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

	public ModeOfTranscation getModeOfTransaction() {
		return modeOfTransaction;
	}

	public void setModeOfTransaction(ModeOfTranscation modeOfTransaction) {
		this.modeOfTransaction = modeOfTransaction;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
