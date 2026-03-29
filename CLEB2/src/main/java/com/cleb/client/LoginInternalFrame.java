package com.cleb.client;

import com.cleb.dao.UserDAO;
import com.cleb.model.User;
import com.cleb.server.ServiceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInternalFrame extends JInternalFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private GridBagConstraints gbc;

    public LoginInternalFrame() {
        super("Login to CLEB", true, true, true, true);
        setSize(450, 280);
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        // Username
        JLabel lblUsername = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblUsername, gbc);

        txtUsername = new JTextField("demario", 20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtUsername, gbc);

        // Password
        JLabel lblPassword = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField("pass123", 20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(txtPassword, gbc);

        // Login Button
        btnLogin = new JButton("LOGIN");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnLogin, gbc);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String password = new String(txtPassword.getPassword());

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginInternalFrame.this, "Please enter your Username", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginInternalFrame.this, "Please enter your Password", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                UserDAO userDAO = ServiceFactory.getUserDAO();
                User user = userDAO.authenticate(username, password);

                if (user != null) {
                    Session.setLoggedInUser(user);
                    JOptionPane.showMessageDialog(LoginInternalFrame.this, 
                        "Login successful!\nWelcome " + user.getUsername() + " (" + user.getRole() + ")", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    dispose();   // close login window

                    // Safely open DataViewer (no risky cast)
                    SwingUtilities.invokeLater(() -> {
                        JDesktopPane desktop = getDesktopPane();
                        if (desktop != null) {
                            DataViewerInternalFrame frame = new DataViewerInternalFrame();
                            desktop.add(frame);
                            frame.setVisible(true);
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(LoginInternalFrame.this, 
                        "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Cancel Button
        btnCancel = new JButton("CANCEL");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnCancel, gbc);

        btnCancel.addActionListener(e -> dispose());
    }
}