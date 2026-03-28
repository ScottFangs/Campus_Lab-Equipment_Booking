package com.cleb.model;

import java.io.Serializable;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long reservationId;
	private User bookedBy;
	private String startTime;
	private String endTime;
	private Seat bookedSeat;
	private Equipment bookedEquipment;
	private String status;
	
	
	public Reservation(long reservationId, User bookedBy, String startTime, String endTime, Seat seat, Equipment equip, String status) {
		this.reservationId = reservationId;
        this.bookedBy = bookedBy;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedSeat = seat;
        this.bookedEquipment = equip;
        this.status = status;
	}
	
	public Reservation() {
		this.reservationId = 0;
		this.bookedBy = null;
		this.startTime = "?";
		this.endTime = "?";
		this.bookedSeat = null;
		this.bookedEquipment = null;
		this.status = "APPROVED";
	}
	
	public Reservation(Reservation r) {
		this.reservationId = r.reservationId;
		this.bookedBy = r.bookedBy;
		this.startTime = r.startTime;
		this.endTime = r.endTime;
		this.bookedSeat = r.bookedSeat;
		this.bookedEquipment = r.bookedEquipment;
		this.status = r.status;
	}

	public long getReservationId() {
		return reservationId;
	}
	
	public void setReservationId(long reservationId) { 
	    this.reservationId = reservationId; 
	}

	public User getBookedBy() {
		return bookedBy;
	}

	public void setBookedBy(User bookedBy) {
		this.bookedBy = bookedBy;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Seat getBookedSeat() {
		return bookedSeat;
	}

	public void setBookedSeat(Seat bookedSeat) {
		this.bookedSeat = bookedSeat;
	}

	public Equipment getBookedEquipment() {
		return bookedEquipment;
	}

	public void setBookedEquipment(Equipment bookedEquipment) {
		this.bookedEquipment = bookedEquipment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", bookedBy=" + bookedBy + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", bookedSeat=" + bookedSeat + ", bookedEquipment=" + bookedEquipment
				+ ", status=" + status + "]";
	}
	
	
	
	
	
	
}
