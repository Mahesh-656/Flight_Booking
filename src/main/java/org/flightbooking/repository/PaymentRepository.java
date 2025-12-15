package org.flightbooking.repository;

import java.util.List;

import org.flightbooking.entity.Payment;
import org.flightbooking.util.ModeOfTranscation;
import org.flightbooking.util.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	
	@Query("select p from Payment p where p.status=?1")
	List<Payment> getPaymentByStatus(Enum<PaymentStatus> status);

	
	@Query("select p from Payment p where p.modeOfTranscation=:modeOfTransactionStatus")
	List<Payment> getPaymentByModeOfTransaction(Enum<ModeOfTranscation> modeOfTransactionStatus);


	@Query("select p from Payment p where p.booking.id=:id")
	Payment getPaymentByBookingId(Integer id);


	@Query("select p from Payment p where p.amount>?1")
	List<Payment> fetchPaymentWhereAmountGreaterThanValue(double price);

}
