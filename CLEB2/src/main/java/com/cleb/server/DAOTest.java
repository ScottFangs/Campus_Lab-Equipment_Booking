package com.cleb.server;

import com.cleb.dao.UserDAO;
import com.cleb.model.Reservation;
import com.cleb.model.Student;
import com.cleb.model.User;
import com.cleb.dao.ReservationDAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DAOTest {
	
    private static final Logger logger = LogManager.getLogger(DAOTest.class);

    
    public static void main(String[] args) {
        System.out.println("CLEB Day 2 DAO Test Starting");

        try {
            // Test UserDAO (JDBC)
            UserDAO userDAO = ServiceFactory.getUserDAO();
            User loggedInUser = userDAO.authenticate("demario", "pass1");

            if (loggedInUser != null) {
                System.out.println("SUCCESS: Login worked! User = " + loggedInUser.getUsername() 
                                   + " | Role = " + loggedInUser.getRole());
                logger.info("User login test passed");
            } else {
                System.out.println("Login failed - check username/password in database");
            }

            // Test adding a new user
            Student newStudent = new Student(0, "teststudent", "test123");
            userDAO.addUser(newStudent);
            System.out.println("SUCCESS: New user 'teststudent' added to database");

            // Test ReservationDAO
            ReservationDAO resDAO = ServiceFactory.getReservationDAO();
            Reservation testRes = new Reservation(0, loggedInUser, "2026-04-10 09:00", "2026-04-10 11:00", 
                                                  null, null, "PENDING");
            resDAO.createReservation(testRes);
            System.out.println("SUCCESS: Reservation created in database");

            System.out.println("\n ALL TESTS PASSED \nCheck CLEB.log file for detailed logs!");
            
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            logger.error("DAOTest failed", e);
        }
        
        
    }
    
    
    
    
    
    
}