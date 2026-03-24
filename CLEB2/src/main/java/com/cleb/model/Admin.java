package com.cleb.model;

public class Admin extends User {
    private static final long serialVersionUID = 1L;
    
    public Admin(int userId, String username, String password) {
    	super(userId, username, password, Role.ADMIN);
    }
}
