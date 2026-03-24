package com.cleb.dao;

import java.util.List;
import com.cleb.model.Reservation;

public interface ReservationDAO {
	Reservation createReservation(Reservation reservation);
	List<Reservation> getPendingReservations();
	void approveReservation(long reservationId);
	void rejectReservation(long reservationId);
	List<Reservation> getAllReservations();
	
	
}
	