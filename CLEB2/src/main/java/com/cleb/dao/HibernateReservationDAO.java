package com.cleb.dao;

import java.util.List;

import com.cleb.model.Reservation;

public class HibernateReservationDAO implements ReservationDAO {

    @Override
    public Reservation createReservation(Reservation reservation) {
        throw new UnsupportedOperationException("Hibernate version not implemented yet");
    }

    @Override
    public List<Reservation> getPendingReservations() {
        throw new UnsupportedOperationException("Hibernate version not implemented yet");
    }

    @Override
    public void approveReservation(long reservationId) {
        throw new UnsupportedOperationException("Hibernate version not implemented yet");
    }

    @Override
    public void rejectReservation(long reservationId) {
        throw new UnsupportedOperationException("Hibernate version not implemented yet");
    }

    @Override
    public List<Reservation> getAllReservations() {
        throw new UnsupportedOperationException("Hibernate version not implemented yet");
    }
    
    
    
}