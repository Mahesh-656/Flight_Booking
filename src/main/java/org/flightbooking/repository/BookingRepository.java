package org.flightbooking.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.flightbooking.entity.Booking;
import org.flightbooking.entity.Passenger;
import org.flightbooking.entity.Payment;
import org.flightbooking.util.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query("select b from Booking b where b.flight.id=:id")
	List<Booking> getBookingByFlightId(Integer id);

	@Query("select b from Booking b where Date(b.bookingDate) =:d")
	List<Booking> findByBookingDate(LocalDate d);

	@Query("select b from Booking b where b.status=?1")
	List<Booking> getBookingByStatus(Enum<Status> status);

	@Query("select b.passengers from Booking b where b.id=?1")
	List<Passenger> getPassengersByBooking(Integer id);

	@Query("select b.payment from Booking b where b.id=?1")
	Payment getPaymentDetailsOfBooking(Integer id);

}
