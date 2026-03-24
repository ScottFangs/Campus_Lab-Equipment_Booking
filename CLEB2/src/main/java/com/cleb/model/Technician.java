package com.cleb.model;

public class Technician extends User {
    private static final long serialVersionUID = 1L;
    
    public Technician(int userId, String username, String password) {
    	super(userId, username, password, Role.TECHNICIAN);
    }
}
