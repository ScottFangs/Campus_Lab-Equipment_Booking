package com.cleb.dao;

import com.cleb.model.Admin;
import com.cleb.model.Reservation;
import com.cleb.model.User;
import com.cleb.model.Role;
import com.cleb.model.Student;
import com.cleb.model.Technician;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcReservationDAO implements ReservationDAO {

    private static final Logger logger = LogManager.getLogger(JdbcReservationDAO.class);
    private static final String URL = "jdbc:mysql://localhost:3307/cleb_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "usbw";

    @Override
    public Reservation createReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (userId, startTime, endTime, seatNumber, equipmentId, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, reservation.getBookedBy().getUserId());
            pstmt.setString(2, reservation.getStartTime());
            pstmt.setString(3, reservation.getEndTime());
            pstmt.setObject(4, reservation.getBookedSeat() != null ? reservation.getBookedSeat().getSeatNumber() : null);
            pstmt.setObject(5, reservation.getBookedEquipment() != null ? reservation.getBookedEquipment().getEquipmentId() : null);
            pstmt.setString(6, reservation.getStatus());

            pstmt.executeUpdate();
            logger.info("Reservation created for user " + reservation.getBookedBy().getUsername());
            return reservation;
        } catch (SQLException e) {
            logger.error("Failed to create reservation", e);
            throw new DatabaseException("Failed to create reservation", e);
        }
    }

    @Override
    public List<Reservation> getPendingReservations() {
        List<Reservation> list = new ArrayList<>();
        String sql = """
            SELECT r.*, u.userId, u.username, u.role 
            FROM reservations r 
            JOIN users u ON r.userId = u.userId 
            WHERE r.status = 'PENDING' 
            ORDER BY r.reservationId DESC
            """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Create correct User subclass (Student / Admin / Technician)
                String roleStr = rs.getString("role");
                Role role = Role.valueOf(roleStr);
                User bookedBy;

                if (role == Role.STUDENT) {
                    bookedBy = new Student(rs.getInt("userId"), rs.getString("username"), "");
                } else if (role == Role.ADMIN) {
                    bookedBy = new Admin(rs.getInt("userId"), rs.getString("username"), "");
                } else {
                    bookedBy = new Technician(rs.getInt("userId"), rs.getString("username"), "");
                }

                // Create Reservation and set all fields
                Reservation r = new Reservation();
                r.setReservationId(rs.getLong("reservationId"));
                r.setBookedBy(bookedBy);
                r.setStartTime(rs.getString("startTime"));
                r.setEndTime(rs.getString("endTime"));
                r.setStatus(rs.getString("status"));

                list.add(r);
            }

            logger.info("Retrieved " + list.size() + " pending reservations from database");

        } catch (SQLException e) {
            logger.error("Failed to get pending reservations", e);
            throw new DatabaseException("Failed to get pending reservations", e);
        }

        return list;
    }
    
    
    @Override
    public void approveReservation(long reservationId) {
        String sql = "UPDATE reservations SET status = 'APPROVED' WHERE reservationId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, reservationId);
            pstmt.executeUpdate();
            logger.info("Reservation " + reservationId + " approved");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to approve reservation", e);
        }
    }

    @Override
    public void rejectReservation(long reservationId) {
        String sql = "UPDATE reservations SET status = 'REJECTED' WHERE reservationId = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, reservationId);
            pstmt.executeUpdate();
            logger.info("Reservation " + reservationId + " rejected");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to reject reservation", e);
        }
    }

    @Override
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(); // placeholder for now
    }
}