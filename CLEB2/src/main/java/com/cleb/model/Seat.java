package com.cleb.model;

import java.io.Serializable;

public class Seat implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
	private int seatNumber;
	private boolean isAvailable;
	private Lab lab;
    
    public Seat(int seatNumber, boolean isAvailable, Lab lab) {
    	this.seatNumber = seatNumber;
    	this.isAvailable = isAvailable;
    	this.lab = lab;
    }
    
    public Seat() {
    	this.seatNumber = 0;
    	this.isAvailable = true;
    	this.lab = null;
    }
    
    public Seat(Seat s) {
    	this.seatNumber = s.seatNumber;
    	this.isAvailable = s.isAvailable;
    	this.lab = s.lab;
    }

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Lab getLab() {
		return lab;
	}

	public void setLab(Lab lab) {
		this.lab = lab;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Seat [seatNumber=" + seatNumber + ", isAvailable=" + isAvailable + ", lab=" + lab + "]";
	}
    
    
    
	
}
