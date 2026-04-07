package com.cleb.dao;

import com.cleb.model.User;
import com.cleb.model.Admin;
import com.cleb.model.Role;
import com.cleb.model.Student;
import com.cleb.model.Technician;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cleb.auth.PasswordUtil;
import com.cleb.client.DataViewerInternalFrame;



public class JdbcUserDAO implements UserDAO {

    private static final Logger logger = LogManager.getLogger(JdbcUserDAO.class);
    private static final String URL = "jdbc:mysql://localhost:3307/cleb_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "usbw";

    @Override
public User authenticate(String username, String password) {
    //we fetch the stored hash and salt by username alone
    String sql = "SELECT userId, username, password_hash, salt, role " +
                 "FROM users WHERE username = ?";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, username);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                String salt       = rs.getString("salt");

                //verify the password using PBKDF2
                boolean valid = PasswordUtil.verifyPassword(password, salt, storedHash);
                if (!valid) return null; // if it's the wrong password


                int    id      = rs.getInt("userId");
                String roleStr = rs.getString("role");
                Role   role    = Role.valueOf(roleStr);

                if (role == Role.STUDENT)    return new Student(id, username, password);
                if (role == Role.ADMIN)      return new Admin(id, username, password);
                return new Technician(id, username, password);
            }
        }
    } catch (SQLException e) {
        logger.error("Authentication failed", e);
        throw new DatabaseException("Login failed", e);
    }
    return null;
}

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole().name());
            pstmt.executeUpdate();
            logger.info("User added: " + user.getUsername());
        } catch (SQLException e) {
            logger.error("Failed to add user", e);
            throw new DatabaseException("Failed to add user", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // For simplicity we only create basic User objects here
                // You can expand later to create proper Student/Admin/Technician
                User u = new com.cleb.model.Student(
                    rs.getInt("userId"),
                    rs.getString("username"),
                    rs.getString("password")
                );
                users.add(u);
            }
        } catch (SQLException e) {
            logger.error("Failed to get users", e);
            throw new DatabaseException("Failed to get users", e);
        }
        return users;
    }
}
