package com.cleb.server;

import com.cleb.dao.JdbcReservationDAO;
import com.cleb.dao.JdbcUserDAO;
import com.cleb.dao.ReservationDAO;
import com.cleb.dao.UserDAO;

public class ServiceFactory {

    public static UserDAO getUserDAO() {
        return new JdbcUserDAO();
    }

    public static ReservationDAO getReservationDAO() {
        return new JdbcReservationDAO();
    }
}