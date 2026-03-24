package com.cleb.model;


public class Student extends User {
    private static final long serialVersionUID = 1L;
    
    public Student(int userId, String username, String password) {
    	super(userId, username, password, Role.STUDENT);
    }
}
